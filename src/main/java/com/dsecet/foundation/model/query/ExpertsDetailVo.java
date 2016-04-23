package com.dsecet.foundation.model.query;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.use.Img;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.core.service.SystemProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import lombok.Data;

/**
 * 专家VO
 * @author heyue
 * */
@Data
public class ExpertsDetailVo {

	@JsonProperty("id")
    private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("nickName")
	private String nickName;
	
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
	 * 专家上下线状态
	 * */
	@JsonProperty("state")
	private Long state;
	
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
     * 资历证书
     * */
    @JsonProperty("certificateImg")
    private String[] certificateImg;
    
    @JsonProperty("idCard")
    private String idCard;
    
	@JsonProperty("seniorityStart")
	private String seniorityStart;
	
	@JsonProperty("seniorityEnd")
	private String seniorityEnd;
	
	@JsonProperty("industryId")
	private Long industryId;
	
	@JsonProperty("industryName")
	private String industryName;
	
	@JsonProperty("goodAt")
	private String goodAt;
	
	@JsonProperty("sex")
	private Long sex;
	
	@JsonProperty("cityId")
	private Long cityId;
	
	@JsonProperty("provinceId")
	private Long  provinceId;
	
	@JsonProperty("cityName")
	private String cityName;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("alipayUserName")
	private String alipayUserName;
	
	@JsonProperty("expertsState")
	private String expertState;
	
	@JsonProperty("serviceCell")
	private String serviceCell;
	
	@JsonProperty("_handle")
	private Long _handle;
	
	public static List<ExpertsDetailVo> of(Page<Experts> page){
        List<ExpertsDetailVo> reslut = Lists.newArrayList();
        return page.getContent().stream().map(b -> ExpertsDetailVo.of(b)).collect(Collectors.toList());
    }
	
	public static ExpertsDetailVo of(Experts e){
		ExpertsDetailVo vo = new ExpertsDetailVo();
		vo.setId(e.getId());
		vo.setName(e.getRealName() == null?"":e.getRealName());
		vo.setCityName(e.getCity() == null?"":e.getCity().getName());
		vo.setSex(e.getSex());
		vo.set_handle(e.getHandle());
		vo.setNickName(e.getNickName());
		vo.setProvinceId(e.getCity()==null?null:e.getCity().provinceId());
		vo.setAlipayUserName(e.getAlipayUserName() == null?"":e.getAlipayUserName());
		String handle ;
		switch (e.getHandle().toString()) {
		case "0":handle="空";break;
		case "1":handle="身份申请中";break;
		case "-1":handle="身份拒绝";break;
		case "2":handle="资料变更申请中";break;
		case "-2":handle="资料变更拒绝";break;
		default:handle="资料审核成功";break;
		}
		vo.setExpertState(handle);
		vo.setAvatar(e.getAvatar() == null ?SystemProperty.webUrl +"/information/000.png":SystemProperty.webUrl + e.getAvatar());
		vo.setCell(e.getCellNo());
		vo.setExpertsLevels(e.getExperLevel());
		vo.setSink(e.getSink());
		vo.setIntegral(e.getIntegral());
		vo.setState(e.getState());
		vo.setServiceCell(SystemProperty.tel);
		if(e.getCity() != null){
			vo.setCityId(e.getCity().getId());
		}
		vo.setIdCard(e.getIdCard());
		List<ExpertsRecord> expertsRecord = e.getExpertsRecord();
		Collections.sort(expertsRecord);
		for (ExpertsRecord record : expertsRecord) {
			vo.setGoodAt(record.getGoodAt() == null ?"":record.getGoodAt());
			vo.setDescription(record.getDescription() == null ?"":record.getDescription());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			if(record.getSeniorityStart() != null){
				vo.setSeniorityStart(sdf.format(record.getSeniorityStart()));
			}
			if(record.getSeniorityEnd() != null){
				vo.setSeniorityEnd(sdf.format(record.getSeniorityEnd()));
			}
			if(record.getIndustry() != null){
				vo.setIndustryId(record.getIndustry().getId());
			}
			vo.setIndustryName(record.getIndustry()==null?"":record.getIndustry().getName());
			List<Img> list = record.getImg();
			for (Img mediaDossier : list) {
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
		}
		return vo;
	}
}
