package com.dsecet.core.service.admin;

import java.util.List;

import com.dsecet.core.domain.record.Levels;

/**
 * Created on 2016年3月25日
 * <p>Title:       咨询汇_级别</p>
 * <p>Description: 咨询者/师级别业务层</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public interface LevelsService {
	
	Levels save(Levels levels);
	
	void save(List<Levels> leveles);
	
	Levels findOne(Long id);
	
	List<Levels> findAll();

}
