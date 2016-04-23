package com.dsecet.foundation.model;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiParam;

import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel
@Getter
@NoArgsConstructor
public abstract class PageRequest{
	
	@ApiModelProperty(value = "page", required = true, position = 0)
	//@ApiParam(name = "page",required = true)
	//@QueryParam("page")
	@JsonProperty("page")
	private int page;
	
	@ApiModelProperty(value = "size", required = true, position = 1)
	//@ApiParam(name = "size",required = true)
	//@QueryParam("size")
	@JsonProperty("size")
	private int size;

	public PageRequest(@JsonProperty("page")int page, @JsonProperty("size")int size) {
		this.page = page;
		this.size = size;
	}
	
	


}
