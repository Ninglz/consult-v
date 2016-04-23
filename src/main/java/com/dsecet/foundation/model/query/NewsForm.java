package com.dsecet.foundation.model.query;

import com.dsecet.core.domain.news.News;
import lombok.Data;

import java.util.Date;

/**
 * @author: lxp
 * Date: 2015/5/12 22:27
 * 66cf-v2
 */
@Data
public class NewsForm{

    private Long id;

    private String status;

    private String serial;

    private String title;

    private String content;

    private Date lastModifiedDate;

    private Date createdDate;

    public static NewsForm merge(News news, String content){
        NewsForm form = new NewsForm();
        form.setId(news.getId());
        form.setSerial(news.getSerial());
        form.setContent(content);
        form.setLastModifiedDate(news.getLastModifiedDate());
        form.setCreatedDate(news.getCreatedDate());
        form.setTitle(news.getTitle());
        return form;
    }

}
