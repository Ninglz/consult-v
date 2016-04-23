package com.dsecet.core.domain.admin;

import com.dsecet.foundation.model.Domain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: lxl
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "admin_resources")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Resources extends Domain<Long>{

    @Column(name = "name", length = 64, nullable = false, unique = true)
    private String name;

    @Column(name = "pattern_url", length = 128, nullable = false, unique = true)
    private String patternUrl;

}
