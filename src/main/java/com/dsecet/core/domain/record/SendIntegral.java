package com.dsecet.core.domain.record;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.foundation.model.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统送积分
 * @author heyue
 * */
@SuppressWarnings("serial")
@Entity
@Table(name = "send_integral")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"consumer"})
public class SendIntegral extends Domain<Long>{

	/**
	 * 发送事件名称
	 * */
	@Column(name="name")
	private String name;
	
	@Column(name="integral")
	private Double integral;
	
	@Column(name="sink")
	private Double sink;
	
	/**
	 * 用户
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "consumer_id", nullable = true, updatable = false)
	private Consumer consumer;
	
	@Column(name="message",length=500)
	private String message;
	
	public String getShortMessage(){
	       if (message.length()>30)
	           return message.substring(0, 15) + "...";
	       else
	           return message;
	    }
}
