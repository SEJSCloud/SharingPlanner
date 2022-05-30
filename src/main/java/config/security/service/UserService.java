package config.security.service;

import config.security.dao.UserRepository;
import config.security.model.CustomUser;
import config.security.model.UserConstant;
import config.security.model.entity.UserEntity;
import config.security.provider.UserAuthProvider;
import config.security.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserAuthProvider userAuthProvider;

    public String login(CustomUser user){
        try {
            SecurityContextHolder.getContext().setAuthentication(userAuthProvider.getAuthentication(userRepository.findById(user.getUserId()).orElseThrow(() -> new InternalAuthenticationServiceException("유저가 없습니다."))));
            //        SecurityContextHolder.getContext().setAuthentication(userAuthProvider.getAuthenticationFromJwt(createToken(user.getUserId()));
        }catch (InternalAuthenticationServiceException serviceException) {
            return "fail";
        }
        return "success";
    }

    private String createToken(String userId) throws InternalAuthenticationServiceException{
        UserEntity entity = userRepository.findById(userId).orElseThrow(() -> new InternalAuthenticationServiceException("유저가 없습니다."));
        CustomUser user = new CustomUser();
        return jwtUtil.createToken(user.getUserId(), makeClaims(user));
    }

    private Map<String, Object> makeClaims(CustomUser user) {
        Map<String, Object> data = new HashMap<>();
        data.put(UserConstant.ROLE, user.getAuthorities());
        data.put(UserConstant.USER_ID, user.getUserId());
        data.put(UserConstant.USER_NAME, user.getUserName());
        return data;
    }
}
