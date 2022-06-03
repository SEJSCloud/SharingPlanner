package com.sharingplanner.config.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
    private String userId;
    private String userName;
    private String password;
    private String userEmail;
    private String authority;
}
