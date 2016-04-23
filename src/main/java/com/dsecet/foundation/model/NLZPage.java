package com.dsecet.foundation.model;

import java.util.List;

import org.springframework.data.domain.Page;


/**
 * Created on 2016年4月20日
 * <p>Title:       重构</p>
 * <p>Description: 重新构造Page类</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public interface NLZPage<T>  extends Page<T>{
	
	public NLZPage<T> setCollection(List<?> content);
	
	public List<T> getVoList();

}
