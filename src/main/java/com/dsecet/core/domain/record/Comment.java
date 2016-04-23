package com.dsecet.core.domain.record;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.foundation.model.Domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 评论表
 * @author heyue
 * */
@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString(callSuper = true, exclude = {"consumer","experts"})
@EqualsAndHashCode(callSuper = true, exclude = {"consumer","experts"})
public class Comment extends Domain<Long>{
	
	/**
	 * 评论内容
	 * */
	@Column(name="content",length=500)
	private String content;
	
	@Column(name="score")
	private Double score;
	
	/**
	 * 评论类型   1:用户   2:专家
	 * */
	@Column
	private Long type;
	
	/**
	 * 评论人ID
	 * */
	@ManyToOne
    @JoinColumn(name = "consumer_id", nullable = true, updatable = false)
	private Consumer consumer;
	
	/**
	 * 评论人ID
	 * */
	@ManyToOne
	@JoinColumn(name = "experts_id", nullable = true, updatable = false)
	private Experts experts;
	
	 public String getShortContent(){
	       if (content.length()>30)
	           return content.substring(0, 10) + "...";
	       else
	           return content;
	    }
}
