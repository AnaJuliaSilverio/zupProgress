package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.ProjectModel;
import com.zup.zupProgress.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {
    Optional<ProjectModel> findByName(@Param("name") String name);
    @Query("SELECT p.name FROM ProjectModel p")
    List<String> getAllProjectName();

    @Query("SELECT s.name FROM StudentModel s JOIN s.fkProject p WHERE p.name = :projectName")
    List<String> findStudentNamesByProjectName(String projectName);

    @Query("SELECT s FROM StudentModel s JOIN s.fkProject p WHERE p.name = :projectName")
    List<StudentModel> findStudentByProjectName(String projectName);

}
