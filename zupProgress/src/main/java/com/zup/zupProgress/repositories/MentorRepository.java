package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.MentorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<MentorModel,Long> {
}
