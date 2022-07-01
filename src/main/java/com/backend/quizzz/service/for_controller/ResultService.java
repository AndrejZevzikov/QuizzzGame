package com.backend.quizzz.service.for_controller;

import com.backend.quizzz.exception.InvalidGivenInput;
import com.backend.quizzz.exception.InvalidScoreForSavingException;
import com.backend.quizzz.model.Result;
import com.backend.quizzz.repository.ResultRepository;
import com.backend.quizzz.service.for_mail.MailSenderServices;
import com.backend.quizzz.validation.ResultServiceValidation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResultService {

    public static final int TOP_TWENTY = 20;
    private final ResultRepository resultRepository;
    private final ResultServiceValidation validator;
    private final MailSenderServices mailSender;
    private static Logger LOGGER = LoggerFactory.getLogger(ResultService.class);

    public List<Result> getTopResultByGivenNumber(int givenNumber) {
        try {
            validator.validateInputForTopResultList(givenNumber);
        } catch (InvalidGivenInput e) {
            LOGGER.info(e.getMessage());
            return resultRepository.findTopResultsByGivenCount(TOP_TWENTY);
        }
        return resultRepository.findTopResultsByGivenCount(givenNumber);
    }

    public Result saveResult(Result result) throws InvalidScoreForSavingException {
        validator.isScoreValidForSaving(result);
        Result resultForSaving = resultRepository.save(result);
        LOGGER.info("start sending email");
//        mailSender.SendEmail(result);
        LOGGER.info("email send");
        return resultForSaving;
    }

    @Cacheable(value = "results")
    public List<Result> getAllResults() {
        System.out.println("Im not from redis");
        return resultRepository.findAll();
    }

    @CachePut(value = "results", key = "#id")//TODO neveikia
    public Result updateResultById(Long id, Result result) {
        Result resultToChange = resultRepository.findById(id).get();
        resultToChange.setScore(result.getScore());
        return resultRepository.save(resultToChange);
    }
}