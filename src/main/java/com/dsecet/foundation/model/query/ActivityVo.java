package com.dsecet.foundation.model.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.system.Activity;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 活动VO
 * @author heyue
 * */
@Data
public class ActivityVo {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("img")
	private String img;
	
	@JsonProperty("describe")
	private String describe;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("created")
	private String created;
	
	
	public static List<ActivityVo> of(Page<Activity> page){
        return page.getContent().stream().map(b -> ActivityVo.of(b)).collect(Collectors.toList());
    }
	
	public static ActivityVo of(Activity a){
		ActivityVo vo = new ActivityVo();
		vo.setId(a.getId());
		vo.setName(a.getName() == null ?"":a.getName());
		vo.setImg(a.getImg() == null?"":(SystemProperty.webUrl + a.getImg()).replaceAll("\\\\", "/"));
		vo.setDescribe(a.getDescription() == null ?"":a.getDescription());
		vo.setUrl(a.getUrl() == null?"":a.getUrl());
		vo.setCreated(TimeUtils.getDateToString(a.getCreated()));
		return vo;
	}
}
