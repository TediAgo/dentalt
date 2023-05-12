package ta.presentation.dentalt.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAppointmentDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
