package com.sharingplanner.dao;

import com.sharingplanner.config.security.model.UserVo;

import java.util.Optional;

public interface PlannerUserRepository {

    Optional<UserVo> findUserByUserId(String userId);
}
