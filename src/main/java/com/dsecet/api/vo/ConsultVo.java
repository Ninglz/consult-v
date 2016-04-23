package com.dsecet.api.vo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.record.Consult;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.foundation.model.query.ExpertsDetailVo;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.ApiModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Setter
@Getter
@NoArgsConstructor
public class ConsultVo {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("avatar")
	private String avatar;
	
	@JsonProperty("times")
	private String times;
	
	@JsonProperty("startTimes")
	private String startTimes;
	
	@JsonProperty("state")
	private Long state;
	
	@JsonProperty("consumerSink")
	private Double consumerSink;
	
	@JsonProperty("consumerIntegral")
	private Double consumerIntegral;
	
	@JsonProperty("expertsSink")
	private Double expertsSink;
	
	@JsonProperty("expertsIntegral")
	private Double expertsIntegral;
	
	public static List<ConsultVo> of(Page<Consult> page){
        return page.getContent().stream().map(b -> ConsultVo.of(b)).collect(Collectors.toList());
    }
	
	public static ConsultVo of(Consult c){
		ConsultVo vo = new ConsultVo();
		vo.setId(c.getId());
		vo.setName(c.getExperts().getRealName() == null?"":c.getExperts().getRealName());
		vo.setAvatar(c.getExperts().getAvatar() == null?"":SystemProperty.webUrl + c.getExperts().getAvatar());
		vo.setStartTimes(c.getStartTime() == null ?"":TimeUtils.getDateToString(c.getStartTime()));
		vo.setTimes(c.getEndTime() == null?"":TimeUtils.getDateToString(c.getEndTime() - c.getStartTime()));
		vo.setConsumerIntegral(c.getConsumerIntegral());
		vo.setConsumerSink(c.getConsumerSink());
		vo.setExpertsIntegral(c.getExpertsIntegral());
		vo.setExpertsSink(c.getExpertsSink());
		return vo;
	}
}
