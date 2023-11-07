package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.ChallengeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeModel, Long> {
    @Query("SELECT p.title FROM ChallengeModel p")
    List<String> getAllChallengeTitle();
    Optional<ChallengeModel> findByTitle(String name);
}
