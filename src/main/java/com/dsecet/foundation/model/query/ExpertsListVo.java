package com.dsecet.foundation.model.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.system.SystemDefaultProperty;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import lombok.Data;

/**
 * 专家列表VO
 * @author heyue
 * */
@Data
public class ExpertsListVo {
	
	@JsonProperty("evaluation")
	private String evaluation; 
	
	@JsonProperty("industryName")
	private String industryName;
	
	@JsonProperty("startPrice")
	private String startPrice;
	
	@JsonProperty("goodAt")
	private String goodAt;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("topTime")
	private String topTime;
	
	@JsonProperty("isTop")
	private String isTop;
	
	@JsonProperty("avatar")
	private String avatar;
	
	@JsonProperty("active")
	private Boolean active;
	
	public static List of(List<Map> list){
		List newList = new ArrayList<>();
		for (Map map : list) {
			newList.add(ExpertsListVo.of(map));
		}
		return newList;
	}
	
	public static ExpertsListVo of(Map map){
		ExpertsListVo vo = new ExpertsListVo();
		vo.setDescription(map.get("description")==null?"":map.get("description").toString());
		vo.setEvaluation(map.get("evaluation") == null?"":map.get("evaluation").toString());
		vo.setGoodAt(map.get("goodAt")==null?"":map.get("goodAt").toString());
		vo.setId(map.get("id").toString());
		vo.setIndustryName(map.get("industryName") == null?"":map.get("industryName").toString());
		vo.setIsTop(map.get("is_top") == null ?"":map.get("is_top").toString());
		vo.setName(map.get("name") == null?"":map.get("name").toString());
		vo.setPrice(map.get("price") == null?"":map.get("price").toString());
		vo.setStartPrice(map.get("startPrice") == null?"":map.get("startPrice").toString());
		vo.setState(map.get("state")== null?"":map.get("state").toString());
		vo.setTopTime(map.get("top_time") == null?"":TimeUtils.millisToDateStr(Long.valueOf(map.get("top_time").toString())));
		vo.setAvatar(map.get("avatar") == null?"":map.get("avatar").toString().replaceAll("\\\\", "/"));
		vo.setActive(Boolean.valueOf(map.get("active") == null?"":map.get("active").toString()));
		return vo;
	}
}
