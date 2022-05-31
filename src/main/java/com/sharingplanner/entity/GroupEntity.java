package com.sharingplanner.entity;

import lombok.Getter;

import javax.persistence.*;

@SequenceGenerator(
        name = "group_seq",
        sequenceName = "group_seq",
        allocationSize = 1)
@Entity
@Table(name = "group", schema = "plan")
@Getter
public class GroupEntity {

    @Id
    @GeneratedValue
    private Long groupSeq;
    private String groupName;
}
