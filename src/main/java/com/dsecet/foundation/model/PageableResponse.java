package com.dsecet.foundation.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Collection;


/**
 * @author: Fablenas
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class PageableResponse<T>{

    @JsonProperty("records")
    private Collection<T> collection;

    @JsonProperty("queryRecordCount")
    @NonNull
    private int queryRecordCount;

    @JsonProperty("totalRecordCount")
    private int totalRecordCount;

    @JsonCreator
    public static PageableResponse of(
            @JsonProperty(value = "records") Collection collection,
            @JsonProperty(value = "queryRecordCount", required = true) int queryRecordCount,
            @JsonProperty(value = "totalRecordCount", required = true) int totalRecordCount
            ) {

        return new PageableResponse(collection, queryRecordCount, totalRecordCount);
    }
    
    public static PageableResponse of(NLZPage page
            ) {
        return PageableResponse.of(page.getVoList(), page.getContent().size(), (int)page.getTotalElements());
    }



    public static PageableResponse empty() {
        return of(null, 0, 0);
    }



}

