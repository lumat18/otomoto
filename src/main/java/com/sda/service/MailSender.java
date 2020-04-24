package com.sda.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MailSender {

    private static MailSender mailSender;

    public static MailSender getInstance() {
        if (mailSender == null) {
            mailSender = new MailSender();

        }
        return mailSender;
    }

    public void sendMail(String recipient) throws Exception {
        log.info("Start preparation of mail message" +
                "");
        final Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "photoarchapp@gmail.com";
        String password = "photo123!";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient);

        Transport.send(message);
        log.info("Message sent successfully");
    }

    private Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        final Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Otomoto - Registration link");
            message.setText("Hello Sir, sitting infront of yourself myself Rakhesh Maszafsdfsddi");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

}
