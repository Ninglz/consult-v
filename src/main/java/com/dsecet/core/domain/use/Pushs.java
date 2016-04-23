package com.dsecet.core.domain.use;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.foundation.model.Domain;

import lombok.Data;
import lombok.ToString;
/**
 * 推送
 * */
@Entity
@Table(name = "pushs")
@Data
@ToString(callSuper = true, exclude = {"consumer"})
//@EqualsAndHashCode(callSuper = true, exclude = {"consumer"})
public class Pushs extends Domain<Long>{

	@Column(name="type")
	private String type;
	
	@Column(name="content")
	private String content;
	
	/**
	 * 用户群
	 * */
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "consumer_pushs", joinColumns = { @JoinColumn(name = "pushs_id") }, inverseJoinColumns = { @JoinColumn(name = "consumer_id") })
	private List<Consumer> consumer;
	
	public String getShortContent(){
	       if (content.length()>30)
	           return content.substring(0, 10) + "...";
	       else
	           return content;
	    }
}
