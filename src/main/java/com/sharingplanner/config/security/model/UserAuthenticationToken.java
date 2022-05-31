package com.sharingplanner.config.security.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String userEmail;
    private String userName;

    public UserAuthenticationToken(UserDetails user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.userEmail = userEmail;
        this.userName = userName;
    }
}
