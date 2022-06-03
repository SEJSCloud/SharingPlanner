package com.sharingplanner.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "planner_user", schema = "plan")
@Getter
public class UserEntity {

    @Id
    private String userId;
    private String userName;
    private String userEmail;
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    private AuthorityEntity authorityEntity;
}
