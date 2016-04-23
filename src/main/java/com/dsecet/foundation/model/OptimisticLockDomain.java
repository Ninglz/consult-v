package com.dsecet.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * @author: Fablenas
 */
@MappedSuperclass
@ToString(callSuper = true, exclude = {"version"})
@EqualsAndHashCode(callSuper = true, exclude = {"version"})
public abstract class OptimisticLockDomain extends Domain<Long>{

    @JsonIgnore
    @Version
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private long version;
}
