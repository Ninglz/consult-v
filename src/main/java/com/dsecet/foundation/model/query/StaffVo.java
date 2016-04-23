package com.dsecet.foundation.model.query;

import com.dsecet.core.domain.admin.Role;
import com.dsecet.core.domain.admin.Staff;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class StaffVo{

    @JsonProperty("username")
    private String username;

    @JsonProperty("isRoot")
    private String isRoot;

    @JsonProperty("active")
    private String active;

    @JsonProperty("id")
    private String id;

    @JsonProperty("roles")
    private String roles;

    public static List<StaffVo> of(Page<Staff> staffVoPage){
        List<StaffVo> reslut = Lists.newArrayList();
        List<Staff> l = staffVoPage.getContent();
        l.stream().forEach(s -> {
            StaffVo d = new StaffVo();
            d.setUsername(s.getUsername());
            d.setRoles(s.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()).toString());
            d.setActive(String.valueOf(s.isActive()));
            d.setId(String.valueOf(s.getId()));
            d.setIsRoot(String.valueOf(s.isRoot()));
            reslut.add(d);
        });
        return reslut;
    }

    public static StaffVo of(Staff s){
        StaffVo d = new StaffVo();
        d.setUsername(s.getUsername());
        d.setRoles(Longs.join(",", Longs.toArray(s.getRoles().stream().map(p -> p.getId()).collect(Collectors.toList()))));
        d.setActive(String.valueOf(s.isActive()));
        d.setId(String.valueOf(s.getId()));
        d.setIsRoot(String.valueOf(s.isRoot()));
        return d;
    }
}
