package com.dsecet.foundation.model.query;

import com.dsecet.core.domain.admin.Resources;
import com.dsecet.core.domain.admin.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lxl
 */
@Data
public class RoleVo{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("resources")
    private String resources;

    public static List<RoleVo> of(Page<Role> rolePage){
        List<RoleVo> reslut = Lists.newArrayList();
        List<Role> l = rolePage.getContent();
        l.stream().forEach(s -> {
            RoleVo d = new RoleVo();
            d.setId(s.getId());
            d.setName(s.getRoleName());
            d.setCode(s.getRoleCode());
            d.setResources(Joiner.on(",").join(s.getResourceses().stream().map(Resources::getName).collect(Collectors.toList()).toArray()));
            reslut.add(d);
        });
        return reslut;
    }

    public static RoleVo of(Role s){
        RoleVo d = new RoleVo();
        d.setId(s.getId());
        d.setName(s.getRoleName());
        d.setCode(s.getRoleCode());
        d.setResources(Longs.join(",", Longs.toArray(s.getResourceses().stream().map(p -> p.getId()).collect(Collectors.toList()))));
        return d;
    }
}
