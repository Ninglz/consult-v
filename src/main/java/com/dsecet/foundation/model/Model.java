package com.dsecet.foundation.model;

import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class Model<T extends Serializable> implements Serializable{

    @ApiModelProperty(value = "Entity Identity", dataType = "long", required = true, position = -10)
    @JsonProperty("id")
    @GenericGenerator(name = "sys_model_id_gen", strategy = "enhanced-table", parameters = {
            @Parameter(name = "table_name", value = "sys_model_id_enhanced_gen"),
            @Parameter(name = "value_column_name", value = "next"),
            @Parameter(name = "segment_column_name", value = "segment_name"),
            @Parameter(name = "segment_value", value = "emp_seq"),
            @Parameter(name = "increment_size", value = "10"),
            @Parameter(name = "optimizer", value = "pooled-lo")
    })
    @Id
    @GeneratedValue(generator = "sys_model_id_gen")
    private T id;

    @JsonIgnore
    @NotNull
    @Setter(AccessLevel.NONE)
    @Column(nullable = false, updatable = false)
    Long created = TimeUtils.currentMillis();
}
