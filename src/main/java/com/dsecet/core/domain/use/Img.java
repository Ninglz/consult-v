package com.dsecet.core.domain.use;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.foundation.model.Domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Table(name = "img")
@Getter
@Setter
@ToString(callSuper = true, exclude = {})
public class Img extends Domain<Long>{

	/**
	 * 资历证书
	 */
//    @ManyToMany
//    @JoinTable(name = "record_img", joinColumns = { @JoinColumn(name = "img_id") }, inverseJoinColumns = { @JoinColumn(name = "record_id") })
//	private List<ExpertsRecord> record;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "record_id", nullable = true, updatable = false)
	private ExpertsRecord record;
    
    @Column(name="path",length=255)
    private String path;
    
    public static Img of(@NotNull String path,@NotNull ExpertsRecord expertsRecord){
		Img mediaDossier = new Img();
		mediaDossier.setPath(path);
		mediaDossier.setRecord(expertsRecord);
		return mediaDossier;
    }
	
}
