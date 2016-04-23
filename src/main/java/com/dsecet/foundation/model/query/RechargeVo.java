package com.dsecet.foundation.model.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.record.Trading;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author: heyue
 * 充值记录VO
 */
@Data
public class RechargeVo{
    
    @JsonProperty("created")
    private String created;
    
    @JsonProperty("money")
    private Double money;
    
    @JsonProperty("state")
    private String state;
    
    @JsonProperty("type")
    private String type;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("cell")
    private String cell;
    
    
    public static List<RechargeVo> of(Page<Trading> page){
        return page.getContent().stream().map(b -> RechargeVo.of(b)).collect(Collectors.toList());
    }

    public static RechargeVo of(Trading t){
    	RechargeVo vo = new RechargeVo();
        vo.setCreated(TimeUtils.getDateToString(t.getCreated()));
        vo.setMoney(t.getSink()/100);
        vo.setState(t.getState() == 0L?"充值成功":"充值失败(系统异常)");
        vo.setType(t.getType() == 1L?"充值":"兑换");
        vo.setName(t.getExperts().getRealName() == null?t.getExperts().getRealName():t.getExperts().getNickName());
        vo.setCell(t.getExperts().getCellNo());
        return vo;
    }
}
