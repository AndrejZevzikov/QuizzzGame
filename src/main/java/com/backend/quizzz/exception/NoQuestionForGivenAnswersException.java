package com.backend.quizzz.exception;

public class NoQuestionForGivenAnswersException extends Exception {

    public NoQuestionForGivenAnswersException(String message) {
        super(message);
    }
}
