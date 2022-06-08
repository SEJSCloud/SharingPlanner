package com.sharingplanner.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String userId;
    private String userName;
    private String userEmail;
    private String userPw;
}
