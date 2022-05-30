package config.security.provider;

import config.security.model.CustomUserDetail;
import config.security.model.UserAuthenticationToken;
import config.security.model.UserConstant;
import config.security.model.entity.UserEntity;
import config.security.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserAuthProvider {
    @Autowired
    private JWTUtil jwtUtil;

    public Authentication getAuthentication(UserEntity entity){
        return new UserAuthenticationToken(new CustomUserDetail(entity.getUserName(), entity.getUserId(), null));
    }
    
    public Authentication getAuthenticationFromJwt(final String token) {
        Claims claims = jwtUtil.getData(token);

        String userId = claims.get(UserConstant.USER_ID, String.class);
        String userName = claims.get(UserConstant.USER_NAME, String.class);
        List<String> roles = claims.get(UserConstant.ROLE, List.class);
        if (roles == null) {
            roles = Collections.emptyList();
        }
        List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());


        return new UserAuthenticationToken(new CustomUserDetail(userName, userId, authorities));
    }
}
