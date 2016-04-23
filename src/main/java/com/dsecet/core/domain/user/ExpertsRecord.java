package com.dsecet.core.domain.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.dsecet.core.domain.system.Industry;
import com.dsecet.core.domain.use.Img;
import com.dsecet.foundation.model.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Table(name = "experts_record")
@Getter
@Setter
@ToString(callSuper = true, exclude = {})
public class ExpertsRecord extends Domain<Long> implements Comparable<ExpertsRecord> {

	/**
	 * 状态 -1：历史、0：修改、1：正在使用/正常
	 */
	@Column
	private Long state;

	/**
	 * 资费
	 */
	@Column(nullable = true)
	private Double price;

	/**
	 * 资历时间起
	 */
	@Column(nullable = true)
	private Date seniorityStart;

	/**
	 * 资历时间起
	 */
	@Column(nullable = true)
	private Date seniorityEnd;

	/**
	 * 擅长
	 */
	@Column(nullable = true, length = 500)
	private String goodAt;

	/**
	 * 说明
	 */
	@Column(nullable = true, length = 500)
	private String description;

	/**
	 * 行业
	 */
	@ManyToOne
	@JoinColumn(name = "industry_id", nullable = true, updatable = false)
	private Industry industry;

	/**
	 * 资历证书
	 */
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
//	@JoinTable(name = "record_img", joinColumns = { @JoinColumn(name = "record_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "img_id") })
//	private List<Img> img = new ArrayList<>();
    @Getter
    @OneToMany(mappedBy = "record", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
    @OrderBy(value="id ASC")
	private List<Img> img = new ArrayList<>();

	/**
	 * 审核状态
	 */
	@Column
	private Boolean auditState = false;

	/**
	 * 审核原因
	 */
	@Column(length = 500)
	private String reason;
	
	@Column
	private String expertsAvatar;
	@Column
	private Long expertsSex;
	@Column
	private String expertsRealName;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
	@JoinTable(name = "experts_expertsRecord", joinColumns = {
			@JoinColumn(name = "expertsRecord_id") }, inverseJoinColumns = { @JoinColumn(name = "experts_id") })
	private List<Experts> experts = new ArrayList<>();

	public void addExperts(Experts experts) {
		if (experts != null) {
			if (!this.experts.contains(experts))
				this.experts.add(experts);
		}
	}

	public void removeExperts(Experts experts) {
		if (this.experts.contains(experts)) {
			this.experts.remove(experts);
		}
	}

	public void addImg(List<Img> img) {
		if (null != img && !img.isEmpty()) {
			this.img.addAll(img);
		}
	}

	public void removeImg(List<Img> img) {
		if (null != img && !img.isEmpty()) {
			this.img.removeAll(img);
		}
	}

	public Object clone() throws CloneNotSupportedException {
		ExpertsRecord expertsRecord = (ExpertsRecord) super.clone();
		// 将引用的对象img也clone下
		// expertsRecord.setImg(expertsRecord.getImg().clone());
		return expertsRecord;
	}
//升序
	@Override
	public int compareTo(ExpertsRecord o) {
		return this.getCreated().compareTo(o.getCreated());
	}
}
