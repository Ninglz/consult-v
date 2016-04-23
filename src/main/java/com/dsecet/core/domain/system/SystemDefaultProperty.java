package com.dsecet.core.domain.system;

import com.dsecet.foundation.model.Domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created on 2016年3月2日
 * <p>Title:       咨询汇_系统配置/p>
 * <p>Description: 系统配置实体</p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "system_default_property")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SystemDefaultProperty extends Domain<Long>{

    @Column(name = "system_host_url")
    @JsonProperty("host_url")
    @NotNull
    private String systemHostUrl;

    @Column(name = "storage_file_prefix")
    @JsonProperty("storage_prefix")
    @NotNull
    private String storageFilePrefix;

    @Column(name = "app_prefix", nullable = false)
    @JsonProperty("app_prefix")
    @NotBlank
    private String appPrefix;
    
    /**
     * 映射地址 
     * */
    @Column(name = "website_static_url", nullable = false)
    @JsonProperty("website_static_url")
    @NotBlank
    private String websiteStaticUrl;
    
    @Column(name = "news_prefix", nullable = false)
    @JsonProperty("news_prefix")
    @NotBlank
    private String newsPrefix;
    
    @Column(name = "andorid_url", nullable = false)
    @JsonProperty("andorid_url")
    @NotBlank
    private String andoridUrl;
    
    @Column(name = "ios_url", nullable = false)
    @JsonProperty("ios_url")
    @NotBlank
    private String iosUrl;
    
    /**
     * 注册积分
     * */
    @Column(name = "free_integral", nullable = true)
    @JsonProperty("free_integral")
    private Double freeIntegral;
    
    /**
     * 免费时长    专家=用户
     * */
    @Column(name = "free_time", nullable = true)
    @JsonProperty("free_time")
    private Long freeTime;
    
    /**
     * 计费时间
     * */
    @Column(name = "pay_time", nullable = false)
    @JsonProperty("pay_time")
    private Long payTime;
    
    /**
     * 最大交互峰值
     * */
    @Column(name = "max_num", nullable = false)
    @JsonProperty("max_num")
    private Long maxNum;
    
    /**
     * 客服联系电话
     * */
    @Column(name = "cell", nullable = false)
    @JsonProperty("cell")
    private String cell;
    
    /**
     * 函数
     * */
    @Column(name = "function", nullable = true)
    @JsonProperty("function")
    private String function;
    
    /**
     * 咨询师临时时间起
     * */
    @Column(name = "time_form", nullable = true)
    @JsonProperty("time_form")
    private Long timeForm;
    
    /**
     * 咨询师临时时间止
     * */
    @Column(name = "time_to", nullable = true)
    @JsonProperty("time_to")
    private Long timeTo;
    
    /**
     * 咨询者临时时间起
     * */
    @Column(name = "conusmer_time_form", nullable = true)
    @JsonProperty("consumer_time_form")
    private Long consumerTimeForm;
    
    /**
     * 咨询者临时时间止
     * */
    @Column(name = "conusmer_time_to", nullable = true)
    @JsonProperty("conusmer_time_to")
    private Long consumerTimeTo;
    
    /**
     * 咨询师临时比例
     * */
    @Column(name = "proportion", nullable = false)
    @JsonProperty("proportion")
    private Double proportion;
    /**
     * 咨询者临时比例
     * */
    @Column(name = "consumerProportion", nullable = false)
    @JsonProperty("consumerProportion")
    private Double consumerProportion;
    
    /**
     * 专家单价
     * */
    @Column(name = "price", nullable = false)
    @JsonProperty("price")
    private Double price;
    
    /**
     * 专家起步价
     * */
    @Column(name = "start_price", nullable = false)
    @JsonProperty("start_price")
    private Double startPrice;
    /**
     * 咨询者单价
     * */
    @Column(name = "consumerPrice", nullable = false)
    @JsonProperty("consumerPrice")
    private Double consumerPrice;
    
    /**
     * 咨询者起步价
     * */
    @Column(name = "consumer_start_price", nullable = false)
    @JsonProperty("consumer_start_price")
    private Double consumerStartPrice;
    /**
     * 个人信息前缀
     * */
    @Column(name = "information_prefix", nullable = false)
    @JsonProperty("information_prefix")
    private String informationPrefix;
    /**
     * 活动图片前缀
     * */
    @Column(name = "activity_prefix", nullable = false)
    @JsonProperty("activity_prefix")
    private String activityPrefix;
}
