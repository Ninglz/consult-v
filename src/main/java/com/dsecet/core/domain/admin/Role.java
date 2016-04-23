package com.dsecet.core.domain.admin;

import com.dsecet.foundation.model.Domain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: lxl
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "admin_role")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"resourceses"})
public class Role extends Domain<Long>{

    @Column(name = "role_name", length = 30, nullable = false, unique = true)
    private String roleName;

    @Column(name = "role_code", length = 30, nullable = false, unique = true)
    private String roleCode;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="admin_role_resources",joinColumns={@JoinColumn(name="role_id")},inverseJoinColumns={@JoinColumn(name="resources_id")})
    private Set<Resources> resourceses = new LinkedHashSet<Resources>();

}
