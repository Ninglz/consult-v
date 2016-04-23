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
 * <p>Title:       咨询汇_交易记录/p>
 * <p>Description: 交易记录类实体</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "trading")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"experts"})
public class Trading extends Domain<Long>{
	
	/**
	 * 用户
	 */
	@ManyToOne
    @JoinColumn(name = "experts_id", nullable = true, updatable = false)
	private Experts experts;
	
	/**
	 * 类型 1：充值、2：提现
	 */
	@NotNull
	@Column
	private Long type;
	
	/**
	 * 汇点
	 */
	@NotNull
	@Column
	private Double  sink;

	/**
	 * 充值状态    0: 成功   1:失败
	 * */
	@Column
	private Long state;
	
	/**
	 * 提现状态    0: 申请中   1: 处理中    2:提现完成     3: 拒绝
	 * */
	@Column
	private Long status;
}
