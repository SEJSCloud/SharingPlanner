package com.sharingplanner.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "authority", schema = "plan")
@Getter
public class AuthorityEntity {
    @Id
    private String authId;
    private String authName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "authorityEntity")
    private UserEntity userEntity;
}
