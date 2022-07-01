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
public class QuestionDto {

    private Long id;
    private String questionText;

    public QuestionDto(Long id, String text) {
        this.id = id;
        this.questionText = text;
    }

    public QuestionDto() {
    }

    public QuestionDto getQuestionDto(Question question) {
        return new QuestionDto(question.getId(),
                question.getQuestionText());
    }

    public List<QuestionDto> getAllQuestionDto(List<Question> questions) {
        return questions.stream()
                .map(this::getQuestionDto)
                .collect(Collectors.toList());
    }
}
