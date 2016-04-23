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

import com.dsecet.core.domain.record.Trading;
import com.dsecet.util.JpaUtils;

/**
 * @author: heyue
 */
@Repository
public class TradingRepositoryImpl extends SimpleJpaRepository<Trading, Long> implements TradingRepository{

    private EntityManager entityManager;

    @Autowired
    public TradingRepositoryImpl(EntityManager entityManager) {
        super(Trading.class, entityManager);
        this.entityManager = entityManager;
    }

	@Override
	public Page<Trading> findPage(Long id, String keyword, Long state, Pageable pageable,Long type,Long status) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Trading> criteriaQuery = criteriaBuilder.createQuery(Trading.class);
        Root<Trading> transactionRoot = criteriaQuery.from(Trading.class);
        List<Predicate> conditions = new ArrayList<>();
        
        if(id != null){
        	conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("experts").get("id"), id)));
        }
        
        if (!StringUtils.isBlank(keyword)){
        	conditions.add(criteriaBuilder.or(criteriaBuilder.like(transactionRoot.get("authentication").get("realName").get("data"),"%"+keyword+"%"),
        			criteriaBuilder.like(transactionRoot.get("authentication").get("cellNo").get("data"),"%"+keyword+"%"),
        			criteriaBuilder.like(transactionRoot.get("authentication").get("nickName").get("data"),"%"+keyword+"%")));
        }
        
        if(state != null){
        	conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("state"), state)));
        }
        
        if(type != null){
        	conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("type"), type)));
        }
        if(status != null){
        	conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("status"), status)));
        }
        
        conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("active"), true)));
        
        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));

        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(transactionRoot.get("created")));
        criteriaQuery.orderBy(orderList);
        TypedQuery<Trading> query = entityManager.createQuery(criteriaQuery);
        
        long count= JpaUtils.count(entityManager, criteriaQuery);
        
        if(pageable != null){
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        return new PageImpl<Trading>(query.getResultList(), pageable, count);
	}
}


