package com.backend.quizzz.mapper;

import com.backend.quizzz.exception.InvalidParameterForDifficultyFactory;
import com.backend.quizzz.exception.InvalidUrlForQuestionImage;
import com.backend.quizzz.factory.DifficultFactory;
import com.backend.quizzz.model.Answer;
import com.backend.quizzz.model.Question;
import com.backend.quizzz.validation.QuestionServiceValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class QuestionMapper {

    private DifficultFactory difficultFactory;
    private QuestionServiceValidation validator;

    public List<Question> mapStringsFromGivenDataToQuestionObj(Map<Integer, List<String>> dataFromFile)
            throws InvalidParameterForDifficultyFactory {

        List<Question> questionsToReturn = new ArrayList<>();

        for (Integer row : dataFromFile.keySet()) {
            if (row == 1) continue;
            Question question = getQuestionFromDataList(dataFromFile.get(row));
            mapQuestionForAnswers(question);
            try {
                validator.validateUrl(question);
            } catch (InvalidUrlForQuestionImage e) {
                question.setImageLink(null);
            }
            questionsToReturn.add(question);
        }
        return questionsToReturn;
    }

    private Question getQuestionFromDataList(List<String> givenRowData) throws InvalidParameterForDifficultyFactory {
        List<Answer> answers = mapAnswerFromGivenData(givenRowData);
        return Question.builder()
                .questionText(givenRowData.get(0))
                .difficulty(difficultFactory.getDifficultyFromString(givenRowData.get(1)))
                .imageLink(givenRowData.get(2))
                .answers(answers)
                .build();
    }

    private List<Answer> mapAnswerFromGivenData(List<String> givenData) {
        List<Answer> answers = new ArrayList<>();
        answers.add(Answer.builder().answerText(givenData.get(3)).isCorrect(Boolean.TRUE).build());
        answers.add(Answer.builder().answerText(givenData.get(4)).isCorrect(Boolean.FALSE).build());
        answers.add(Answer.builder().answerText(givenData.get(5)).isCorrect(Boolean.FALSE).build());
        answers.add(Answer.builder().answerText(givenData.get(6)).isCorrect(Boolean.FALSE).build());
        answers.sort(Comparator.comparing(Answer::getAnswerText));
        return answers;
    }

    private void mapQuestionForAnswers(Question question) {
        question.getAnswers().forEach(answer -> answer.setQuestion(question));
    }
}
