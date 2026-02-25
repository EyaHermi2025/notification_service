package tn.esprit.notificationservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tn.esprit.notificationservice.dto.ClubRegistrationEvent;
import tn.esprit.notificationservice.service.EmailService;

@Service
public class RegistrationConsumer {

    private static final Logger log = LoggerFactory.getLogger(RegistrationConsumer.class);
    private final EmailService emailService;

    public RegistrationConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "registration-topic", groupId = "notification-group")
    public void consumeRegistrationEvent(ClubRegistrationEvent event) {
        log.info("Received club registration event from Kafka: {}", event);
        emailService.sendWelcomeEmail(event.getEmail(), event.getFullName(), event.getClubName());
    }
}
