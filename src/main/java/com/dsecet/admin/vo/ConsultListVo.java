package com.dsecet.admin.vo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.record.Consult;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Setter
@Getter
@NoArgsConstructor
public class ConsultListVo {
	
	@JsonProperty("consumerName")
	private String consumerName;
	
	@JsonProperty("expertsName")
	private String expertsName;
	
	@JsonProperty("startTimes")
	private String startTimes;
	
	@JsonProperty("endTimes")
	private String endTimes;
	
	@JsonProperty("launchTime")
	private String launchTime;
	
	@JsonProperty("state")
	private String state;
	
	@JsonProperty("consumerSink")
	private Double consumerSink;
	
	@JsonProperty("consumerIntegral")
	private Double consumerIntegral;
	
	@JsonProperty("expertsSink")
	private Double expertsSink;
	
	@JsonProperty("expertsIntegral")
	private Double expertsIntegral;
	
	public static List<ConsultListVo> of(Page<Consult> page){
        return page.getContent().stream().map(b -> ConsultListVo.of(b)).collect(Collectors.toList());
    }
	
	public static ConsultListVo of(Consult c){
		ConsultListVo vo = new ConsultListVo();
		vo.setConsumerName(c.getConsumer().getNickName());
		vo.setExpertsName(c.getExperts().getRealName());
		vo.setStartTimes(c.getStartTime() == null ?"":TimeUtils.getDateToString(c.getStartTime()));
		vo.setEndTimes(c.getEndTime() == null?"":TimeUtils.getDateToString(c.getEndTime()));
		vo.setLaunchTime(c.getLaunchTime() == null?"":TimeUtils.getDateToString(c.getLaunchTime()));
		vo.setState(c.getState() == null?"":c.getState() == 1L?"通话成功":"通话失败");
		vo.setConsumerIntegral(c.getConsumerIntegral());
		vo.setConsumerSink(c.getConsumerSink());
		vo.setExpertsIntegral(c.getExpertsIntegral());
		vo.setExpertsSink(c.getExpertsSink());
		return vo;
	}
}
