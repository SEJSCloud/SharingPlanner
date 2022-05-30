package config.security.checker;

import config.security.model.CustomUserDetail;
import config.security.model.UserConstant;
import config.security.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthChecker {
    private final JWTUtil jwtUtil;

    public boolean isAuthenticated(HttpServletRequest request) {
        try {
            return getUserDetail().getUserId().equals(jwtUtil.getData(request.getHeader("Authorization")).get(UserConstant.USER_ID));
        }catch (AccessDeniedException ade){
            log.error(ade.getMessage());
            return false;
        }
    }

    private CustomUserDetail getUserDetail() throws AccessDeniedException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetail user;
        if (principal instanceof CustomUserDetail) {
            user = (CustomUserDetail) principal;
        } else {
            throw new AccessDeniedException("사용자 정보가 없습니다.");
        }
        return user;
    }
}
