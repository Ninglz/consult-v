package com.dsecet.foundation.model.query;

import com.dsecet.core.domain.admin.OptsTraceLog;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: lxl
 */
@Data
public class OptLogVo{

    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("operation")
    private String operation;
    @JsonProperty("params")
    private String params;
    @JsonProperty("succeed")
    private Boolean succeed;
    @JsonProperty("created")
    private String created;

    public static List<OptLogVo> of(Page<OptsTraceLog> logsPage){
        List<OptLogVo> reslut = Lists.newArrayList();
        List<OptsTraceLog> l = logsPage.getContent();
        l.stream().forEach(s -> {
            OptLogVo d = new OptLogVo();
            d.setUsername(s.getUsername());
            d.setOperation(s.getOperationType().toString());
            d.setParams(s.getParams());
            d.setSucceed(s.isSucceed());
            d.setCreated(TimeUtils.millisToDateStr(s.getCreated()));
            reslut.add(d);
        });
        return reslut;
    }

}
