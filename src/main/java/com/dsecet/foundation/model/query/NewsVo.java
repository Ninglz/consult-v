package com.dsecet.foundation.model.query;

import com.dsecet.core.domain.news.News;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

import org.springframework.data.domain.Page;

@Data
public class NewsVo {

    private static final String FROMAT_DATE = "yyyy-MM-dd HH:mm:ss";

    @JsonProperty("id")
    private Long id;

    @JsonProperty("lastModified")
    private String lastModified;

    @JsonProperty("title")
    private String title;
    
    @JsonProperty("digest")
    private String digest;
    
    @JsonProperty("titleImg")
    private String titleImg;
    
    @JsonProperty("content")
    private String content;
    
    @JsonProperty("industryName")
    private String industryName;
    
    public static NewsVo of(News news){     
            NewsVo vo = new NewsVo();
            vo.setId(news.getId());
            vo.setLastModified(TimeUtils.millisToDateTime(news.getLastModified(), FROMAT_DATE));
            vo.setTitle(news.getShortTitle());
            vo.setDigest(news.getShortDigest());
            vo.setTitleImg(SystemProperty.webUrl+news.getTitleImg().replaceAll("\\\\", "/"));
            vo.setIndustryName(news.getIndustry() == null ?"":news.getIndustry().getName());
        return vo;
    }
    
    public static List<NewsVo> of(Page<News> page){
    	 List<NewsVo> reslut = Lists.newArrayList();
    	 page.getContent().stream().forEach(
         		s -> {reslut.add(NewsVo.of(s));});
    	return reslut;
    }
    
    public static NewsVo detailOf(News news,String content){     
        NewsVo vo= NewsVo.of(news);
        vo.setDigest(news.getDigest());
        vo.setTitle(news.getTitle());
        vo.setContent(content);
        return vo;
}
}
