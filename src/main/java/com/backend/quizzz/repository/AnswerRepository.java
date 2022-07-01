package com.backend.quizzz.repository;

import com.backend.quizzz.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(value = "SELECT a FROM Answer a WHERE question_id = :id AND isCorrect = TRUE")
    Answer getCorrectAnswerByQuestionId(@Param("id") Long id);

    @Query(value = "SELECT a FROM Answer a Where question_id = :id")
    List<Answer> getAnswersByQuestionId(@Param("id") Long id);
}