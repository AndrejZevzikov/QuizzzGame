package com.backend.quizzz.dto;

import com.backend.quizzz.model.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Component
public class QuestionAnswerDto {

    private Long id;
    private String questionText;
    private List<AnswerDto> answers;
    private String difficulty;
    private String imageLink;

    public QuestionAnswerDto(Long id, String text, List<AnswerDto> answers, String imageLink) {
        this.id = id;
        this.questionText = text;
        this.answers = answers;
        this.imageLink = imageLink;
    }

    public QuestionAnswerDto() {
    }

    public QuestionAnswerDto getQuestionDto(Question question) {
        AnswerDto answerDto = new AnswerDto();
        return new QuestionAnswerDto(question.getId(),
                question.getQuestionText(),
                answerDto.getAnswersDto(question.getAnswers()), question.getImageLink());
    }

    public List<QuestionAnswerDto> getAllQuestionDto(List<Question> questions) {
        return questions.stream()
                .map(this::getQuestionDto)
                .collect(Collectors.toList());
    }
}
