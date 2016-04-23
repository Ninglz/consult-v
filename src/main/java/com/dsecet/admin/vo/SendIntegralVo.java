package com.dsecet.admin.vo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.record.SendIntegral;
import com.dsecet.core.domain.record.Trading;
import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author: heyue
 * 系统发送积分VO
 */
@Data
public class SendIntegralVo{
    
    @JsonProperty("created")
    private String created;
    
    @JsonProperty("cell")
    private String cell;
    
    @JsonProperty("nickName")
    private String nickName;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("integral")
    private Double integral;
    
    public static List<SendIntegralVo> of(Page<SendIntegral> page){
        return page.getContent().stream().map(b -> SendIntegralVo.of(b)).collect(Collectors.toList());
    }

    public static SendIntegralVo of(SendIntegral s){
    	SendIntegralVo vo = new SendIntegralVo();
    	vo.setCreated(TimeUtils.getDateToString(s.getCreated()));
    	vo.setName(s.getName());
    	vo.setIntegral(s.getIntegral());
    	 vo.setNickName(s.getConsumer().getRealName() == null?s.getConsumer().getRealName():s.getConsumer().getNickName());
    	vo.setCell(s.getConsumer().getCellNo());
        return vo;
    }
}
