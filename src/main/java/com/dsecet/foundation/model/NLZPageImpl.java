package com.dsecet.foundation.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Created on 2016年4月20日
 * <p>Title:       重构</p>
 * <p>Description: 重新构造PageImpl类</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public class NLZPageImpl<T>  extends PageImpl<T> implements NLZPage<T>{
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private List<T> voList = new ArrayList<T>();

	public NLZPageImpl(List<T> content) {
		super(content);
	}
	
	public NLZPageImpl(List<T> content, Pageable pageable, long total) {
		super(content, pageable,total);
	}
	
	@SuppressWarnings("unchecked")
	public NLZPage<T> setCollection(List<?> content){
		this.voList.addAll((Collection<? extends T>) content);
		return this;
	}

	@Override
	public List<T> getVoList() {
		return this.voList;
	}

}
