package com.backend.quizzz.validation;

import com.backend.quizzz.exception.InvalidUrlForQuestionImage;
import com.backend.quizzz.exception.NoMoreNewQuestionsException;
import com.backend.quizzz.exception.NoTextInQuestion;
import com.backend.quizzz.model.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionServiceValidation {

    public static final String EMPTY_TEXT = "Can't save Question because text field is empty";
    public static final String NO_MORE_UNUSED_QUESTION_IN_DATABASE = "No more unused question in database";
    public static final String INVALID_URL_FOR_QUESTION_IMAGE = "Invalid url for question image";

    public void validateUrl(Question question) throws InvalidUrlForQuestionImage {
        if (question.getImageLink().length() < 5)
            throw new InvalidUrlForQuestionImage(INVALID_URL_FOR_QUESTION_IMAGE);
    }

    public void validateNewQuestion(Question question) throws NoTextInQuestion {
        if (question.getQuestionText().isEmpty()) throw new NoTextInQuestion(EMPTY_TEXT);
    }

    public void validateNewQuestions(List<Question> questions) throws NoTextInQuestion {
        for (Question question : questions) {
            if (question.getQuestionText().isEmpty()) throw new NoTextInQuestion(EMPTY_TEXT);
        }
    }

    public void hasAnyNewQuestions(List<Question> questions) throws NoMoreNewQuestionsException {
        if (questions.size() == 0) throw new NoMoreNewQuestionsException(NO_MORE_UNUSED_QUESTION_IN_DATABASE);
    }
}