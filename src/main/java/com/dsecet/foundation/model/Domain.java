package com.dsecet.foundation.model;

import com.dsecet.helper.ImageHelper;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Fablenas
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Domain<T extends Serializable> extends Model<T>{

    @JsonIgnore
    @NotNull
    @Setter(AccessLevel.NONE)
    @Column(nullable = false, name = "last_modified")
    private Long lastModified = created;

    @JsonIgnore
    @NotNull
    @Column(nullable = false)
    private boolean active = true;

    public void updateLastModified(){
        lastModified = TimeUtils.currentMillis();
    }

    @JsonIgnore
    @Transient
    public Date getCreatedDate(){
        return new Date(this.created);
    }
    
    
}
