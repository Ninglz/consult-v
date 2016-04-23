package com.dsecet.admin.vo;

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
public class WithdrawListVo{
    
    @JsonProperty("created")
    private String created;
    
    @JsonProperty("money")
    private Double money;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("cell")
    private String cell;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("alipayUserName")
    private String alipayUserName;
    
    @JsonProperty("id")
    private Long id;
    
    public static List<WithdrawListVo> of(Page<Trading> page){
        return page.getContent().stream().map(b -> WithdrawListVo.of(b)).collect(Collectors.toList());
    }

    public static WithdrawListVo of(Trading t){
    	WithdrawListVo vo = new WithdrawListVo();
        vo.setCreated(TimeUtils.getDateToString(t.getCreated()));
        vo.setMoney(t.getSink()/100);
        vo.setStatus(t.getStatus() == 0L?"申请中":t.getStatus() == 1L?"处理中":t.getStatus() == 2L?"提现完成":"提现拒绝");
        vo.setCell(t.getExperts().getCellNo());
        vo.setName(t.getExperts().getRealName() == null?"":t.getExperts().getRealName());
        vo.setAlipayUserName(t.getExperts().getAlipayUserName() == null?"":t.getExperts().getAlipayUserName());
        vo.setId(t.getId());
        return vo;
    }
}
