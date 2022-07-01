package com.backend.quizzz.validation;

import com.backend.quizzz.exception.NoQuestionForGivenAnswersException;
import com.backend.quizzz.exception.NoSuchQuestionException;
import com.backend.quizzz.model.Answer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AnswerServiceValidation {

    public static final String NO_QUESTION = "Can't save answer because no question to assign";
    public static final String QUESTION_NOT_FOUND = "Question not found with id = ";

    public void isQuestionExists(List<Answer> answers, Long id, Logger logger) throws NoSuchQuestionException {
        if (answers.size() == 0) {
            logger.error(QUESTION_NOT_FOUND + id);
            throw new NoSuchQuestionException(QUESTION_NOT_FOUND + id);
        }
    }

    public void isAnswersHasQuestion(List<Answer> answers, Logger logger) throws NoQuestionForGivenAnswersException {
        for (Answer answer : answers) {
            if (answer.getQuestion() == null) {
                logger.error(NO_QUESTION);
                throw new NoQuestionForGivenAnswersException(NO_QUESTION);
            }
        }
    }
}