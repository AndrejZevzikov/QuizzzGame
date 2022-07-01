package com.backend.quizzz.controller;

import com.backend.quizzz.exception.InvalidScoreForSavingException;
import com.backend.quizzz.model.Result;
import com.backend.quizzz.service.for_controller.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "results")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:83", "http://front-app:80"})
@AllArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @GetMapping("/top={count}")
    public ResponseEntity<List<Result>> getTopResultByGivenNumber(@PathVariable(name = "count") int count) {
        return new ResponseEntity<>(resultService.getTopResultByGivenNumber(count), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Result>> getAllResults() {
        return new ResponseEntity<>(resultService.getAllResults(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> saveResult(@RequestBody Result result) throws InvalidScoreForSavingException {
        return new ResponseEntity<>(resultService.saveResult(result), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable(name = "id") Long id, @RequestBody Result result) {
        return new ResponseEntity<>(resultService.updateResultById(id, result), HttpStatus.OK);
    }


}
