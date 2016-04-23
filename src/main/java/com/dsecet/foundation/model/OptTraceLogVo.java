package com.dsecet.foundation.model;

import com.dsecet.core.domain.admin.OptsTraceLog;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author: lxl
 */
@Data
public class OptTraceLogVo{

    @JsonProperty("username")
    private String username;

    @JsonProperty("succeed")
    private String succeed;

    @JsonProperty("created")
    private String created;

    @JsonProperty("operation")
    private String operation;

    public static List<OptTraceLogVo> of(Page<OptsTraceLog> optTraceLogPage){
        List<OptTraceLogVo> reslut = Lists.newArrayList();
        List<OptsTraceLog> l = optTraceLogPage.getContent();
        l.stream().parallel().forEach(s -> {
            OptTraceLogVo d = new OptTraceLogVo();
            d.setCreated(LocalDateTime.ofInstant(Instant.ofEpochMilli(s.getCreated()), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            d.setOperation(s.getOperationType().toString());
            d.setSucceed(s.isSucceed() ? "成功" : "失败");
            d.setUsername(s.getUsername());
            reslut.add(d);
        });
        return reslut;
    }
}
