package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.Role;
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
public class RoleListRepositoryImpl extends SimpleJpaRepository<Role, Long> implements RoleListRepository{

    private EntityManager entityManager;

    @Autowired
    public RoleListRepositoryImpl(EntityManager entityManager) {
        super( Role.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Role> getByConditions(RoleListRepositoryImpl.RoleQueryBuilder builder, Pageable pageable){
        Query countQuery = builder.buildForCount(entityManager);

        BigInteger count = BigInteger.ZERO;
        try {
            count = (BigInteger) countQuery.getSingleResult();
        } catch(NoResultException e) {
        }
        Query query = builder.build(entityManager, pageable);
        List<Role> roleList = query.getResultList();
        return new PageImpl<Role>(roleList, pageable, count.intValue());
    }

    public static class RoleQueryBuilder{
        private String selectStatement = "select * ";
        private String countStatement = "select count(*) ";
        private String tableStatement = " from admin_role a ";
        private String whereStatement = " where 1=1 and a.active = 1";
        private String orderByStatement = " order by a.created desc";
        private Map<String, Object> parameters = new HashMap<String, Object>();


        private RoleQueryBuilder() {}

        public static RoleQueryBuilder newBuilder() {
            return new RoleQueryBuilder();
        }



        private Query buildForCount(EntityManager entityManager) {
            String sql = countStatement + tableStatement;

            sql += whereStatement;

            Query query = entityManager.createNativeQuery(sql);
            for (String key : parameters.keySet()) {
                query.setParameter(key, parameters.get(key));
            }
            return query;
        }

        private Query build(EntityManager entityManager, Pageable pageable) {
            String sql = selectStatement + tableStatement;
            sql += whereStatement;
            sql += orderByStatement;

            Query query = entityManager.createNativeQuery(
                    sql,
                    Role.class);
            for (String key : parameters.keySet()) {
                query.setParameter(key, parameters.get(key));
            }
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            return query;
        }
    }
}


