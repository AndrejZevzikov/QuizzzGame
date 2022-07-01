package com.backend.quizzz.service.for_controller;

import com.backend.quizzz.exception.InvalidParameterForDifficultyFactory;
import com.backend.quizzz.exception.InvalidUrlForQuestionImage;
import com.backend.quizzz.exception.NoMoreNewQuestionsException;
import com.backend.quizzz.exception.NoTextInQuestion;
import com.backend.quizzz.factory.DifficultFactory;
import com.backend.quizzz.model.Difficulty;
import com.backend.quizzz.model.Question;
import com.backend.quizzz.repository.QuestionRepository;
import com.backend.quizzz.validation.QuestionServiceValidation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final DifficultFactory difficultFactory;
    private final QuestionServiceValidation questionServiceValidation;
    private static Logger LOGGER = LoggerFactory.getLogger(QuestionService.class);

    //TODO parasyti testus
    public Question findQuestionByCurrentScore(int score, List<Long> previousQuestions)
            throws InvalidParameterForDifficultyFactory {
        List<Question> questions = questionRepository.findQuestionsWithoutGivenIdsAndByCurrentScore(
                previousQuestions,
                difficultFactory.getDifficultyFromScore(score));
        try {
            questionServiceValidation.hasAnyNewQuestions(questions);
        } catch (NoMoreNewQuestionsException e) {
            LOGGER.warn(e.getMessage());
            questions = questionRepository.findQuestionByGivenDifficulty(difficultFactory.getDifficultyFromScore(score));
        }
        return getRandomQuestion(questions);
    }

    public Question findFirstQuestion() {
        List<Question> questions = questionRepository.findQuestionByGivenDifficulty(Difficulty.EASY);
        return getRandomQuestion(questions);
    }

    public Question saveQuestionFromForm(Question question, String difficulty)
            throws NoTextInQuestion, InvalidParameterForDifficultyFactory {
        questionServiceValidation.validateNewQuestion(question);

        try {
            questionServiceValidation.validateUrl(question);
        } catch (InvalidUrlForQuestionImage e) {
            question.setImageLink(null);
        }

        return questionRepository.save(Question.builder()
                .questionText(question.getQuestionText())
                .difficulty(difficultFactory.getDifficultyFromString(difficulty))
                .imageLink(question.getImageLink())
                .build());
    }

    public void saveQuestions(List<Question> questions) throws NoTextInQuestion, InvalidParameterForDifficultyFactory {
        questionServiceValidation.validateNewQuestions(questions);
        questionRepository.saveAll(questions);
    }

    private Question getRandomQuestion(List<Question> questions) {
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }
}