package com.dsecet.foundation.model;

import static com.dsecet.foundation.model.ErrorCode.SUCCESSFUL;

import javax.validation.constraints.NotNull;

import com.dsecet.util.ErrorCodeMap;
import com.dsecet.util.ErrorCodeMap.CodeErrorEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author: Fablenas
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class StatusResponse{

    @ApiModelProperty(value = "Status Code, {'c': 0, 'm': {${response}}}", required = true)
    @JsonProperty("c")
    @NotNull
    private int code;

    @ApiModelProperty(value = "Message Body, {'c': 0, 'm': {${response}}}", dataType = "JSON Object")
    @JsonProperty("m")
    private Object data;
    
    
    

    private static final StatusResponse EMPTY_SUCCESS = of(ErrorCode.SUCCESSFUL, null);
    private static final StatusResponse ERR_UNDEFINED = of(ErrorCode.UNDEFINDED, null);

    @JsonCreator
    public static StatusResponse of(
            @JsonProperty(value = "c", required = true) int code,
            @JsonProperty("m") Object data) {
        return new StatusResponse(code, data);
    }

    public static StatusResponse success() {
        return EMPTY_SUCCESS;
    }

    public static StatusResponse undefined() {
        return ERR_UNDEFINED;
    }

    public static StatusResponse error(int code) {
    	 ErrorCodeMap map = new ErrorCodeMap();
    	 //CodeErrorEntity en = map.new CodeErrorEntity(code);
        return of(code, map.toErrorString(code));
    }
    
    public static StatusResponse success(Object data) {
        return of(SUCCESSFUL, data);
    }
    
}

