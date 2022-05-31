package com.sharingplanner.config.security.service;

import com.sharingplanner.config.security.dao.UserRepository;
import com.sharingplanner.config.security.model.CustomUserDetails;
import com.sharingplanner.config.security.model.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findById(userId).map(user -> new CustomUserDetails(new UserVo())).get();
    }
}
