package tn.esprit.notificationservice.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tn.esprit.notificationservice.dto.ClubRegistrationEvent;
import tn.esprit.notificationservice.service.EmailService;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "registration-topic", groupId = "notification-group")
    public void consumeRegistrationEvent(ClubRegistrationEvent event) {
        log.info("Received club registration event from Kafka: {}", event);
        emailService.sendWelcomeEmail(event.getEmail(), event.getFullName(), event.getClubName());
    }
}
