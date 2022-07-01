package com.backend.quizzz.validation;

import com.backend.quizzz.exception.NoQuestionForGivenAnswersException;
import com.backend.quizzz.model.Answer;
import com.backend.quizzz.repository.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AnswerServiceValidationTest {

    private List<Answer> answers;
    private AnswerServiceValidation validator;

    @BeforeEach
    void setUp() {
        answers = new ArrayList<>();
        validator = new AnswerServiceValidation();
        answers.add(new Answer());
        answers.add(new Answer());

    }

    @Test
    void testIsAnswerHasQuestion() throws NoQuestionForGivenAnswersException {

//        assertThrows(NoQuestionForGivenAnswersException.class, () -> {validator.isAnswersHasQuestion(answers,);});
    }
}