package com.sharingplanner.dao.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sharingplanner.config.security.model.UserVo;
import com.sharingplanner.dao.PlannerUserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.sharingplanner.entity.QUserEntity.userEntity;
import static com.sharingplanner.entity.QAuthorityEntity.authorityEntity;

@Repository
public class PlannerUserRepositoryImpl implements PlannerUserRepository {
    private final JPAQueryFactory queryFactory;

    @Autowired
    public PlannerUserRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public UserVo findUserByUserId(String userId) {
        UserVo userVo = Optional.ofNullable(queryFactory.select(Projections.bean(UserVo.class
                , userEntity.userId
                , userEntity.userName
                , userEntity.password
                , userEntity.userEmail
                , authorityEntity.authId.as("authority")))
                .from(userEntity)
                .join(authorityEntity)
                .on(userEntity.eq(authorityEntity.userEntity))
                .where(userEntity.userId.eq(userId))
                .limit(1)
                .fetch()
                .get(0)).orElseThrow(() -> new ServiceException(""));

        return userVo;
    }
}
