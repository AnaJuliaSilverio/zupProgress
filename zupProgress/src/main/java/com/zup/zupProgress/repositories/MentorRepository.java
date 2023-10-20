package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.MentorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<MentorModel,Long> {
    @Query("SELECT p.name FROM MentorModel p")
    List<String> getAllMentorName();
    MentorModel findByName(String name);
}
