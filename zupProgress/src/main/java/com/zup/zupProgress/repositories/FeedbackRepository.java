package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.FeedbackModel;
import com.zup.zupProgress.model.TypeOfAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackModel,Long> {
    @Query("SELECT f FROM FeedbackModel f WHERE f.challengeModel.title = :challengeName AND f.type = :feedbackType AND f.studentModel.name = :studentName")
    List<FeedbackModel> findFeedbackByChallengeNameAndType(
            String challengeName, TypeOfAssessment feedbackType,String studentName
    );
}
