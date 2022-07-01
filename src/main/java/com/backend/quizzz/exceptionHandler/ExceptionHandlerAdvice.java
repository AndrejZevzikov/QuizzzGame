package com.backend.quizzz.exceptionHandler;

import com.backend.quizzz.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InvalidScoreForSavingException.class)
    public ResponseEntity handleInvalidScoreForSavingException(InvalidScoreForSavingException e) {
        // log exception
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(e.getMessage());
    }

    @ExceptionHandler(NoSuchQuestionException.class)
    public ResponseEntity handleNoSuchQuestionException(NoSuchQuestionException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(NoQuestionForGivenAnswersException.class)
    public ResponseEntity handleNoQuestionForGivenAnswerException(NoQuestionForGivenAnswersException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(InvalidParameterForDifficultyFactory.class)
    public ResponseEntity handleInvalidParameterForDifficultyFactoryException(InvalidParameterForDifficultyFactory e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(NoTextInQuestion.class)
    public ResponseEntity handleNoTextInQuestion(NoTextInQuestion e) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(e.getMessage());
    }
}
