package com.dsecet.website.controller;

import static com.dsecet.foundation.model.StatusResponse.success;

import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.domain.news.News;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.core.service.admin.NewsService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.query.NewsVo;
import com.dsecet.util.FileHelper;

/**
 * Created on 2016年3月22日
 * <p>Title:       咨询汇_官网新闻</p>
 * <p>Description: 官网新闻控制层</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@Controller
@RequestMapping("/news")
public class NewsController{
	
	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    StatusResponse list(
			    		@FormParam("name") String title,@FormParam("timeSort") String timeSort,
            			@FormParam("industryId") Long industryId,@QueryParam("page") Integer page,
                        @QueryParam("size") Integer size
    ){
        PageableResponse<NewsVo> newsPage = newsService.findPage(title, timeSort,industryId, new PageRequest(page-1, size));
        return success(PageableResponse.of(newsPage.getCollection(), newsPage.getQueryRecordCount(), newsPage.getTotalRecordCount()));
    }

	/**
	 * <p>Description:app详情</p>
	 * @author:宁良竹
	 * @update: 2016年4月20日
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Long id){
    	News news = newsService.findOne(id);
    	ModelAndView view = new ModelAndView("news/news_detail_app");
    	String content = "";
        if(StringUtils.isNotBlank(news.getStoragePath())){
            String filePath = SystemProperty.storageFilePrefix + news.getStoragePath();
            try{
                content = FileHelper.readerToString(filePath);
            }catch(IOException e){
            	
            }
           }
    	view.addObject("news",NewsVo.detailOf(news,content));
        return view;
    }
    
    /**
     * <p>Description:pc详情</p>
     * @author:宁良竹
     * @update: 2016年4月20日
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detailWeb(@PathVariable("id") Long id){
    	News news = newsService.findOne(id);
    	ModelAndView view = new ModelAndView("news/news_detail_web");
    	String content = "";
        if(StringUtils.isNotBlank(news.getStoragePath())){
            String filePath = SystemProperty.storageFilePrefix + news.getStoragePath();
            try{
                content = FileHelper.readerToString(filePath);
            }catch(IOException e){
            	
            }
           }
    	view.addObject("news",NewsVo.detailOf(news,content));
        return view;
    }

}
