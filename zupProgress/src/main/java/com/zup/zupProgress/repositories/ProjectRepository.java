package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {
    @Query("SELECT p FROM Projects p WHERE p.name = :name")
    Optional<ProjectModel> findByName(@Param("name") String name);

}
