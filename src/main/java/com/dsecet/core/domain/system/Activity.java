package com.dsecet.core.domain.system;

import com.dsecet.core.service.SystemProperty;
import com.dsecet.foundation.model.Domain;
import com.dsecet.foundation.model.ImgDomain;
import com.dsecet.helper.ImageHelper;
import com.dsecet.util.ModelUtils;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

/**
 * Created on 2016年03月02日
 * <p>Title:       咨询汇_版本/p>
 * <p>Description: 版本实体</p>
 * <p>Copyright:   Copyright (c) 2015</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "activity")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {})
public class Activity extends ImgDomain<Long>{

	@JsonProperty("name")
    @Column(name = "name", length = 30, nullable = false, unique = false)
    private String name;

	@JsonProperty("description")
    @Column(name = "description", length = 500, nullable = false, unique = false)
    private String description;
    
	@JsonProperty("img")
    @Column(name="img")
    private String img;
	

	@Column(name = "url", length = 500)
	private String url;
	
	@Column(name = "serial", unique = true, nullable = false, updatable = false)
	@Setter(AccessLevel.PRIVATE)
	private String serial = ModelUtils.getSerial();
	
	@Column(name = "start_time", length = 30, nullable = false, unique = false)
	private Long start;
	
	@Column(name = "end_time", length = 30, nullable = false, unique = false)
	private Long end;
	
    public String getShortName(){
        if (name.length()>10)
            return name.substring(0, 10) + "...";
        else
            return name;
    }


    public String getShortDescription(){
        if (description.length()>30)
            return description.substring(0, 30) + "...";
        else
            return description;
    }



}
