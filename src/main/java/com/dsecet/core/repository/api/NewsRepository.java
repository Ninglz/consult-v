package com.dsecet.core.repository.api;

import com.dsecet.core.domain.news.News;
import com.dsecet.core.repository.DomainRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



/**
 * Created on 2016年3月24日
 * <p>Title:       咨询汇_时间</p>
 * <p>Description: 新闻数据层</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public interface NewsRepository extends DomainRepository<News>{
	
	Page<News> findByTitleLikeAndActiveOrderByCreatedDesc(String title,boolean active,Pageable pageable);
	
	Page<News> findPage(String title,String timeSort,Long industryId, Pageable page);
}
