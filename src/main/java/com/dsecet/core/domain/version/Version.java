package com.dsecet.core.domain.version;

import com.dsecet.foundation.model.Domain;
import com.dsecet.foundation.model.MediaItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

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
@Table(name = "version")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {})
public class Version extends Domain<Long>{

	@JsonProperty("name")
    @Column(name = "name", length = 30, nullable = false, unique = false)
    private String name;

	@JsonProperty("description")
    @Column(name = "description", length = 500, nullable = false, unique = false)
    private String description;
    
	@JsonProperty("forceUpdate")
    @Column(name="is_forceUpdate")
    private boolean forceUpdate=false;
    
	@JsonProperty("downUrl")
    @Column(name="down_url")
    private MediaItem downUrl;
	
	
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
