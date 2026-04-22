package tn.esprit.notificationservice.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.notificationservice.dto.ClubRegistrationEvent;
import tn.esprit.notificationservice.service.EmailService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RegistrationConsumerTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private RegistrationConsumer registrationConsumer;

    @Test
    public void consumeRegistrationEvent_ShouldCallEmailService() {
        // Arrange
        ClubRegistrationEvent event = new ClubRegistrationEvent();
        event.setEmail("test@example.com");
        event.setFullName("John Doe");
        event.setClubName("Tech Club");

        // Act
        registrationConsumer.consumeRegistrationEvent(event);

        // Assert
        verify(emailService, times(1)).sendWelcomeEmail(
                "test@example.com",
                "John Doe",
                "Tech Club"
        );
    }
}
