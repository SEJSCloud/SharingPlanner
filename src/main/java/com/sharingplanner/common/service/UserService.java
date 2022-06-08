package com.sharingplanner.common.service;

import com.sharingplanner.common.dao.AuthorityRepository;
import com.sharingplanner.common.dao.UserRepository;
import com.sharingplanner.common.entity.AuthorityEntity;
import com.sharingplanner.common.entity.UserEntity;
import com.sharingplanner.common.model.UserRequest;
import com.sharingplanner.config.security.enums.Authority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(UserRequest userRequest){
        UserEntity userEntity = userRepository.save(
                new UserEntity(
                        userRequest.getUserId(),
                        userRequest.getUserName(),
                        userRequest.getUserEmail(),
                        passwordEncoder.encode(userRequest.getUserPw())
                )
        );

        authorityRepository.save(new AuthorityEntity(userEntity, Authority.USER.getAuthCd()));
    }
}
