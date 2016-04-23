package com.dsecet.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

/**
 * @author: lxp
 * Date: 2015/4/8 11:03
 * 66cf-v2
 */
@ApiModel
@Setter
@Getter
@NoArgsConstructor
public class VersionVo{

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("url")
    private String url = "";

    @JsonProperty("category")
    private String category="";

    @JsonProperty("type")
    private String type = "";

    @JsonProperty("operator")
    private String operator;

    public VersionVo wapperUrl(String url){
        if(StringUtils.isBlank(url)){
            return this;
        }
        this.url = url;
        return this;
    }
}
