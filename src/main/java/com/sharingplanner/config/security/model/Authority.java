package com.sharingplanner.config.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {

   ADMIN("admin", "관리자"),
    USER("user", "일반유저"),
    LEADER("leader", "그룹장");

    private final String authCd;
    private final String authName;


    public static String getAuthorityNameByAuthCd(String authCd){
        for (Authority authority : Authority.values()) {
            if (authority.getAuthCd().equals(authCd)){
                return authority.getAuthName();
            }
        }
        return Authority.USER.getAuthName();
    }
}
