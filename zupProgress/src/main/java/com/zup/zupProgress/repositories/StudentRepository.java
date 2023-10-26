package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository  extends JpaRepository<StudentModel, Long>{

    Optional<StudentModel> findByName(@Param("name") String name);
    StudentModel findByEmail(String email);

}


