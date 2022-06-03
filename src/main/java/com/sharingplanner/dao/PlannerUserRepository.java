package com.sharingplanner.dao;

import com.sharingplanner.config.security.model.UserVo;

import java.util.Optional;

public interface PlannerUserRepository {

    UserVo findUserByUserId(String userId);
}
