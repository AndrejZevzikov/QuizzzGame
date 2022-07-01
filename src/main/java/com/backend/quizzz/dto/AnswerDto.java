package com.backend.quizzz.dto;

import com.backend.quizzz.model.Answer;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Component
public class AnswerDto {

    private Long id;
    private String answerText;

    public AnswerDto(Long id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }

    public AnswerDto() {
    }

    public AnswerDto getAnswerDto(Answer answer) {
        return new AnswerDto(answer.getId(), answer.getAnswerText());
    }

    public List<AnswerDto> getAnswersDto(List<Answer> answers) {
        return answers.stream()
                .map(this::getAnswerDto)
                .collect(Collectors.toList());
    }
}
