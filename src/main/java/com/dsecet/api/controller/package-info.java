/**
 * @author: lxl
 */
package com.dsecet.api.controller;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


class Constant{
    static final String REGISTER = "register";
    static final String RESTPWD = "restPwd";
    static final String MODIFY = "modify";
}

@Getter
@NoArgsConstructor
@EqualsAndHashCode
final class BooleanEntity{


    @JsonProperty("v")
    @NotNull
    private Boolean value;

    @JsonCreator
    public BooleanEntity(@JsonProperty("v") @NotNull Boolean value){
        this.value = value;
    }
}

@Getter
@NoArgsConstructor
@EqualsAndHashCode
final class IntegerEntity{

    @JsonProperty("v")
    private long value;

    @JsonCreator
    public IntegerEntity(@JsonProperty("v") @NotNull Long value){
        this.value = value;
    }
}


@Getter
@NoArgsConstructor
@EqualsAndHashCode
final class StringEntity{


    @JsonProperty("v")
    @NotBlank
    private String value;

    @JsonCreator
    public StringEntity(@JsonProperty("v") @NotBlank String value){
        this.value = value;
    }
}