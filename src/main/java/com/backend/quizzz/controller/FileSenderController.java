package com.backend.quizzz.controller;

import com.backend.quizzz.service.for_controller.FileSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping(path = "files")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:83", "http://front-app:80"})
@AllArgsConstructor
public class FileSenderController {
    FileSenderService fileSenderService;

    @GetMapping
    public ResponseEntity<byte[]> getMainPicture() throws IOException {
        return new ResponseEntity<>(fileSenderService.getMainPicture(), HttpStatus.OK);
    }
}
