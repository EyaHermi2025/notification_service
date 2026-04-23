package tn.esprit.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendWelcomeEmail(String to, String name, String clubName) {
        log.info("Sending welcome email to {} for club {}", to, clubName);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
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
