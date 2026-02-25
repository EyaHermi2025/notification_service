package tn.esprit.notificationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String to, String name, String clubName) {
        log.info("Sending welcome email to {} for club {}", to, clubName);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("schoolplateforme@gmail.com");
            message.setTo(to);
            message.setSubject("Welcome to " + clubName + "!");
            message.setText("Hello " + name + ",\n\n" +
                    "Your registration for the club '" + clubName + "' has been successfully received!\n" +
                    "We are excited to have you with us.\n\n" +
                    "Best regards,\n" +
                    "School Platform Team");

            mailSender.send(message);
            log.info("Email sent successfully to {}", to);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", to, e.getMessage());
        }
    }
}
