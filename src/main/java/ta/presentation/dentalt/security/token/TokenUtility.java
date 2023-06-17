package ta.presentation.dentalt.security.token;

import org.springframework.security.core.context.SecurityContextHolder;

public class TokenUtility {

    private TokenUtility() {
        super();
    }

    public static TokenDetails getDetails() {
        return (TokenDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
