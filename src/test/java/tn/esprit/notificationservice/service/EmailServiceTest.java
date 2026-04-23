package tn.esprit.notificationservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(emailService, "fromEmail", "schoolplateforme@gmail.com");
    }

    @Test
    public void sendWelcomeEmail_Success() {
        // Arrange
        String to = "test@example.com";
        String name = "John Doe";
        String clubName = "Tech Club";

        // Act
        emailService.sendWelcomeEmail(to, name, clubName);

        // Assert
        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender, times(1)).send(messageCaptor.capture());

        SimpleMailMessage sentMessage = messageCaptor.getValue();
        assertNotNull(sentMessage);
        assertEquals("schoolplateforme@gmail.com", sentMessage.getFrom());
        assertNotNull(sentMessage.getTo());
        assertEquals(to, sentMessage.getTo()[0]);
        assertEquals("Welcome to " + clubName + "!", sentMessage.getSubject());
        assertTrue(sentMessage.getText().contains("Hello " + name));
        assertTrue(sentMessage.getText().contains("Your registration for the club '" + clubName + "' has been successfully received"));
    }

    @Test
    public void sendWelcomeEmail_Failure() {
        // Arrange
        String to = "test@example.com";
        String name = "John Doe";
        String clubName = "Tech Club";
        
        doThrow(new MailSendException("Failed to send")).when(mailSender).send(any(SimpleMailMessage.class));

        // Act
        // This should not throw an exception because it's caught in a try/catch block
        emailService.sendWelcomeEmail(to, name, clubName);

        // Assert
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
