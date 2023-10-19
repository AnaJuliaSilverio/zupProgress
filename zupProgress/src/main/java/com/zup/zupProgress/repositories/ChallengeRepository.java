package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.ChallengeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeModel, Long> {
    Optional<ChallengeModel> findByName(String name);
}
