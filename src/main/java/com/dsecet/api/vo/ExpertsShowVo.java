package com.dsecet.api.vo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.foundation.model.query.CommentVo;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ExpertsShowVo {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("avatar")
	private String avatar;
	
	@JsonProperty("sex")
	private Long sex;
	
	/**
	 * 城市名
	 * */
	@JsonProperty("cityName")
	private String cityName;
	
	/**
	 * 省份名
	 * */
	@JsonProperty("provinceName")
	private String provinceName;
	
	/**
	 * 行业名
	 * */
	@JsonProperty("industryName")
	private String industryName;
	
	/**
	 * 擅长
	 * */
	@JsonProperty("goodAt")
	private String goodAt;
	
	/**
	 * 评分
	 * */
	@JsonProperty("evaluation")
	private Double evaluation;
	
	/**
	 * 资费
	 * */
	@JsonProperty("price")
	private BigDecimal price;
	
	/**
	 * 有效时长
	 * */
	@JsonProperty("validTime")
	private Double validTime;
	
	/**
	 * 标准单价
	 * */
	@JsonProperty("normalPrice")
	private Double normalPrice;
	
	/**
	 * 起价
	 * */
	@JsonProperty("startPrice")
	private Double startPrice;
	
	/**
	 * 级别比例
	 * */
	@JsonProperty("levelsProportion")
	private Double levelsProportion;
	
	/**
	 * 行业比例
	 * */
	@JsonProperty("industryProportion")
	private Double industryProportion;
	
	/**
	 * 时间比例
	 * */
	@JsonProperty("timesProportion")
	private Double timesProportion;
	
	/**
	 * 专家地区比例
	 * */
	@JsonProperty("areaProportion")
	private Double areaProportion;
	
	/**
	 * 专家说明
	 * */
	@JsonProperty("description")
	private String description;
	
	/**
	 * 专家评论
	 * */
	@JsonProperty("commentVo")
	private Collection<CommentVo> commentVo;
	
	/**
	 * 关注状态
	 * */
	@JsonProperty("active")
	private Boolean active;
	
	/**
	 * 专家上下线状态
	 * */
	@JsonProperty("state")
	private Long state;
	
	@JsonProperty("clientNum")
	private String clientNum;
	
	public static ExpertsShowVo of(Experts e,Collection<CommentVo> commentVo, Double timesProportion,Boolean active){
		ExpertsShowVo vo = new ExpertsShowVo();
		vo.setName(e.getRealName() == null?"":e.getRealName());
		vo.setAvatar(e.getAvatar() == null ?"":SystemProperty.webUrl + e.getAvatar());
		vo.setSex(e.getSex());
		vo.setCityName(e.getCity().getName() == null ?"":e.getCity().getName());
		vo.setProvinceName(e.getCity() == null?"":e.getCity().getProvince().getName());
		
		List<ExpertsRecord> expertsRecord = e.getExpertsRecord();
		Collections.sort(expertsRecord);
		expertsRecord.get(0);
		for (ExpertsRecord record : expertsRecord) {
			vo.setIndustryName(record.getIndustry() == null?"":record.getIndustry().getName());
			vo.setGoodAt(record.getGoodAt() == null?"":record.getGoodAt());
			vo.setDescription(record.getDescription() == null?"":record.getDescription());
			vo.setIndustryProportion(record.getIndustry() == null?0.0:record.getIndustry().getConsumerProportion());
		}
		vo.setClientNum(e.getClientNum());
		vo.setEvaluation(e.getConsumerDetails().getConsumerNum() == 0.0?0.0:(e.getConsumerDetails().getConsumerTotal() / e.getConsumerDetails().getConsumerNum()));
		vo.setCommentVo(commentVo);
		vo.setState(e.getState());
		vo.setValidTime(60.0);
		vo.setNormalPrice(SystemProperty.consumerPrice);
		vo.setStartPrice(SystemProperty.consumerStartPrice);
		vo.setLevelsProportion(e.getConsumerDetails().getExpertsLevels().getConsumerProportion());
		vo.setAreaProportion(e.getCity().getConsumerProportion());
		vo.setTimesProportion(timesProportion);
		vo.setActive(active);
		//资费  = (有效时长 * 标准单价 + 起步价)*级别浮动比例*行业浮动比例*时间浮动比例*地区浮动比例
		vo.setPrice(new BigDecimal(
				(vo.getValidTime() * vo.getNormalPrice() + vo.getStartPrice())*vo.getLevelsProportion()*vo.getIndustryProportion()*vo.getTimesProportion()*vo.getAreaProportion()
				).setScale(0, BigDecimal.ROUND_HALF_UP));
		return vo;
	}
}
