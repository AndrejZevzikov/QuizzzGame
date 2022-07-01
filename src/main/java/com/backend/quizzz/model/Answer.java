package com.backend.quizzz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answerText;
    private Boolean isCorrect;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question question;
}