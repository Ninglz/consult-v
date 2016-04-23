package com.dsecet.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: Fablenas
 */
@MappedSuperclass
@Data
public abstract class Record<T extends Serializable> extends Model<T>{

    @JsonIgnore
    @Column
    private String comment;

    @JsonIgnore
    @Transient
    public Date getCreatedDate(){
        return new Date(this.created);
    }
}
