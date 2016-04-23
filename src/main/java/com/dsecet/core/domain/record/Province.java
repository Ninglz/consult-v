package com.dsecet.core.domain.record;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.dsecet.foundation.model.Domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 省份表
 * */
@SuppressWarnings("serial")
@Entity
@Table(name = "province")
@Getter
@Setter
@ToString(callSuper = true, exclude = {})
public class Province extends Domain<Long>{
	
	@Column(name="name")
	private String name;
	
    
    @Getter
    @OneToMany(mappedBy = "province", orphanRemoval = true)
    @OrderBy(value="id ASC")
	private List<City> cities;
	
}
