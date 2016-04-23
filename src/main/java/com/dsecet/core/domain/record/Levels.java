package com.dsecet.core.domain.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dsecet.foundation.model.Domain;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 级别
 * */
@SuppressWarnings("serial")
@Entity
@Table(name = "levels")
@Getter
@Setter
@ToString(callSuper = true, exclude = {})
public class Levels extends Domain<Long>{

	/**
	 * 级别
	 * */
	@JsonProperty
	@Column(name="levels")
	private Integer levels = 1;
	
	/**
	 * 用户比例
	 * */
	@JsonProperty
	@Column(name="consumer_proportion")
	private Double consumerProportion;
	
	/**
	 * 专家比例
	 * */
	@JsonProperty
	@Column(name="experts_proportion")
	private Double expertsProportion;
	
}
