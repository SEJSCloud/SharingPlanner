package com.sharingplanner.dao.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sharingplanner.dao.PlannerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PlannerUserRepositoryImpl implements PlannerUserRepository {
    private final JPAQueryFactory queryFactory;

    @Autowired
    public PlannerUserRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

}
