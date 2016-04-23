package com.dsecet.core.repository.api.impl;

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

import com.dsecet.core.domain.news.News;
import com.dsecet.util.JpaUtils;


public class NewsRepositoryImpl {
	
	public Page<News> findPage(String title,String timeSort,Long industryId, Pageable pageable){
		 	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
	        Root<News> transactionRoot = criteriaQuery.from(News.class);
	        List<Predicate> conditions = new ArrayList<>();
	        
	        if(!StringUtils.isBlank(title)){
	        	conditions.add(
	        			criteriaBuilder.and(criteriaBuilder.like(transactionRoot.get("title"), "%"+title+"%"))
	        			);
	        }
	        
	        if(null != industryId){
	        	conditions.add(
	        			criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("industry").get("id"), industryId))
	        			);
	        }
	        conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("active"), true)));
	        
	        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));

	        List<Order> orderList = new ArrayList<>();
	        if (!StringUtils.isBlank(timeSort)&&"ASC".equals(timeSort.toUpperCase())) {
	                orderList.add(criteriaBuilder.asc(transactionRoot.get("created")));
            }else{
                orderList.add(criteriaBuilder.desc(transactionRoot.get("created")));
	        }
	        criteriaQuery.orderBy(orderList);
	        TypedQuery<News> query = entityManager.createQuery(criteriaQuery);
	        
	        long count= JpaUtils.count(entityManager, criteriaQuery);
	        
	        if(pageable != null){
	            query.setFirstResult(pageable.getOffset());
	            query.setMaxResults(pageable.getPageSize());
	        }

	        return new PageImpl<News>(query.getResultList(), pageable, count);
	}
	@Autowired
	private EntityManager entityManager;

}
