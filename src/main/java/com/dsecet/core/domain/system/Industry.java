package com.dsecet.core.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dsecet.foundation.model.Domain;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created on 2016年3月2日
 * <p>Title:       咨询汇_行业/p>
 * <p>Description: 行业码表</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "industry")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {})
public class Industry extends Domain<Long>{

	@JsonProperty("name")
    @Column(name = "name", length = 30, nullable = false, unique = false)
    private String name;

	/**
	 * 用户比例
	 * */
	@JsonProperty("consumer_proportion")
	@Column(name="consumer_proportion")
	private Double consumerProportion;
	
	/**
	 * 专家比例
	 * */
	@JsonProperty("experts_proportion")
	@Column(name="experts_proportion")
	private Double expertsProportion;

}