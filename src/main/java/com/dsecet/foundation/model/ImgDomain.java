package com.dsecet.foundation.model;

import com.dsecet.helper.ImageHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Created on 2016年4月21日
 * <p>Title:       重构</p>
 * <p>Description: 添加图片函数</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class ImgDomain<T extends Serializable> extends Domain<T>{
	@Setter
	private String imgUrl;
	
	
	public  String getImgUrl(){
		return replaceImgPath(this.imgUrl);
	}

	private String replaceImgPath(String path) {
		if(!StringUtils.isBlank(path)){
			if(!StringUtils.isBlank(ImageHelper.extension)) path+=ImageHelper.extension;
	    		return path.replaceAll("\\\\", "/");
			}
		else
			return "";
	}
    
}
