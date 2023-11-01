package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.InstructorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<InstructorModel,Long> {

}
