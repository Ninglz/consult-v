package com.dsecet.core.repository.admin;

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

import com.dsecet.core.domain.system.Industry;
import com.dsecet.util.JpaUtils;

@Repository
public class IndustryRepositoryImpl extends SimpleJpaRepository<Industry, Long> implements IndustryRepository{

	
	private EntityManager entityManager;
	
    @Autowired
    public IndustryRepositoryImpl(EntityManager entityManager) {
        super(Industry.class, entityManager);
        this.entityManager = entityManager;
    }

	@Override
	public Page<Industry> queryByCondition(String keyword, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Industry> criteriaQuery = criteriaBuilder.createQuery(Industry.class);
        Root<Industry> transactionRoot = criteriaQuery.from(Industry.class);
        List<Predicate> conditions = new ArrayList<>();
        criteriaBuilder.countDistinct(transactionRoot.get("name"));
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
        TypedQuery<Industry> query = entityManager.createQuery(criteriaQuery);
        if(pageable != null){
            query.setFirstResult(0);
            query.setMaxResults(pageable.getPageSize());
        }
        long count = JpaUtils.count(entityManager, criteriaQuery);

        return new PageImpl<Industry>(query.getResultList(), pageable, count);
	}
}
