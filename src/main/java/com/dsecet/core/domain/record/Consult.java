package com.dsecet.core.domain.record;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.foundation.model.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created on 2016年3月2日
 * <p>Title:       咨询汇_咨询记录/p>
 * <p>Description: 关注类实体</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "consult")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"consumer"})
public class Consult extends Domain<Long>{
	
	/**
	 * 视频开始时间
	 */
	@NotNull
	@Column
	private Long startTime;
	/**
	 * 视频结束时间
	 */
	@NotNull
	@Column
	private Long endTime;
	
	/**
	 * 使用积分
	 */
	@NotNull
	@Column
	private Double  useIntegral = 0.0;

	/**
	 * 用户使用汇点
	 * */
	@Column
	private Double consumerSink = 0.0;
	
	/**
	 * 专家汇点
	 * */
	@Column
	private Double expertsSink= 0.0;
	
	/**
	 * 用户使用积分
	 * */
	@Column
	private Double consumerIntegral= 0.0;
	
	/**
	 * 专家使用积分
	 * */
	@Column
	private Double expertsIntegral= 0.0;
	
	/**
	 * 用户ID
	 */
	@ManyToOne
    @JoinColumn(name = "consumer_id", nullable = true, updatable = false)
	private Consumer consumer;
	
	/**
	 * 专家ID
	 */
	@ManyToOne
    @JoinColumn(name = "experts_id", nullable = true, updatable = false)
	private Experts experts;
	
	/**
	 * 专家行业比例
	 * */
	@Column(name="e_industry_proportion")
	private Double eIndustryProportion;
	/**
	 * 专家地区比例
	 * */
	@Column(name="e_city_proportion")
	private Double eCityProportion;
	
	/**
	 * 专家时间段比例
	 * */
	@Column(name="e_times_proportion")
	private Double eTimesProportion;
	
	/**
	 * 专家级别比例
	 * */
	@Column(name="e_levels_proportion")
	private Double eLevelsProportion;
	
	/**
	 * 用户行业比例
	 * */
	@Column(name="c_industry_proportion")
	private Double cIndustryProportion;
	
	/**
	 * 用户地区比例
	 * */
	@Column(name="c_city_proportion")
	private Double cCityProportion;
	
	/**
	 * 用户时间段比例
	 * */
	@Column(name="c_times_proportion")
	private Double cTimesProportion;
	
	/**
	 * 用户级别比例
	 * */
	@Column(name="c_levels_proportion")
	private Double cLevelsProportion;
	
	/**
	 * 时间状态    1:成功   2:失败
	 * */
	@Column(name="state")
	private Long state;
	
	/**
	 * 通话发起时间
	 * */
	@Column(name="launchTime")
	private Long launchTime;
	
}
