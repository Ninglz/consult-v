package com.dsecet.core.service.admin;

import java.util.List;

import com.dsecet.core.domain.record.Times;

/**
 * Created on 2016年3月28日
 * <p>Title:       咨询汇_时间段</p>
 * <p>Description: 时间段业务层</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public interface TimesService {
	
	Times save(Times times);
	
	void save(List<Times> times);
	
	Times findOne(Long id);
	
	List<Times> findAll();
	
	Times findProportion(Long weeks,Long times);
}
