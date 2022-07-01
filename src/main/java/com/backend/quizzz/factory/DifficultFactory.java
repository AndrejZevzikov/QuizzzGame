package com.backend.quizzz.factory;

import com.backend.quizzz.exception.InvalidParameterForDifficultyFactory;
import com.backend.quizzz.model.Difficulty;
import org.springframework.stereotype.Component;

import static com.backend.quizzz.model.Difficulty.*;

@Component
public class DifficultFactory {

    public static final String CAN_T_ASSIGN_DIFFICULTY_BY_GIVEN_STRING = "Can't assign difficulty for given parameter: ";
    public static final String CAN_T_ASSIGN_DIFFICULTY_BY_GIVEN_SCORE = "Can't assign difficulty by given score: ";

    public Difficulty getDifficultyFromScore(int score) throws InvalidParameterForDifficultyFactory {
        if (score >= 20) return HARD;
        if (score >= 10) return MEDIUM;
        if (score >= 0) return EASY;
        throw new InvalidParameterForDifficultyFactory(CAN_T_ASSIGN_DIFFICULTY_BY_GIVEN_SCORE + score);
    }

    public Difficulty getDifficultyFromString(String difficulty) throws InvalidParameterForDifficultyFactory {
        if (difficulty.equalsIgnoreCase(EASY.toString())) return EASY;
        if (difficulty.equalsIgnoreCase(MEDIUM.toString())) return MEDIUM;
        if (difficulty.equalsIgnoreCase(HARD.toString())) return HARD;
        throw new InvalidParameterForDifficultyFactory(CAN_T_ASSIGN_DIFFICULTY_BY_GIVEN_STRING + difficulty);
    }
}
