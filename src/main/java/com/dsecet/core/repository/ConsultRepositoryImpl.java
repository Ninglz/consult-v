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

import com.dsecet.core.domain.record.Consult;
import com.dsecet.foundation.model.NLZPage;
import com.dsecet.foundation.model.NLZPageImpl;
import com.dsecet.util.JpaUtils;

/**
 * @author: lxl
 */
@Repository
public class ConsultRepositoryImpl extends SimpleJpaRepository<Consult, Long> implements ConsultRepository{

    private EntityManager entityManager;

    @Autowired
    public ConsultRepositoryImpl(EntityManager entityManager) {
        super(Consult.class, entityManager);
        this.entityManager = entityManager;
    }

	@Override
	public NLZPage<Consult> findpage(String keyword,Long id, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Consult> criteriaQuery = criteriaBuilder.createQuery(Consult.class);
        Root<Consult> transactionRoot = criteriaQuery.from(Consult.class);
        List<Predicate> conditions = new ArrayList<>();
        
        if(!StringUtils.isBlank(keyword)){
        	conditions.add(
        			criteriaBuilder.and(criteriaBuilder.like(transactionRoot.get("experts").get("authentication").get("realName").get("data"), "%"+keyword+"%"))
        			);
        }
        
        if(id != null){
        	conditions.add(
        			criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("consumer").get("id"),id))
        			);
        }
        
        conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("active"), true)));
        
        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));

        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(transactionRoot.get("created")));
        criteriaQuery.orderBy(orderList);
        TypedQuery<Consult> query = entityManager.createQuery(criteriaQuery);
        
        JpaUtils.setPage(query, pageable);

        return new NLZPageImpl<Consult>(query.getResultList(), pageable,  JpaUtils.count(entityManager, criteriaQuery));
	}
}


