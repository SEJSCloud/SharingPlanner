package com.sharingplanner.config.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String userEmail;
    private String userName;

    public UserAuthenticationToken(CustomUserDetails user) {
        super(user, user.getPassword(), user.getAuthorities());
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
    }
}
