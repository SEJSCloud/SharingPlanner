package com.sharingplanner.config.security.dao;

import com.sharingplanner.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
