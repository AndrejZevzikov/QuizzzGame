package com.backend.quizzz.controller;

import com.backend.quizzz.dto.QuestionAnswerDto;
import com.backend.quizzz.dto.QuestionDto;
import com.backend.quizzz.exception.InvalidParameterForDifficultyFactory;
import com.backend.quizzz.exception.NoTextInQuestion;
import com.backend.quizzz.model.Question;
import com.backend.quizzz.repository.QuestionRepository;
import com.backend.quizzz.service.for_controller.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "question")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:83", "http://front-app:80"})
@AllArgsConstructor
public class QuestionController {

    private final QuestionAnswerDto questionAnswerDto;
    private final QuestionDto questionDto;
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;


    @GetMapping("/random_{score}/pq")
    public ResponseEntity<QuestionAnswerDto> getQuestionByGivenScore(
            @PathVariable(name = "score") int score,
            @RequestParam(name = "previousQuestions") List<Long> previousQuestions) throws InvalidParameterForDifficultyFactory {
        return new ResponseEntity<>(questionAnswerDto.getQuestionDto(
                questionService.findQuestionByCurrentScore(score, previousQuestions)), HttpStatus.OK);
    }

    @GetMapping("/game_start")
    public ResponseEntity<QuestionAnswerDto> getFirstQuestion() {
        return new ResponseEntity<>(questionAnswerDto.getQuestionDto(questionService.findFirstQuestion()), HttpStatus.OK);
    }


    @GetMapping //TODO pataisyti padaryta testo sumetimais
    public ResponseEntity<List<QuestionAnswerDto>> getAllQuestions() {
        return new ResponseEntity<>(questionAnswerDto.getAllQuestionDto(questionRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping("/add_{difficulty}")
    public ResponseEntity<QuestionDto> saveQuestion(
            @RequestBody Question question,
            @PathVariable(name = "difficulty") String difficulty)
            throws NoTextInQuestion, InvalidParameterForDifficultyFactory {
        System.out.println(question.getImageLink());
        return new ResponseEntity<>(questionDto.getQuestionDto(questionService.saveQuestionFromForm(question, difficulty)), HttpStatus.OK);
    }
}
