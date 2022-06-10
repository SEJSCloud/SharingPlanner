package com.sharingplanner.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planner_user", schema = "plan")
@NoArgsConstructor
@Getter
public class UserEntity extends BaseEntity{

    @Id
    private String userId;
    private String userName;
    private String userEmail;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<AuthorityEntity> authorityEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupEntity groupEntity;

    public UserEntity(String userId, String userName, String userEmail, String password){
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
    }
}
