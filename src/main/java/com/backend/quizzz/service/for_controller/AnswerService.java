package com.backend.quizzz.service.for_controller;

import com.backend.quizzz.exception.NoQuestionForGivenAnswersException;
import com.backend.quizzz.exception.NoSuchQuestionException;
import com.backend.quizzz.model.Answer;
import com.backend.quizzz.repository.AnswerRepository;
import com.backend.quizzz.validation.AnswerServiceValidation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerServiceValidation answerServiceValidation;
    private static Logger LOGGER = LoggerFactory.getLogger(AnswerService.class);

    public Answer getCorrectAnswerByQuestionId(Long id) throws NoSuchQuestionException {
        answerServiceValidation.isQuestionExists(answerRepository.getAnswersByQuestionId(id), id, LOGGER);
        return answerRepository.getCorrectAnswerByQuestionId(id);
    }

    public List<Answer> saveAnswer(List<Answer> answers) throws NoQuestionForGivenAnswersException {
        answerServiceValidation.isAnswersHasQuestion(answers, LOGGER);
        answers.sort(Comparator.comparing(Answer::getAnswerText));
        return answerRepository.saveAll(answers);
    }
}