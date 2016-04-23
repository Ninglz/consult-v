package com.dsecet.core.service.admin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.domain.news.News;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.NewsVo;

/**
 * Created on 2016年3月22日
 * <p>Title:       咨询汇_新闻接口</p>
 * <p>Description: [描述该类概要功能介绍]</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public interface NewsService {
	
	/**
	 * <p>Description:查找新闻对象</p>
	 * @author:宁良竹
	 * @update: 2016年3月24日
	 * @param id
	 * @return
	 */
	News findOne(Long id);
	
	/**
	 * <p>Description:保存新闻</p>
	 * @author:宁良竹
	 * @update: 2016年3月24日
	 * @param news
	 * @return
	 */
	News save(News news);
	
	/**
	 * <p>Description:分页查询</p>
	 * @author:宁良竹
	 * @update: 2016年3月22日
	 * @param title
	 * @param page
	 * @return
	 */
	Page<News>findPage(String title, Pageable page);
	
	/**
	 * <p>Description:分页查询</p>
	 * @author:宁良竹
	 * @update: 2016年3月22日
	 * @param title
	 * @param PageableResponse
	 * @return
	 */
	PageableResponse<NewsVo>findPage(String title,String timeSort,Long industryId, Pageable page);
	
	/**
	 * <p>Description:添加新闻</p>
	 * @author:宁良竹
	 * @update: 2016年3月24日
	 * @param title
	 * @param digest
	 * @param titleImg
	 * @param content
	 * @return
	 */
	News create(String title, String digest, 
    		MultipartFile titleImg,String content,Long industryId);
	


	
}
