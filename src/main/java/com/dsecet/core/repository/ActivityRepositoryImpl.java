package com.dsecet.core.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.dsecet.core.domain.system.Activity;
import com.dsecet.foundation.model.NLZPage;
import com.dsecet.foundation.model.NLZPageImpl;
import com.dsecet.util.JpaUtils;

/**
 * @author: lxl
 */
@Repository
public class ActivityRepositoryImpl extends SimpleJpaRepository<Activity, Long> implements ActivityRepository{

    private EntityManager entityManager;

    @Autowired
    public ActivityRepositoryImpl(EntityManager entityManager) {
        super(Activity.class, entityManager);
        this.entityManager = entityManager;
    }
    
    

	@Override
	public NLZPage<Activity> findpage(String keyword, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Activity> criteriaQuery = criteriaBuilder.createQuery(Activity.class);
        Root<Activity> transactionRoot = criteriaQuery.from(Activity.class);
        List<Predicate> conditions = new ArrayList<>();
        
        if(!StringUtils.isBlank(keyword)){
        	conditions.add(
        			criteriaBuilder.and(criteriaBuilder.like(transactionRoot.get("name"), "%"+keyword+"%"))
        			);
        }
        
        conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("active"), true)));
        
        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));

        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(transactionRoot.get("created")));
        criteriaQuery.orderBy(orderList);
        TypedQuery<Activity> query = entityManager.createQuery(criteriaQuery);
        
        JpaUtils.setPage(query, pageable);
        return new NLZPageImpl<Activity>(query.getResultList(), pageable, JpaUtils.count(entityManager, criteriaQuery));
	}

}


