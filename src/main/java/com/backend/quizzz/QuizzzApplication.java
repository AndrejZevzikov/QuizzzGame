package com.backend.quizzz;

import com.backend.quizzz.fileReader.ExcelReaderImpl;
import com.backend.quizzz.mapper.QuestionMapper;
import com.backend.quizzz.model.Question;
import com.backend.quizzz.service.for_controller.QuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableCaching
public class QuizzzApplication {

    public static final String PATH_FOR_PREPARED_QUESTIONS = "src/main/resources/files/Questions.xlsx";

    public static void main(String[] args) {
        SpringApplication.run(QuizzzApplication.class, args);
    }

    @Bean
    CommandLineRunner run(final ExcelReaderImpl excelReader, final QuestionMapper mapper, final QuestionService questionService) {
        return args -> {
            Map<Integer, List<String>> questions = excelReader.getFileContent(PATH_FOR_PREPARED_QUESTIONS);
            List<Question> questionObjList = mapper.mapStringsFromGivenDataToQuestionObj(questions);
            questionService.saveQuestions(questionObjList);
        };
    }
}