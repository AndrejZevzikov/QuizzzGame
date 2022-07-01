package com.backend.quizzz.controller;

import com.backend.quizzz.dto.AnswerDto;
import com.backend.quizzz.exception.NoQuestionForGivenAnswersException;
import com.backend.quizzz.exception.NoSuchQuestionException;
import com.backend.quizzz.model.Answer;
import com.backend.quizzz.service.for_controller.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "answer")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:83","http://front-app:80"})
@AllArgsConstructor
public class AnswerController {

    private final AnswerDto answerDto;
    private final AnswerService answerService;

    @GetMapping("/correct_{id}")
    ResponseEntity<AnswerDto> getCorrectAnswer(@PathVariable(name = "id") Long id) throws NoSuchQuestionException {
        return new ResponseEntity<>(answerDto.getAnswerDto(answerService.getCorrectAnswerByQuestionId(id)), HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<List<AnswerDto>> saveAnswers(@RequestBody List<Answer> answers) throws NoQuestionForGivenAnswersException {
        return new ResponseEntity<>(answerDto.getAnswersDto(answerService.saveAnswer(answers)),HttpStatus.OK);
    }


}
