package com.sharingplanner.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planner_user", schema = "plan")
@Getter
public class UserEntity {

    @Id
    private String userId;
    private String userName;
    private String userEmail;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<AuthorityEntity> authorityEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupEntity groupEntity;
}
