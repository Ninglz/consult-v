package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.OptsTraceLog;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lxl
 */
@Repository
public class OptsTraceLogListRepositoryImpl extends SimpleJpaRepository<OptsTraceLog, Long> implements OptsTraceLogListRepository{

    private EntityManager entityManager;

    @Autowired
    public OptsTraceLogListRepositoryImpl(EntityManager entityManager) {
        super( OptsTraceLog.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<OptsTraceLog> findOptsTraceLogList(OptsTraceLogListRepositoryImpl.OptsTraceLogQueryBuilder builder, Pageable pageable){
        Query countQuery = builder.buildForCount(entityManager);

        BigInteger count = BigInteger.ZERO;
        try {
            count = (BigInteger) countQuery.getSingleResult();
        } catch(NoResultException e) {
        }
        Query query = builder.build(entityManager, pageable);
        List<OptsTraceLog> OptsTraceLogList = query.getResultList();
        return new PageImpl<OptsTraceLog>(OptsTraceLogList, pageable, count.intValue());
    }

    public static class OptsTraceLogQueryBuilder{
        private String selectStatement = "select * ";
        private String countStatement = "select count(*) ";
        private String tableStatement = " from admin_opts_track_log a ";
        private String whereStatement = " where 1=1 ";
        private String createdStatement = " and a.created between :begDate and :endDate ";
        private String usernameStatement = " and a.username = :username ";
        private String operationStatement = " and a.operation = :operation ";
        private String orderByStatement = " order by a.created desc";
        private Map<String, Object> parameters = new HashMap<>();


        private OptsTraceLogQueryBuilder() {}

        public static OptsTraceLogQueryBuilder newBuilder() {
            return new OptsTraceLogQueryBuilder();
        }

        public OptsTraceLogQueryBuilder createdQueryCondiftion(Long begDate, Long endDate){
            if(begDate == null || endDate == null){
                return this;
            }
            parameters.put("begDate", begDate);
            parameters.put("endDate", endDate);
            return this;
        }

        public OptsTraceLogQueryBuilder usernameCondiftion(String username) {
            if (StringUtils.isBlank(username)) {
                return this;
            }
            parameters.put("username", username);
            return this;
        }

        public OptsTraceLogQueryBuilder operationCondiftion(String operation) {
            if (StringUtils.isBlank(operation)) {
                return this;
            }
            parameters.put("operation", operation);
            return this;
        }

        private Query buildForCount(EntityManager entityManager) {
            String sql = countStatement + tableStatement;
            sql += whereStatement;

            if(parameters.containsKey("begDate")){
                sql += createdStatement;
            }
            if (parameters.containsKey("username")) {
                sql += usernameStatement;
            }
            if (parameters.containsKey("operation")) {
                sql += operationStatement;
            }
            Query query = entityManager.createNativeQuery(sql);
            for (String key : parameters.keySet()) {
                query.setParameter(key, parameters.get(key));
            }
            return query;
        }

        private Query build(EntityManager entityManager, Pageable pageable) {
            String sql = selectStatement + tableStatement;
            sql += whereStatement;

            if(parameters.containsKey("begDate")){
                sql += createdStatement;
            }
            if (parameters.containsKey("username")) {
                sql += usernameStatement;
            }
            if (parameters.containsKey("operation")) {
                sql += operationStatement;
            }
            sql += orderByStatement;

            Query query = entityManager.createNativeQuery(
                    sql,
                    OptsTraceLog.class);
            for (String key : parameters.keySet()) {
                query.setParameter(key, parameters.get(key));
            }
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            return query;
        }
    }
}


