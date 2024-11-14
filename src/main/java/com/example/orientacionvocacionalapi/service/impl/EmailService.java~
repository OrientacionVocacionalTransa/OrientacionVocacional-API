package com.example.orientacionvocacionalapi.service.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;





    public void sendHtmlEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom("jeisson12aaron@gmail.com");

            mailSender.send(message);
        } catch (MessagingException | MailException e) {
            e.printStackTrace();
        }
    }



    public void sendVerificationEmail(String toEmail, String verificationCode) {
        String subject = "Verificación de correo electrónico";
        String body = "Hola,\n\n" +
                "Gracias por registrarte. Por favor, usa el siguiente código para verificar tu correo electrónico:\n\n" +
                "Código de verificación: " + verificationCode + "\n\n" +
                "Si no solicitaste este registro, ignora este mensaje.\n\n" +
                "Gracias.";

        sendHtmlEmail(toEmail, subject, body);
    }


}

