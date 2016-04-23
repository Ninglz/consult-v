package com.dsecet.foundation.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Collection;

import static com.dsecet.foundation.model.ErrorCode.SUCCESSFUL;

/**
 * @author: lxp
 * Date: 2015/7/14 16:45
 * safeguard-v1
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PageStatusResponse{

    @ApiModelProperty(value = "Status Code, {'c': 0, 'm': {${response}}}", required = true)
    @JsonProperty("c")
    @NonNull
    private int code;

    @ApiModelProperty(value = "Total count", dataType = "long")
    @JsonProperty("ct")
    private long count;

    @ApiModelProperty(value = "Message Body, {'c': 0, 'm': {${response}}}", dataType = "JSON Object")
    @JsonProperty("m")
    private Collection data;

    private static final PageStatusResponse EMPTY_SUCCESS = of(ErrorCode.SUCCESSFUL, 0, null);

    @JsonCreator
    public static PageStatusResponse of(
            @JsonProperty(value = "c", required = true) int code,
            @JsonProperty("ct") long count,
            @JsonProperty("m") Collection data) {
        return new PageStatusResponse(code, count, data);
    }

    public static PageStatusResponse success() {
        return EMPTY_SUCCESS;
    }

    public static PageStatusResponse error(int code) {
        return of(code, 0, null);
    }

    public static PageStatusResponse success(Collection data, long count) {
        return of(SUCCESSFUL, count, data);
    }
}
