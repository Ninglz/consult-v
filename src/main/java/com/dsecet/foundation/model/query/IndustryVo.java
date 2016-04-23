package com.dsecet.foundation.model.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.system.Industry;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class IndustryVo {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
    
    @JsonProperty("consumerProportion")
    private Double consumerProportion;
    
    @JsonProperty("expertsProportion")
    private Double expertsProportion;

    
    public static List<IndustryVo> of(Page<Industry> page){
    	 List<IndustryVo> reslut = Lists.newArrayList();
    	 return page.getContent().stream().map(b -> IndustryVo.of(b)).collect(Collectors.toList());
    }
    
    public static IndustryVo of(Industry i){     
        IndustryVo vo= new IndustryVo();
        vo.setName(i.getName());
        vo.setId(i.getId());
        vo.setConsumerProportion(i.getConsumerProportion());
        vo.setExpertsProportion(i.getExpertsProportion());
        return vo;
}
}
