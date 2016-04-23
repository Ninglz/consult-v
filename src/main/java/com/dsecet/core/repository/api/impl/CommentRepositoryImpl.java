package com.dsecet.core.repository.api.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.dsecet.core.domain.record.Comment;
import com.dsecet.core.repository.api.CommentRepository;
import com.dsecet.foundation.model.NLZPage;
import com.dsecet.foundation.model.NLZPageImpl;
import com.dsecet.util.JpaUtils;

@Repository
public class CommentRepositoryImpl extends SimpleJpaRepository<Comment, Long> implements CommentRepository{

	
	private EntityManager entityManager;
	
    @Autowired
    public CommentRepositoryImpl(EntityManager entityManager) {
        super(Comment.class, entityManager);
        this.entityManager = entityManager;
    }

	@Override
	public NLZPage<Comment> findByCondition(Long expertsId, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> transactionRoot = criteriaQuery.from(Comment.class);
        
        criteriaQuery.where(criteriaBuilder.equal(transactionRoot.get("experts").get("id"),expertsId));
        
        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(transactionRoot.get("created")));
        criteriaQuery.orderBy(orderList);
        TypedQuery<Comment> query = entityManager.createQuery(criteriaQuery);
        JpaUtils.setPage(query, pageable);
        return new NLZPageImpl<Comment>(query.getResultList(), pageable
        		,JpaUtils.count(entityManager, criteriaQuery));
	}
}
