package com.dsecet.core.domain.user;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dsecet.foundation.model.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created on 2016年3月2日
 * <p>Title:       咨询汇_关注/p>
 * <p>Description: 关注类实体</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "focus")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"consumer","experts"})
@EqualsAndHashCode(callSuper = true, exclude = {})
public class Focus extends Domain<Long>{
	
	/**
	 * 用户
	 */
	@ManyToOne
    @JoinColumn(name = "consumer_id", nullable = true, updatable = false)
	private Consumer consumer;
	
	/**
	 * 专家
	 */
	@ManyToOne
    @JoinColumn(name = "experts_id", nullable = true, updatable = false)
	private Experts experts;
	
	private Long state;
}
