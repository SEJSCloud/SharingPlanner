package com.sharingplanner.config.security.service;

import com.sharingplanner.config.security.model.Authority;
import com.sharingplanner.config.security.model.CustomUserDetails;
import com.sharingplanner.dao.PlannerUserRepository;
import com.sharingplanner.dao.UserRepository;
import com.sharingplanner.entity.AuthorityEntity;
import com.sharingplanner.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PlannerUserRepository plannerUserRepository;

    @Override
    @Transactional
    public User loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(""));
        List<SimpleGrantedAuthority> authorityList = userEntity.getAuthorityEntityList()
                .stream()
                .map(authorityEntity -> new SimpleGrantedAuthority(Authority.getAuthorityNameByAuthCd(authorityEntity.getAuthCd())))
                .collect(Collectors.toList());

        return new CustomUserDetails(userEntity, authorityList);
    }
}
