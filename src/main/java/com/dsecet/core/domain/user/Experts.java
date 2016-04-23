package com.dsecet.core.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dsecet.core.domain.record.City;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 专家
 * */
@SuppressWarnings("serial")
@Entity
@Table(name = "experts")
@Getter
@Setter
@ToString(callSuper = true, exclude = {})
public class Experts extends Consumer{

	/**
	 * 状态 0：下线、1：上线、2：忙碌
	 */
    @Column(name="state")
	private Long state;
    
    /**
	 * 关注量
	 */
    @Column(name="focus_no")
	private Long focusNo = 0L;
    
    /**
	 * 置顶，默认未置顶
	 */
    @Column(name="is_top")
	private Boolean top =false;
    
    /**
	 * 置顶时间
	 */
    @Column(name="top_time")
	private Long topTime; 
    
    /**
     * 咨询师状态：0:空，1: 身份申请中，-1: 身份拒绝，2: 资料变更申请中，-2: 资料变更拒绝   3:资料审核成功
     */
    @Column(name="handle")
    private Long handle = 0L;
    
    /**
     * 最后活跃时间
     */
    @Column(name="last_active")
    private Long lastActive;
    /**
     * 最后活跃时间
     */
    @Column(name="alipayUserName")
    private String alipayUserName;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinTable(name = "experts_expertsRecord", joinColumns = { @JoinColumn(name = "experts_id")}, inverseJoinColumns = { @JoinColumn(name = "expertsRecord_id") })
	private List<ExpertsRecord> expertsRecord = new ArrayList<>();
    
    public void addExpertsRecord(ExpertsRecord record){
    	if(record!=null&&!this.expertsRecord.contains(record))
    		this.expertsRecord.add(record);
    }	
    
    public Experts build(City city){
    	if(null!=city)
    		this.setCity(city);
    	return this;
    }
    
    public void removeExpertsRecord(ExpertsRecord expertsRecord) {
		if (this.expertsRecord.contains(expertsRecord)) {
			this.expertsRecord.remove(expertsRecord);
		}
	}
    
    public void removeAll(List<ExpertsRecord> list) {
    	for (ExpertsRecord expertsRecord : list) {
    		this.expertsRecord.remove(expertsRecord);
		}
	}
    
}
