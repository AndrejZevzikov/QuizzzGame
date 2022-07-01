package com.backend.quizzz.service.for_controller;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileSenderService {

    public static final String MAIN_PAGE_IMAGE_PATH = "src/main/resources/images/movieMain.jpg";

    public byte[] getMainPicture() throws IOException {
        return Files.readAllBytes(Paths.get(MAIN_PAGE_IMAGE_PATH));
    }
}