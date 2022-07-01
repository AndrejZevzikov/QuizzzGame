package com.backend.quizzz.service.for_mail;

import com.backend.quizzz.model.Result;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MailSenderServices {
    public static final String SUBJECT = "Your Results";
    public static final String MESSAGE_TEXT = "Player %s just finished the game score was: %d";
    private JavaMailSender mailSender;
    private Environment environment;

    public void SendEmail(Result result) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(environment.getProperty("$my.mail.from"));
        message.setTo("andrejzevz13@gmail.com");
        message.setSubject(SUBJECT);
        message.setText(String.format(MESSAGE_TEXT, result.getUsername(), result.getScore()));
        mailSender.send(message);
    }
}