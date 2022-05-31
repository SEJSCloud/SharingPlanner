package com.sharingplanner.dao.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sharingplanner.config.security.model.UserVo;
import com.sharingplanner.dao.PlannerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.sharingplanner.entity.QUserEntity.userEntity;

@Repository
public class PlannerUserRepositoryImpl implements PlannerUserRepository {
    private final JPAQueryFactory queryFactory;

    @Autowired
    public PlannerUserRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<UserVo> findUserByUserId(String userId) {
        return Optional.ofNullable(queryFactory.select(Projections.bean(UserVo.class
                , userEntity.userId
                , userEntity.userName
                , userEntity.password
                , userEntity.userEmail))
                .from(userEntity)
                .where(userEntity.userId.eq(userId))
                .limit(1)
                .fetch()
        .get(0));
    }
}
