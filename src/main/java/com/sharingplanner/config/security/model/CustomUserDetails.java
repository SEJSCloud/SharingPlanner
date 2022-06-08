package com.sharingplanner.config.security.model;

import com.sharingplanner.common.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@Setter
public class CustomUserDetails extends User {
    private String userId;
    private String userName;
    private String userEmail;

    public CustomUserDetails(UserEntity userEntity, List<SimpleGrantedAuthority> authorityList) {
        super(userEntity.getUserId(), userEntity.getPassword(), authorityList);
        this.userId = userEntity.getUserId();
        this.userName = userEntity.getUserName();
        this.userEmail = userEntity.getUserEmail();
    }


    public CustomUserDetails(String userId, String userPassword, List<GrantedAuthority> authorities) {
        super(userId, userPassword, authorities);
    }
}


