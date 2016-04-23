package com.dsecet.foundation.model.query;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: lxl
 */
@Data
public class ConsumerVo{

    @JsonProperty("sex")
    private Long sex;
    
    @JsonProperty("userName")
    private String userName;
    
    /**
     * 真实姓名
     */
    @JsonProperty("nickName")
    private String nickName;
    
    @JsonProperty("cellNo")
    private String cellNo;
    
    @JsonProperty("active")
    private String active;
    
    @JsonProperty("locked")
    private String locked;
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("lastSigned")
    private String lastSigned;
    
    @JsonProperty("created")
    private String created;
    
    @JsonProperty("avatar")
    private String avatar;
    
    @JsonProperty("address")
    private String address;
    
    /**
     * 咨询者级别
     */
    @JsonProperty("consuemrLevels")
    private String consuemrLevels;
    
    /**
     * 专家级别
     */
    @JsonProperty("expertsLevels")
    private String expertsLevels;
    
    /**
     * 专家上下状态
     */
    @JsonProperty("expertState")
    private String expertState;
    
    /**
     * 汇点
     */
    @JsonProperty("sink")
    private String sink;
    
    /**
     * 积分
     */
    @JsonProperty("integral")
    private String integral;
    
    /**
     * 专家状态：：空，身份申请中，身份拒绝，资料变更申请中，资料变更拒绝
     */
    @JsonProperty("expertHandle")
    private String expertHandle;
    
    public static List<ConsumerVo> of(Page<Consumer> customersPage){
        List<ConsumerVo> reslut = Lists.newArrayList();
        customersPage.getContent().stream().forEach(
        		s -> {reslut.add(ConsumerVo.of(s));});
        return reslut;
    }

    public static ConsumerVo of(Consumer s){
        ConsumerVo d = new ConsumerVo();
        d.setUserName(s.getUsername());
        d.setNickName(s.getNickName());
        d.setActive(String.valueOf(s.isActive()));
        d.setLocked(String.valueOf(s.isLocked()));
        d.setId(String.valueOf(s.getId()));
        d.setConsuemrLevels(s.getConsumerLevel().toString());
        d.setExpertsLevels(s.getExperLevel().toString());
        d.setCreated(TimeUtils.millisToDateStr(s.getCreated()));
        d.setLastSigned(s.getLastSigned() == null ? "" : TimeUtils.millisToDateStr(s.getLastSigned()));
        d.setSex(s.getSex());
        if(d.getAvatar()!=null){
        	d.setAvatar(s.getAvatar());
        }
        d.setAddress(s.getCity()==null?"":s.getCity().getName());
        d.setSink(s.getSink().toString());
        d.setIntegral(s.getIntegral().toString());
        return d;
    }
}
