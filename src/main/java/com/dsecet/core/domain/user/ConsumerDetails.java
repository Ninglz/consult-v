package com.dsecet.core.domain.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dsecet.core.domain.record.Levels;
import com.dsecet.foundation.model.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户详情
 * */
@SuppressWarnings("serial")
@Entity
@Table(name = "consumer_details")
@Getter
@Setter
@ToString(callSuper = true, exclude = {})
public class ConsumerDetails extends Domain<Long>{
	
	@ManyToOne
    @JoinColumn(name = "consumer_levels_id")
	private Levels consuemrLevels ;
	
    @ManyToOne
    @JoinColumn(name = "experts_levels_id")
	private Levels expertsLevels;
    
    @OneToOne(mappedBy = "consumerDetails", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE,CascadeType.MERGE, CascadeType.PERSIST})
    private Consumer consumer;
	
	  /**
     * 汇点
     * */
    @Column(name="sink")
    private Double sink = 0.0;
    
    /**
     * 积分
     * */
    @Column(name="integral")
    private Double integral = 0.0;
    
    /**
     * 咨询者总分
     * */
    @Column(name="consumer_total")
    private Double consumerTotal = 0.0;
    
    /**
     * 咨询者评论数
     * */
    @Column(name="consumer_num")
    private Long consumerNum = 0L;
    /**
     * 咨询师总分
     * */
    @Column(name="experts_total")
    private Double expertsTotal = 0.0;
    
    /**
     * 咨询师评论数
     * */
    @Column(name="experts_num")
    private Long expertsNum = 0L;
    
    /**
     * 累积充值金额
     * */
    @Column(name="recharge_price")
    private Double rechargePrice = 0.0;
    
    /**
     * 累积提现金额
     * */
    @Column(name="cash_price")
    private Double cashPrice = 0.0;
    
    /**
     * 用户无效次数
     * */
    @Column(name="consumer_invalid_time")
    private Long consumerInvalidTime = 0L;
    
    /**
     * 用户有效次数
     * */
    @Column(name="consumer_valid_time")
    private Long consumerValidTime = 0L;
    
    /**
     * 专家无效次数
     * */
    @Column(name="experts_invalid_time")
    private Long expertsInvalidTime = 0L;
    
    /**
     * 专家有效次数
     * */
    @Column(name="experts_valid_time")
    private Long expertsValidTime = 0L;
}
