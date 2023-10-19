package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface ProjectRepository extends JpaRepository<ProjectModel, Long> {
    Optional<ProjectModel> findByName(@Param("name") String name);

}
