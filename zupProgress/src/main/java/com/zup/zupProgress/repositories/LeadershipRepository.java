package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.LeadershipModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadershipRepository extends JpaRepository<LeadershipModel,Long> {
}
