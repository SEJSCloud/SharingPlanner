package com.sharingplanner.common.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SequenceGenerator(
        name = "group_seq_gen",
        sequenceName = "group_seq",
        schema = "plan",
        allocationSize = 1)
@Entity
@Table(name = "group", schema = "plan")
@Getter
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq_gen")
    private Long groupSeq;
    private String groupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupEntity")
    private List<UserEntity> userEntityList = new ArrayList<>();
}
