package com.dsecet.foundation.model.query;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.use.Img;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import lombok.Data;

/**
 * 专家VO
 * @author heyue
 * */
@Data
public class ExpertsVo {

	@JsonProperty("id")
    private String id;
	
	@JsonProperty("name")
	private String name;
	
	/**
	 * 昵称
	 * */
	@JsonProperty("nickName")
	private String nickName;
	
	 @JsonProperty("userName")
	 private String userName;
	/**
	 * 头像
	 * */
	@JsonProperty("avatar")
	private String avatar;
	
	@JsonProperty("cell")
	private String cell;
	
	/**
	 * 专家级别
	 * */
	@JsonProperty("expertsLevels")
	private Integer expertsLevels;
	
	/**
	 * 咨询者级别
	 * */
	@JsonProperty("consumerLevels")
	private Integer consumerLevels;
	
	/**
	 * 专家上下线状态
	 * */
	@JsonProperty("state")
	private Long state;
	
	@JsonProperty("expertState")
	private String expertState;
	
	@JsonProperty("auditState")
	private boolean auditState;
	
	@JsonProperty("active")
    private String active;
	
	@JsonProperty("actived")
	private boolean actived;
	
	 /**
     * 汇点
     * */
    @JsonProperty("sink")
    private Double sink;
	
    /**
     * 积分
     * */
    @JsonProperty("integral")
    private Double integral;
    
    /**
     * 用户无效次数
     * */
    @JsonProperty("consumerInvalidTime")
    private Long consumerInvalidTime;
    
    /**
     * 用户有效次数
     * */
    @JsonProperty("consumerValidTime")
    private Long consumerValidTime;
    
    /**
     * 专家无效次数
     * */
    @JsonProperty("expertsInvalidTime")
    private Long expertsInvalidTime;
    
    /**
     * 专家有效次数
     * */
    @JsonProperty("expertsValidTime")
    private Long expertsValidTime;
    
    /**
     * 咨询者平均分
     * */
    @JsonProperty("cSocre")
    private Long cSocre;
    
    /**
     * 专家平均分
     * */
    @JsonProperty("eSocre")
    private Long eSocre;
	
    /**
     * 资历证书
     * */
    @JsonProperty("certificateImg")
    private String[] certificateImg;
    
    @JsonProperty("created")
    private String created;
    
    @JsonProperty("lastSigned")
    private String lastSigned;
    
    @JsonProperty("idCard")
    private String idCard;
    
	@JsonProperty("seniority")
	private int seniority;
	
	@JsonProperty("industry")
	private String industry;
	
	@JsonProperty("goodAt")
	private String goodAt;
	
	@JsonProperty("sex")
	private String sex;
	
	@JsonProperty("focusNo")
	private Long focusNo = 0L;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("handel")
	private Long handel;
	
	@JsonProperty("isTop")
	private Boolean isTop;
	
	@JsonProperty("rechargePrice")
    private Double rechargePrice;
    
	@JsonProperty("cashPrice")
    private Double cashPrice;
	
	public static List<ExpertsVo> of(Page<Experts> page){
        List<ExpertsVo> reslut = Lists.newArrayList();
        return page.getContent().stream().map(b -> ExpertsVo.of(b)).collect(Collectors.toList());
    }
	
	public static ExpertsVo of(Experts e){
		ExpertsVo vo = new ExpertsVo();
		vo.setId(String.valueOf(e.getId()));
		vo.setName(e.getRealName() == null?"":e.getRealName());
		vo.setNickName(e.getNickName());
		vo.setUserName(e.getUsername());
		vo.setAvatar(e.getAvatar() == null ?"":SystemProperty.webUrl + e.getAvatar());
		vo.setCell(e.getCellNo());
		vo.setExpertsLevels(e.getExperLevel());
		vo.setConsumerLevels(e.getConsumerLevel());
		vo.setActive(String.valueOf(e.isActive()));
		vo.setSink(e.getSink());
		vo.setIntegral(e.getIntegral());
		String handle ;
		switch (e.getHandle().toString()) {
		case "0":handle="空";break;
		case "1":handle="身份申请中";break;
		case "-1":handle="身份拒绝";break;
		case "2":handle="资料变更申请中";break;
		case "-2":handle="资料变更拒绝";break;
		default:handle="资料审核成功";break;
		}
		vo.setState(e.getState());
		vo.setExpertState(handle);
		vo.setHandel(e.getHandle());
		vo.setIsTop(e.getTop());
		vo.setConsumerInvalidTime(e.getConsumerInvalidTime());
		vo.setConsumerValidTime(e.getConsumerValidTime());
		vo.setExpertsInvalidTime(e.getExpertsInvalidTime());
		vo.setExpertsValidTime(e.getExpertsValidTime());
		vo.setRechargePrice(e.getConsumerDetails().getRechargePrice());
		vo.setCashPrice(e.getConsumerDetails().getCashPrice());
		vo.setSex(e.getSex() == 2L ? "" : e.getSex() == 1L ? "男" : "女");
		vo.setIdCard(e.getIdCard());
		vo.setCreated(TimeUtils.getDateToString(e.getCreated()));
		vo.setLastSigned(e.getLastModified() == null?"":TimeUtils.getDateToString(e.getLastModified()));
		vo.setFocusNo(e.getFocusNo());
		vo.setCity(e.getCity() == null ?"":e.getCity().getName());
		vo.setNickName(e.getNickName());
		List<ExpertsRecord> expertsRecord = e.getExpertsRecord();
		for (ExpertsRecord record : expertsRecord) {
			vo.setIndustry(record.getIndustry() == null ?"":record.getIndustry().getName());
			vo.setGoodAt(record.getGoodAt());
			vo.setAuditState(record.getAuditState());
			vo.setDescription(record.getDescription());
			vo.setActived(record.isActive());
			if(record.getImg() != null){
	    		int count =0;
	    		String[] str = new String[5];
	    		for (int i = 0; i < record.getImg().size(); i++) {
	    			Img img = record.getImg().get(i);
	    			str[count++] = SystemProperty.webUrl + img.getPath(); 
	    			vo.setCertificateImg(str);
	    		}
	    	}
		}
		return vo;
	}
}
