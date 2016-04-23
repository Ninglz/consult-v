package com.dsecet.core.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.dsecet.core.domain.system.Activity;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.Focus;
import com.dsecet.foundation.model.query.ExpertsVo;
import com.dsecet.util.JpaUtils;
import com.dsecet.util.TypeHelper;

@Repository
public class FocusRepositoryImpl extends SimpleJpaRepository<Focus, Long> implements FocusRepository {

	private EntityManager entityManager;
	
    @Autowired
    public FocusRepositoryImpl(EntityManager entityManager) {
        super( Focus.class, entityManager);
        this.entityManager = entityManager;
    }

	@Override
	public void removeFocusShip(Long consumerId, Long expertsId) {
		String sql ="delete from focus where consumer_id = "+consumerId+" and experts_id = "+expertsId;
		entityManager.createNativeQuery(sql).executeUpdate();
	}

	@Override
	public Page findByConditions(String name, String industryId, String evaluation, String price,String id,Pageable pageable) {
		//获取session
		Session session = entityManager.unwrap(org.hibernate.Session.class);
		Map map = new HashedMap();
		session.doWork(
			new Work() {//获得JDBC连接
				@Override
				public void execute(Connection conn) throws SQLException {
					CallableStatement cstmt = conn.prepareCall("{call getExpertsPage(?,?,?,?,?,?,?,?,?,?)}");
					cstmt.setString(1, name);
					cstmt.setString(2, industryId);
					cstmt.setString(3, price);
					cstmt.setString(4, evaluation);
					cstmt.setString(5, id);
					cstmt.setString(6, id);
					cstmt.setInt(7,0);
					cstmt.setInt(8, pageable.getPageNumber());
					cstmt.setInt(9, pageable.getPageSize());
					cstmt.registerOutParameter(10,Types.INTEGER);  
                    ResultSet rs = cstmt.executeQuery();
                    List<ExpertsVo> list = TypeHelper.resultSetToList(rs);
                    Long count = Long.valueOf(cstmt.getObject(10).toString());
                    map.put("list", list);
                    map.put("count", count);
				}
			}
		);
		return new PageImpl((List)map.get("list"), pageable, (Long)map.get("count"));
	}

	@Override
	public Focus findActivite(Long consumerId,Long expertsId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Focus> criteriaQuery = criteriaBuilder.createQuery(Focus.class);
        Root<Focus> transactionRoot = criteriaQuery.from(Focus.class);
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("active"), true)));
        conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("consumer").get("id"), consumerId)));
        conditions.add(criteriaBuilder.and(criteriaBuilder.equal(transactionRoot.get("experts").get("id"), expertsId)));
        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));
        TypedQuery<Focus> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList().size()>0?query.getResultList().get(0):null;
	}
}
