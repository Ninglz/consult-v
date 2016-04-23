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

import com.dsecet.core.domain.record.SendIntegral;
import com.dsecet.core.domain.record.Trading;
import com.dsecet.util.JpaUtils;

/**
 * @author: lxl
 */
@Repository
public class SendIntegralRepositoryImpl extends SimpleJpaRepository<SendIntegral, Long> implements SendIntegralRepository{

    private EntityManager entityManager;

    @Autowired
    public SendIntegralRepositoryImpl(EntityManager entityManager) {
        super( SendIntegral.class, entityManager);
        this.entityManager = entityManager;
    }

	@Override
	public Page<SendIntegral> findPage(String keyword, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SendIntegral> criteriaQuery = criteriaBuilder.createQuery(SendIntegral.class);
        Root<SendIntegral> transactionRoot = criteriaQuery.from(SendIntegral.class);
        List<Predicate> conditions = new ArrayList<>();
        
        if (!StringUtils.isBlank(keyword)){
        	conditions.add(criteriaBuilder.or(criteriaBuilder.like(transactionRoot.get("consumer").get("authentication").get("realName").get("data"),"%"+keyword+"%"),
        			criteriaBuilder.like(transactionRoot.get("consumer").get("authentication").get("cellNo").get("data"),"%"+keyword+"%"),
        			criteriaBuilder.like(transactionRoot.get("consumer").get("authentication").get("nickName").get("data"),"%"+keyword+"%")));
        }
        
        conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("active"), true)));
        
        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));

        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(transactionRoot.get("created")));
        criteriaQuery.orderBy(orderList);
        TypedQuery<SendIntegral> query = entityManager.createQuery(criteriaQuery);
        
        long count= JpaUtils.count(entityManager, criteriaQuery);
        
        if(pageable != null){
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        return new PageImpl<SendIntegral>(query.getResultList(), pageable, count);
	}
}


