package com.backend.quizzz.validation;

import com.backend.quizzz.exception.InvalidGivenInput;
import com.backend.quizzz.exception.InvalidScoreForSavingException;
import com.backend.quizzz.model.Result;
import org.springframework.stereotype.Component;

@Component
public class ResultServiceValidation {

    public static final String INVALID_INPUT_THEN_TRY_TO_GET_TOP_RESULTS
            = "Invalid input then try to get top results, input was: ";
    public static final int ONE = 1;

    public void validateInputForTopResultList(int givenNumber) throws InvalidGivenInput {
        if (givenNumber < ONE) throw new InvalidGivenInput(INVALID_INPUT_THEN_TRY_TO_GET_TOP_RESULTS + givenNumber);
    }

    public void isScoreValidForSaving(Result result) throws InvalidScoreForSavingException {
        if (result.getScore() < ONE)
            throw new InvalidScoreForSavingException("Given Score is Invalid can't save it, score is: " + result.getScore());
    }

}