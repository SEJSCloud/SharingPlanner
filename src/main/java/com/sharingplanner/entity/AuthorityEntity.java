package com.sharingplanner.entity;

import lombok.Getter;

import javax.persistence.*;

@SequenceGenerator(
        name = "authority_seq_gen",
        sequenceName = "authority_seq",
        allocationSize = 1)
@Entity
@Table(name = "authority", schema = "plan")
@Getter
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq_gen")
    private Long authoritySeq;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    private String authCd;
}
