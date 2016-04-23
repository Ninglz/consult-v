package com.dsecet.foundation.model.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.version.Version;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 版本VO
 * @author heyue
 * */
@Data
public class VersionListVo {

	@JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
    
    @JsonProperty("description")
    private String description;
    
    @JsonProperty("forceUpdate")
    private String forceUpdate;

    @JsonProperty("downUrl")
    private String downUrl;
    
    @JsonProperty("created")
    private String created;
    
    
    public static List<VersionListVo> of(Page<Version> page){
        return page.getContent().stream().map(b -> VersionListVo.of(b)).collect(Collectors.toList());
    }

    public static VersionListVo of(Version v){
        VersionListVo vo = new VersionListVo();
        vo.setId(v.getId());
        vo.setName(v.getName());
        vo.setDescription(v.getShortDescription());
        vo.setDownUrl(v.getDownUrl().getFilePath());
        vo.setForceUpdate(v.isForceUpdate() == true?"是":"否");
        vo.setCreated(TimeUtils.millisToDateTime(v.getCreated(), "yyyy-MM-dd HH:mm:ss"));
        return vo;
    }
}
