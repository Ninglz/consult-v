package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.Staff;
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
public class StaffListRepositoryImpl extends SimpleJpaRepository<Staff, Long> implements StaffListRepository{

    private EntityManager entityManager;

    @Autowired
    public StaffListRepositoryImpl(EntityManager entityManager) {
        super( Staff.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Staff> getByConditions(StaffListRepositoryImpl.StaffQueryBuilder builder, Pageable pageable){
        Query countQuery = builder.buildForCount(entityManager);

        BigInteger count = BigInteger.ZERO;
        try {
            count = (BigInteger) countQuery.getSingleResult();
        } catch(NoResultException e) {
        }
        Query query = builder.build(entityManager, pageable);
        List<Staff> staffList = query.getResultList();
        return new PageImpl<Staff>(staffList, pageable, count.intValue());
    }

    public static class StaffQueryBuilder{
        private String selectStatement = "select * ";
        private String countStatement = "select count(*) ";
        private String tableStatement = " from admin_staff a ";
        private String joinTableCustomerStatement = " inner join customer c on a.id = c.id ";
        private String whereStatement = " where 1=1 ";
        private String usernameStatement = " and c.username = :username ";
        private String activeStatement = " and c.active = :active ";
        private String orderByStatement = " order by c.created desc";
        private Map<String, Object> parameters = new HashMap<>();


        private StaffQueryBuilder() {}

        public static StaffQueryBuilder newBuilder() {
            return new StaffQueryBuilder();
        }

        public StaffQueryBuilder usernameCondiftion(String username) {
            if (StringUtils.isBlank(username)) {
                return this;
            }
            parameters.put("username", username);
            return this;
        }

        public StaffQueryBuilder activeCondiftion(String active) {
            if (StringUtils.isBlank(active)) {
                return this;
            }
            parameters.put("active", active);
            return this;
        }

        private Query buildForCount(EntityManager entityManager) {
            String sql = countStatement + tableStatement;
            sql += joinTableCustomerStatement;
            sql += whereStatement;

            if (parameters.containsKey("username")) {
                sql += usernameStatement;
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
            if (parameters.containsKey("active")) {
                sql += activeStatement;
            }
            sql += orderByStatement;

            Query query = entityManager.createNativeQuery(
                    sql,
                    Staff.class);
            for (String key : parameters.keySet()) {
                query.setParameter(key, parameters.get(key));
            }
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            return query;
        }
    }
}


