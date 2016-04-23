package com.dsecet.core.domain.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dsecet.foundation.model.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 时间段
 * */
@SuppressWarnings("serial")
@Entity
@Table(name = "times")
@Getter
@Setter
@ToString(callSuper = true, exclude = {})
public class Times extends Domain<Long>{

	@Column(name="weeks")
	private Long weeks;
	
	@Column(name="time_from")
	private Long timeFrom;
	
	@Column(name="time_to")
	private Long timeTo;
	
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
	
}
