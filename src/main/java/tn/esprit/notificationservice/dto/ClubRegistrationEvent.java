package tn.esprit.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubRegistrationEvent {
    private String email;
    private String fullName;
    private String clubName;
}
