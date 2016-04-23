package com.dsecet.core.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.dsecet.core.domain.record.Times;
import com.dsecet.core.repository.DomainRepository;




/**
 * Created on 2016年3月28日
 * <p>Title:       咨询汇_时间</p>
 * <p>Description: 时间段数据层</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public interface TimesRepository extends DomainRepository<Times>{
	List<Times> findAllByOrderByTimeFromAsc();
	
	@Query("select t from Times t where t.weeks = ?1 and t.timeFrom <= ?2 and ?2 < t.timeTo")
	Times findProportion(Long weeks,Long times);
}
