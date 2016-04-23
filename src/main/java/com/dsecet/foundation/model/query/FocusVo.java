package com.dsecet.foundation.model.query;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.core.domain.user.Focus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import lombok.Data;

/**
 * 关注VO
 * @author heyue
 * */
@Data
public class FocusVo {

	@JsonProperty("expertsId")
    private Long expertsId;
	
	/**
	 * 咨询师姓名
	 * */
	@JsonProperty("name")
	private String name;
	
	/**
	 * 咨询师头像
	 * */
	@JsonProperty("img")
	private String img;
	
	/**
	 * 咨询师资历
	 * */
	@JsonProperty("seniority")
	private Integer seniority;
	
	/**
	 * 咨询师行业
	 * */
	@JsonProperty("industry")
	private String industry;
	
	/**
	 * 咨询师资费
	 * */
	@JsonProperty("price")
	private Double price;
	
	/**
	 * 咨询师上下线
	 * */
	@JsonProperty("state")
	private Long state;
	
	
	public static List<FocusVo> of(Page<Focus> page){
        List<FocusVo> reslut = Lists.newArrayList();
        return page.getContent().stream().map(b -> FocusVo.of(b)).collect(Collectors.toList());
    }
	
	public static FocusVo of(Focus f){
		FocusVo vo = new FocusVo();
		vo.setExpertsId(f.getExperts().getId());
		vo.setName(f.getExperts().getRealName());
		vo.setImg(f.getExperts().getAvatar() == null?"":f.getExperts().getAvatar());
		vo.setState(f.getExperts().getState());
		List<ExpertsRecord> expertsRecord = f.getExperts().getExpertsRecord();
		for (ExpertsRecord record : expertsRecord) {
			if(record.getSeniorityEnd() == null){
				vo.setSeniority(new Date().getYear() - record.getSeniorityStart().getYear());
			}else{
				vo.setSeniority(record.getSeniorityEnd().getYear() - record.getSeniorityStart().getYear());
			}
			vo.setIndustry(record.getIndustry().getName());
			vo.setPrice(record.getPrice());
		}
		return vo;
	}
}
