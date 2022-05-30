package config.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public UserAuthenticationToken(UserDetails principal) {
        super(principal, principal.getPassword(), principal.getAuthorities());
    }
}
