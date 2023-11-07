package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.FeedbackAtributesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackAtributesRepository extends JpaRepository<FeedbackAtributesModel,Long> {
    @Query("SELECT p.atributes FROM FeedbackAtributesModel p")
    List<String> getAllFeedbackAtributesNames();
}
