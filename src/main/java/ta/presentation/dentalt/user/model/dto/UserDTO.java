package ta.presentation.dentalt.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ta.presentation.dentalt.user.model.enums.Role;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private LocalDateTime joinedOn;
}
