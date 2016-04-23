package com.dsecet.foundation.model.query;

import com.dsecet.core.domain.admin.Resources;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: lxl
 */
@Data
public class ResourcesVo{

    @JsonProperty("name")
    private String name;

    @JsonProperty("patternUrl")
    private String patternUrl;

    public static List<ResourcesVo> of(Page<Resources> resourcesPage){
        List<ResourcesVo> reslut = Lists.newArrayList();
        List<Resources> l = resourcesPage.getContent();
        l.stream().forEach(s -> {
            ResourcesVo d = new ResourcesVo();
            d.setName(s.getName());
            d.setPatternUrl(s.getPatternUrl());
            reslut.add(d);
        });
        return reslut;
    }
}
