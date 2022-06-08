package com.sharingplanner.config.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    ADMIN("01", "전체관리자"),
    GROUP("02", "그룹관리자"),
    USER("03", "일반유저");

    private final String type;
    private final String name;

}
