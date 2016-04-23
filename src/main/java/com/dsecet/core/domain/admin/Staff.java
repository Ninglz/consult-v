package com.dsecet.core.domain.admin;

import com.dsecet.foundation.model.AuthItem;
import com.dsecet.foundation.model.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("serial")
@Entity
@Table(name = "admin_staff")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, exclude = {"roles"})
public class Staff extends Customer{

    @ApiModelProperty(value = "Cell Number", required = true)
    @JsonProperty(value = "ac")
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "data", column = @Column(name = "cell_no", nullable = true, unique = true, length = 16)),
            @AttributeOverride(name = "authenticated", column = @Column(name = "cell_no_auth")),
            @AttributeOverride(name = "token", column = @Column(name = "cell_no_token"))})
    private AuthItem cellNo  = new AuthItem();

    @ApiModelProperty(value = "Real Name", required = true)
    @JsonProperty(value = "ar")
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "data", column = @Column(name = "real_name", nullable = true, length = 32)),
            @AttributeOverride(name = "authenticated", column = @Column(name = "real_name_auth")),
            @AttributeOverride(name = "token", column = @Column(name = "real_name_token"))})
    private AuthItem realName  = new AuthItem();

    @ApiModelProperty(value = "ID Card", required = true)
    @JsonProperty(value = "ai")
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "data", column = @Column(name = "id_card", nullable = true, unique = true, length = 32)),
            @AttributeOverride(name = "authenticated", column = @Column(name = "id_card_auth")),
            @AttributeOverride(name = "token", column = @Column(name = "id_card_token"))})
    private AuthItem idCard  = new AuthItem();

    @Column(name = "is_root", nullable = false, updatable = false)
    private boolean isRoot;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "admin_staff_role", joinColumns = {@JoinColumn(name = "staff_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new LinkedHashSet<Role>();


}
