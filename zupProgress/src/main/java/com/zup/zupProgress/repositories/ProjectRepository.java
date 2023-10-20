package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.ProjectModel;
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

}
