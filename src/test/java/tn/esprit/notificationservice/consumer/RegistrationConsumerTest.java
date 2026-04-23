package tn.esprit.notificationservice.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.notificationservice.dto.ClubRegistrationEvent;
import tn.esprit.notificationservice.service.EmailService;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationConsumerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private RegistrationConsumer registrationConsumer;

    @Test
    void testConsumeRegistrationEvent() {
        // Arrange
        ClubRegistrationEvent event = new ClubRegistrationEvent("test@example.com", "Eya Hermi", "Tech Club");

        // Act
        registrationConsumer.consumeRegistrationEvent(event);

        // Assert
        verify(emailService, times(1)).sendWelcomeEmail("test@example.com", "Eya Hermi", "Tech Club");
    }
}
