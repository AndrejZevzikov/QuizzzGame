package com.backend.quizzz.repository;

import com.backend.quizzz.model.Difficulty;
import com.backend.quizzz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT q FROM Question q WHERE  id NOT IN (:ids) AND difficulty = :difficulty")
    List<Question> findQuestionsWithoutGivenIdsAndByCurrentScore(
            @Param("ids") List<Long> ids,
            @Param("difficulty") Difficulty difficulty);

    @Query(value = "SELECT q FROM Question q WHERE difficulty = :difficulty")
    List<Question> findQuestionByGivenDifficulty(@Param("difficulty") Difficulty difficulty);
}