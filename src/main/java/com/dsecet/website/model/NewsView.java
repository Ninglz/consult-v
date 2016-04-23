package com.dsecet.website.model;

import com.dsecet.core.domain.news.News;
import lombok.Data;

import java.util.Date;


@Data
public class NewsView{

    private Long id;

    private String status;

    private String serial;

    private String mainTitle;

    private String subTitle;

    private String type;

    private String keyword;

    private String content;

    private Date lastModifiedDate;

    private Date createdDate;

    public static NewsView merge(News news, String content){
        NewsView response = new NewsView();
        response.setId(news.getId());
        response.setSerial(news.getSerial());
        //response.setStatus(news.getStatus().name());
        //response.setType(null != news.getType() ? news.getType().name() : "");
        //response.setMainTitle(news.getMainTitle());
        //response.setSubTitle(news.getSubTitle());
        response.setContent(content);
        response.setLastModifiedDate(news.getLastModifiedDate());
        response.setCreatedDate(news.getCreatedDate());
        return response;
    }

}
