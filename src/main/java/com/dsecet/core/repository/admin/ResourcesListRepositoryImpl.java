package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.Resources;
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
public class ResourcesListRepositoryImpl extends SimpleJpaRepository<Resources, Long> implements ResourcesListRepository{

    private EntityManager entityManager;

    @Autowired
    public ResourcesListRepositoryImpl(EntityManager entityManager) {
        super( Resources.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Resources> getByConditions(ResourcesListRepositoryImpl.ResourcesQueryBuilder builder, Pageable pageable){
        Query countQuery = builder.buildForCount(entityManager);

        BigInteger count = BigInteger.ZERO;
        try {
            count = (BigInteger) countQuery.getSingleResult();
        } catch(NoResultException e) {
        }
        Query query = builder.build(entityManager, pageable);
        List<Resources> resourcesList = query.getResultList();
        return new PageImpl<Resources>(resourcesList, pageable, count.intValue());
    }

    public static class ResourcesQueryBuilder{
        private String selectStatement = "select * ";
        private String countStatement = "select count(*) ";
        private String tableStatement = " from admin_resources a ";
        private String whereStatement = " where 1=1 ";
        private String orderByStatement = " order by a.created desc";
        private Map<String, Object> parameters = new HashMap<>();


        private ResourcesQueryBuilder() {}

        public static ResourcesQueryBuilder newBuilder() {
            return new ResourcesQueryBuilder();
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
                    Resources.class);
            for (String key : parameters.keySet()) {
                query.setParameter(key, parameters.get(key));
            }
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            return query;
        }
    }
}


