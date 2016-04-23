package com.dsecet.api.vo;

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
public class WithdrawVo{
    
    @JsonProperty("created")
    private String created;
    
    @JsonProperty("money")
    private Double money;
    
    @JsonProperty("state")
    private String state;
    
    @JsonProperty("type")
    private String type;
    
    
    public static List<WithdrawVo> of(Page<Trading> page){
        return page.getContent().stream().map(b -> WithdrawVo.of(b)).collect(Collectors.toList());
    }

    public static WithdrawVo of(Trading t){
    	WithdrawVo vo = new WithdrawVo();
        vo.setCreated(TimeUtils.getDateToString(t.getCreated()));
        vo.setMoney(t.getSink()/100);
        vo.setType(t.getType() == 1L?"充值":"兑换");
        vo.setState(t.getStatus() == 0L?"申请中":t.getStatus() == 1L?"兑换中":t.getStatus() == 2L?"兑换成功":"兑换失败(系统异常)");
        return vo;
    }
}
