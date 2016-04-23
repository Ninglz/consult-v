package com.dsecet.core.domain.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dsecet.foundation.model.Domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 城市表
 * */
@SuppressWarnings("serial")
@Entity
@Table(name = "city")
@Getter
@Setter
@ToString(callSuper = true, exclude = {})
public class City extends Domain<Long>{

	@Column(name="name")
	private String name;
	
	/**
	 * 用户比例
	 * */
	@Column(name="consumer_proportion")
	private Double consumerProportion;
	
	/**
	 * 专家比例
	 * */
	
	@Column(name="experts_proportion")
	private Double expertsProportion;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "province_id", nullable = true, updatable = false)
	private Province province;
	
	public Long provinceId(){
		return getProvince().getId();
	}
}
