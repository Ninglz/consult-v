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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.dsecet.core.domain.system.Activity;
import com.dsecet.core.domain.version.Version;
import com.dsecet.util.JpaUtils;

@Repository
public class VersionRepositoryImpl extends SimpleJpaRepository<Version, Long> implements VersionRepository{

	
	private EntityManager entityManager;
	
    @Autowired
    public VersionRepositoryImpl(EntityManager entityManager) {
        super(Version.class, entityManager);
        this.entityManager = entityManager;
    }

	@Override
	public Page<Version> findCondition(String name, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Version> criteriaQuery = criteriaBuilder.createQuery(Version.class);
        Root<Version> transactionRoot = criteriaQuery.from(Version.class);
        List<Predicate> conditions = new ArrayList<>();
        
        if(!StringUtils.isBlank(name)){
        	conditions.add(
        			criteriaBuilder.and(criteriaBuilder.like(transactionRoot.get("name"), "%"+name+"%"))
        			);
        }
        
        conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("active"), true)));
        
        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));

        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(transactionRoot.get("created")));
        criteriaQuery.orderBy(orderList);
        TypedQuery<Version> query = entityManager.createQuery(criteriaQuery);
        
        long count= JpaUtils.count(entityManager, criteriaQuery);
        
        if(pageable != null){
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        return new PageImpl<Version>(query.getResultList(), pageable, count);
	}

}
