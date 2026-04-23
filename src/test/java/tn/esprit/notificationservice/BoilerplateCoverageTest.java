package tn.esprit.notificationservice;

import org.junit.jupiter.api.Test;
import tn.esprit.notificationservice.dto.ClubRegistrationEvent;

import static org.junit.jupiter.api.Assertions.*;

class BoilerplateCoverageTest {

    @Test
    void testClubRegistrationEventBoilerplate() {
        ClubRegistrationEvent event = new ClubRegistrationEvent();
        event.setEmail("test@example.com");
        event.setFullName("Eya Hermi");
        event.setClubName("Tech Club");

        assertEquals("test@example.com", event.getEmail());
        assertEquals("Eya Hermi", event.getFullName());
        assertEquals("Tech Club", event.getClubName());
        assertNotNull(event.toString());
        
        ClubRegistrationEvent event2 = new ClubRegistrationEvent("test@example.com", "Eya Hermi", "Tech Club");
        assertEquals(event, event2);
        assertEquals(event.hashCode(), event2.hashCode());
    }

    @Test
    void testApplicationMain() {
        // Simple call to exercise the application class line
        assertNotNull(new NotificationServiceApplication());
    }
}
