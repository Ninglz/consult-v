package com.dsecet.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.core.domain.news.News;
import com.dsecet.core.domain.system.Industry;
import com.dsecet.core.domain.system.SystemDefaultProperty;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.core.service.admin.IndustryService;
import com.dsecet.core.service.admin.NewsService;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.NewsVo;
import com.dsecet.util.image.Uploader;

/**
 * Created on 2016年3月22日
 * <p>Title:       咨询汇_平台新闻</p>
 * <p>Description: 平台新闻控制层</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@Controller
@RequestMapping("/news")
public class AdminNewsController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "news/news_list";
    }
	
    @RequestMapping(value = "/test" ,method=RequestMethod.POST)
    public
    ModelAndView test(HttpServletResponse response){
    	ModelAndView modelAndView = new ModelAndView("news/news_create");
    	List<Industry> industryList = industryService.findAll();
        return modelAndView.addObject("industryList",industryList);
    }
	
	/**
	 * <p>Description:新闻列表</p>
	 * @author:宁良竹
	 * @update: 2016年3月22日
	 * @param keyword
	 * @param active
	 * @param expertState
	 * @param pageable
	 * @return
	 */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse list(String keyword, Pageable pageable){
    	Page<News> newsPage = newsService.findPage(keyword, pageable);
        return PageableResponse.of(NewsVo.of(newsPage), newsPage.getContent().size(), (int) newsPage.getTotalElements());
    }
    
    /**
     * <p>Description:跳转到新闻创建页面</p>
     * @author:宁良竹
     * @update: 2016年3月23日
     * @param response
     * @return
     */
    @RequestMapping(value = "/create")
    public
    ModelAndView toCreate(HttpServletResponse response){
    	ModelAndView modelAndView = new ModelAndView("news/news_create");
    	List<Industry> industryList = industryService.findAll();
        return modelAndView.addObject("industryList",industryList);
    }
    
    /**
     * <p>Description:添加新闻</p>
     * @author:宁良竹
     * @update: 2016年3月24日
     * @param title 标题
     * @param summary	摘要
     * @param img	图片
     * @param content	内容
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam(required = true)String title, @RequestParam(required = false)String summary, 
    		@RequestParam(required = true) MultipartFile img,@RequestParam(required = true)String content,@RequestParam(required = true)Long industryId){
    	News news = newsService.create(title, summary, img, content,industryId);
    	ModelAndView modelAndView = new ModelAndView("redirect:/admin/news/");
        return modelAndView;
    }
    
    /**
     * <p>Description:删除新闻</p>
     * @author:宁良竹
     * @update: 2016年3月24日
     * @param newsId
     * @return
     */
    @RequestMapping(value = "/delete")
    public ModelAndView delete(Long newsId) {
    	News news = newsService.findOne(newsId);
    	news.setActive(false);
    	newsService.save(news);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/news/");
        return modelAndView;
    }
    
    /**
     * <p>Description:新闻详情</p>
     * @author:宁良竹
     * @update: 2016年3月25日
     * @param newsId
     * @return
     */
    @RequestMapping(value = "/detail")
    public ModelAndView detail(Long newsId) {
    	News news = newsService.findOne(newsId);
    	
        ModelAndView modelAndView = new ModelAndView("/news/news_detail");
        modelAndView.addObject("news", NewsVo.of(news));
        return modelAndView;
    }
    
    /**
     * <p>Description:富文本图片上传</p>
     * @author:宁良竹
     * @update: 2016年3月25日
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/img/upload",method=RequestMethod.POST)
    public  @ResponseBody 
    String upload(HttpServletResponse response,HttpServletRequest request) throws IOException {
    	response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setHeader("X-Content-Type-Options", "MIME-sniffing");
        Uploader up = new Uploader(request);
        up.setSavePath("upload");
        String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
        up.setAllowFiles(fileType);
        up.setMaxSize(10000); //单位KB
        try {
			up.upload(SystemProperty.storageFilePrefix);
		} catch (Exception e) {
			e.printStackTrace();
		}

        String callback = request.getParameter("callback");

        String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";

        result = result.replaceAll( "\\\\", "\\\\" );
        if( callback == null ){
//        	response.getWriter().print( result );
        	return result;
        }else{
//        	response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
        	return "<script>"+ callback +"(" + result + ")</script>";
        }
    }
    
    
    @Autowired
    private NewsService newsService;
    
    
    @Autowired
    private IndustryService industryService;

}
