package com.dsecet.core.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.foundation.model.query.ConsumerVo;
import com.dsecet.util.JpaUtils;

/**
 * @author: lxl
 */
@Repository
public class ConsumerListRepositoryImpl extends SimpleJpaRepository<Consumer, Long> implements ConsumerListRepository{

    private EntityManager entityManager;

    @Autowired
    public ConsumerListRepositoryImpl(EntityManager entityManager) {
        super( Consumer.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Consumer> findConsumerList(ConsumerListRepositoryImpl.ConsumerQueryBuilder builder, Pageable pageable){
        Query countQuery = builder.buildForCount(entityManager);

        BigInteger count = BigInteger.ZERO;
        try {
            count = (BigInteger) countQuery.getSingleResult();
        } catch(NoResultException e) {
        }
        Query query = builder.build(entityManager, pageable);
        List<Consumer> PersonalConsumerList = query.getResultList();
        return new PageImpl<Consumer>(PersonalConsumerList, pageable, count.intValue());
    }


    @Override
    public List<Consumer> exportConsumers(Long from, Long to){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Consumer> criteriaQuery = builder.createQuery(Consumer.class);
        Root<Consumer> root = criteriaQuery.from(Consumer.class);
        Path<Long> createdPath = root.get("created");
        criteriaQuery
                .where(builder.between(createdPath, from, to));
        TypedQuery<Consumer> query = entityManager.createQuery(criteriaQuery);
        return  query.getResultList();
    }


    public static class ConsumerQueryBuilder{
        private String selectStatement = "select * ";
        private String countStatement = "select count(*) ";
        private String tableStatement = " from consumer a ";
        private String joinTableCustomerStatement = " inner join customer c on a.id = c.id ";
        private String whereStatement = " where 1=1 ";
       private String cellNoStatement = " and a.cell_no = :cellNo ";
        private String realNameStatement = " and a.real_name = :realName ";
        private String idCardStatement = " and a.id_card = :idCard ";
        private String usernameStatement = " and c.username = :username ";
        private String lockedStatement = " and c.locked = :locked ";
        private String activeStatement = " and c.active = :active ";
        private String orderByStatement = " order by c.created desc";
        private String orderByLastSigned = " order by c.last_signed desc";
        private Map<String, Object> parameters = new HashMap<>();
        private Boolean lastSignedSorted = false;



        private ConsumerQueryBuilder() {}

        public static ConsumerQueryBuilder newBuilder() {
            return new ConsumerQueryBuilder();
        }

        public ConsumerQueryBuilder usernameCondiftion(String username) {
            if (StringUtils.isBlank(username)) {
                return this;
            }
            parameters.put("username", username);
            return this;
        }


        public ConsumerQueryBuilder cellNoCondiftion(String cellNo) {
            if (StringUtils.isBlank(cellNo)) {
                return this;
            }
            parameters.put("cellNo", cellNo);
            return this;
        }

        public ConsumerQueryBuilder realNameCondiftion(String realName) {
            if (StringUtils.isBlank(realName)) {
                return this;
            }
            parameters.put("realName", realName);
            return this;
        }

        public ConsumerQueryBuilder idCardCondiftion(String idCard) {
            if (StringUtils.isBlank(idCard)) {
                return this;
            }
            parameters.put("idCard", idCard);
            return this;
        }

        public ConsumerQueryBuilder lockedCondiftion(String locked) {
            if (StringUtils.isBlank(locked)) {
                return this;
            }
            parameters.put("locked", locked);
            return this;
        }

        public ConsumerQueryBuilder activeCondiftion(String active) {
            if (StringUtils.isBlank(active)) {
                return this;
            }
            parameters.put("active", active);
            return this;
        }

        public ConsumerQueryBuilder sortedByLastSignedSorted() {
            lastSignedSorted = true;
            return this;
        }

        private Query buildForCount(EntityManager entityManager) {
            String sql = countStatement + tableStatement;
            sql += joinTableCustomerStatement;
            sql += whereStatement;

            if (parameters.containsKey("username")) {
                sql += usernameStatement;
            }
            if (parameters.containsKey("cellNo")) {
                sql += cellNoStatement;
            }
            if (parameters.containsKey("realName")) {
                sql += realNameStatement;
            }
            if (parameters.containsKey("idCard")) {
                sql += idCardStatement;
            }
            if (parameters.containsKey("locked")) {
                sql += lockedStatement;
            }
            if (parameters.containsKey("active")) {
                sql += activeStatement;
            }
            Query query = entityManager.createNativeQuery(sql);
            for (String key : parameters.keySet()) {
                query.setParameter(key, parameters.get(key));
            }
            return query;
        }

        private Query build(EntityManager entityManager, Pageable pageable) {
            String sql = selectStatement + tableStatement;
            sql += joinTableCustomerStatement;
            sql += whereStatement;

            if (parameters.containsKey("username")) {
                sql += usernameStatement;
            }
            if (parameters.containsKey("cellNo")) {
                sql += cellNoStatement;
            }
            if (parameters.containsKey("realName")) {
                sql += realNameStatement;
            }
            if (parameters.containsKey("idCard")) {
                sql += idCardStatement;
            }
            if (parameters.containsKey("locked")) {
                sql += lockedStatement;
            }
            if (parameters.containsKey("active")) {
                sql += activeStatement;
            }

            if(!lastSignedSorted){
                sql += orderByStatement;
            }else{
                sql += orderByLastSigned;
            }


            Query query = entityManager.createNativeQuery(
                    sql,
                    Consumer.class);
            for (String key : parameters.keySet()) {
                query.setParameter(key, parameters.get(key));
            }
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            return query;
        }
    }


	@Override
	public Page<Consumer> queryByCondition(String keyword, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Consumer> criteriaQuery = criteriaBuilder.createQuery(Consumer.class);
        Root<Consumer> transactionRoot = criteriaQuery.from(Consumer.class);
        List<Predicate> conditions = new ArrayList<>();
        
        if (!StringUtils.isBlank(keyword)){
        	conditions.add(criteriaBuilder.or(criteriaBuilder.like(transactionRoot.get("authentication").get("realName").get("data"),"%"+keyword+"%"),
        			criteriaBuilder.like(transactionRoot.get("authentication").get("cellNo").get("data"),"%"+keyword+"%")));
        			
        }
        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));
        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(transactionRoot.get("created")));
        criteriaQuery.orderBy(orderList);
        TypedQuery<Consumer> query = entityManager.createQuery(criteriaQuery);

        if(pageable != null){
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        long count = JpaUtils.count(entityManager, criteriaQuery);

        return new PageImpl<Consumer>(query.getResultList(), pageable, count);
	}

}


