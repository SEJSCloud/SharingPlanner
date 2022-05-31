package com.sharingplanner.config.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomUserDetails extends User {
    private String userId;
    private String userName;
    private String userEmail;

    public CustomUserDetails(UserVo userVo) {
        super(userVo.getUserId(), userVo.getPassword(), userVo.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        this.userId = userVo.getUserId();
        this.userName = userVo.getUserName();
        this.userEmail = userVo.getUserEmail();
    }


    public CustomUserDetails(String userId, String userPassword, List<GrantedAuthority> authorities) {
        super(userId, userPassword, authorities);
    }
}


