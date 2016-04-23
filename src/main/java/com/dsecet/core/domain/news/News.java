package com.dsecet.core.domain.news;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.dsecet.core.domain.system.Industry;
import com.dsecet.foundation.model.Customer;
import com.dsecet.foundation.model.Domain;
import com.dsecet.util.ModelUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Created on 2016年3月22日
 * <p>Title:       咨询汇_新闻</p>
 * <p>Description: 新闻实体</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@Entity
@Table(name = "news")
@EqualsAndHashCode(callSuper = true, exclude = {})
@ToString(callSuper = true, exclude = {})
@Getter
@Setter
public class News extends Domain<Long>{

    /**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;


	/**
	 * 标题
	 */
    @Column(name = "title")
    private String title;

    /** rule: uuid */
    @Column(name = "serial", unique = true, nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private String serial =ModelUtils.getSerial();

    /**
     * 富文本路径
     */
    @Column(name = "storage_path")
    private String storagePath;
    
    /**
     * 摘要
     */
    @Column(name="digest")
    private String digest;
    
    /**
     * 标题图
     */
    @Column(name="title_img")
    private String titleImg;
    
    /**
     * 行业
     */
    @ManyToOne
    @JoinColumn(name = "industry_id", nullable = true, updatable = false)
    private Industry industry;


    @JsonIgnore
    @Transient
    public Date getLastModifiedDate(){
        return new Date(this.getLastModified());
    }
    
    public String getShortDigest(){
        if (digest.length()>30)
            return digest.substring(0, 30) + "...";
        else
            return digest;
    }


    public String getShortTitle(){
        if (title.length()>10)
            return title.substring(0, 10) + "...";
        else
            return title;
    }
}
