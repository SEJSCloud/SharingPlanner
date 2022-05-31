package com.sharingplanner.config.security.service;

import com.sharingplanner.config.security.model.CustomUserDetails;
import com.sharingplanner.dao.PlannerUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PlannerUserRepository plannerUserRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return plannerUserRepository.findUserByUserId(userId).map(CustomUserDetails::new).orElseThrow(() -> new ServiceException(""));
    }
}
