package ta.presentation.dentalt.security.token;

import lombok.Data;
import ta.presentation.dentalt.user.model.enums.Role;

@Data
public final class TokenDetails {

    private final String id;
    private final String email;
    private final Role role;


}
