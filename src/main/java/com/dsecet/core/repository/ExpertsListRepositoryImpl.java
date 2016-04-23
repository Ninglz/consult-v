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

import com.dsecet.core.domain.user.Consumer.AppType;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.repository.admin.IndustryRepository;
import com.dsecet.foundation.model.query.ExpertsVo;
import com.dsecet.util.JpaUtils;
import com.dsecet.util.TypeHelper;


@Repository
public class ExpertsListRepositoryImpl extends SimpleJpaRepository<Experts, Long> implements ExpertsListRepository{

	private EntityManager entityManager;
	
	@Autowired
	private IndustryRepository industryRepository;
	
    @Autowired
    public ExpertsListRepositoryImpl(EntityManager entityManager) {
        super( Experts.class, entityManager);
        this.entityManager = entityManager;
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
					cstmt.setString(5, "");
					cstmt.setString(6, id);
					cstmt.setInt(7,1);
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
	public Page findRecommendExperts(String id,String evaluation,Pageable pageable) {
		//获取session
				Session session = entityManager.unwrap(org.hibernate.Session.class);
				Map map = new HashedMap();
				session.doWork(
					new Work() {//获得JDBC连接
						@Override
						public void execute(Connection conn) throws SQLException {
							CallableStatement cstmt = conn.prepareCall("{call getExpertsPage(?,?,?,?,?,?,?,?,?,?)}");
							cstmt.setString(1, "");
							cstmt.setString(2, "");
							cstmt.setString(3, "");
							cstmt.setString(4, evaluation);
							cstmt.setString(5, "");
							cstmt.setString(6, id);
							cstmt.setInt(7,1);
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
	public Page<Experts> queryByCondition(AppType appType,String keyword, String active ,Long expertState, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Experts> criteriaQuery = criteriaBuilder.createQuery(Experts.class);
        Root<Experts> transactionRoot = criteriaQuery.from(Experts.class);
        List<Predicate> conditions = new ArrayList<>();
        
        if (!StringUtils.isBlank(keyword)){
        	conditions.add(criteriaBuilder.or(criteriaBuilder.like(transactionRoot.get("authentication").get("realName").get("data"),"%"+keyword+"%"),
        			criteriaBuilder.like(transactionRoot.get("authentication").get("cellNo").get("data"),"%"+keyword+"%"),
        			criteriaBuilder.like(transactionRoot.get("authentication").get("nickName").get("data"),"%"+keyword+"%")));
        			
        }
        if (!StringUtils.isBlank(active)){
        	conditions.add(criteriaBuilder.equal(transactionRoot.get("active"),active.equals("true")));
        }
        if (null!=expertState){
        	conditions.add(criteriaBuilder.equal(transactionRoot.get("handle"),expertState));
        }
        if (null!=appType){
        	conditions.add(criteriaBuilder.equal(transactionRoot.get("AppType"),appType));
        }
        criteriaQuery.where(conditions.toArray(new Predicate[conditions.size()]));
        List<Order> orderList = new ArrayList<>();
        orderList.add(criteriaBuilder.desc(transactionRoot.get("created")));
        criteriaQuery.orderBy(orderList);
        TypedQuery<Experts> query = entityManager.createQuery(criteriaQuery);

        if(pageable != null){
            query.setFirstResult(pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }
        long count = JpaUtils.count(entityManager, criteriaQuery);

        return new PageImpl<Experts>(query.getResultList(), pageable, count);
	}

	@Override
	public Boolean updateBatch(Long time) {
		try {
			String sql = "update experts e set e.state=0 where e.state<>0 and ifnull(e.last_active,0)<" + time;
			entityManager.createNativeQuery(sql).executeUpdate();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}

