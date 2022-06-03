package com.sharingplanner.config.security.provider;

import com.sharingplanner.config.security.model.CustomUserDetails;
import com.sharingplanner.config.security.model.UserAuthenticationToken;
import com.sharingplanner.config.security.model.UserConstant;
import com.sharingplanner.config.security.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component("authProvider")
@RequiredArgsConstructor
public class UserAuthProvider implements AuthenticationProvider {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(userId);
        if (user == null) {
            throw new BadCredentialsException("유저가 확인 불가");
        }

        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비말번호 불잂치");
        }

        return new UserAuthenticationToken(user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
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


        return new UserAuthenticationToken(new CustomUserDetails(userName, userId, authorities));
    }
}
