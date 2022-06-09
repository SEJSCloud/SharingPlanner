package com.sharingplanner.common.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @Email
    private String userEmail;
    @NotBlank
    private String userPw;
}
