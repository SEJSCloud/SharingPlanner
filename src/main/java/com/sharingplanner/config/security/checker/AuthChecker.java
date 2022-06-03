package com.sharingplanner.config.security.checker;

import com.sharingplanner.config.security.model.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.file.AccessDeniedException;

@Component
@Slf4j
public class AuthChecker {
    public boolean isAuthenticated() {
        try {
            return StringUtils.hasLength(getUserDetail().getUserId());
        }catch (AccessDeniedException | NullPointerException npe){
            log.error(npe.getMessage());
            return false;
        }
    }

    private CustomUserDetails getUserDetail() throws AccessDeniedException, NullPointerException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails user;
        if (principal instanceof CustomUserDetails) {
            user = (CustomUserDetails) principal;
        } else {
            throw new AccessDeniedException("사용자 정보가 없습니다.");
        }
        return user;
    }
}
