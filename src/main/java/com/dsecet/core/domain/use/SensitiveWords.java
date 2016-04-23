package com.dsecet.core.domain.use;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dsecet.foundation.model.Domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;

import lombok.Data;

/**
 * Created on 2015年10月16日
 * <p>Title:       咨询汇_敏感词/p>
 * <p>Description: 举报原因码表</p>
 * <p>Copyright:   Copyright (c) 2015</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@ApiModel
@SuppressWarnings("serial")
@Entity
@Table(name="sensitive_words")
@Data
public class SensitiveWords extends Domain<Long> {

    @JsonProperty("n")
    @Column(length = 20, nullable = false)
    private String name;


}
