package com.sharingplanner.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SequenceGenerator(
        name = "authority_seq_gen",
        sequenceName = "authority_seq",
        schema = "plan",
        allocationSize = 1)
@Entity
@Table(name = "authority", schema = "plan")
@NoArgsConstructor
@Getter
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq_gen")
    private Long authoritySeq;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    private String authCd;

    public AuthorityEntity(UserEntity userEntity, String authCd){
        this.userEntity = userEntity;
        this.authCd = authCd;
    }
}
