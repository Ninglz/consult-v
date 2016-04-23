package com.dsecet.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

/**
 * @author: lxp
 * Date: 2015/6/18 15:16
 * safeguard-v1
 */
@ApiModel
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class VersionRespones{

    @ApiModelProperty(value = "The version number", dataType = "string", required = true)
    @JsonProperty("versionNumber")
    private String versionNumber;

    @ApiModelProperty(value = "The description", dataType = "string", required = true)
    @JsonProperty("desc")
    private String desc;

    @ApiModelProperty(value = "Apk or hardware url", dataType = "string", required = true)
    @JsonProperty("url")
    @Setter(AccessLevel.PRIVATE)
    private String url;

    @ApiModelProperty(value = "Version type", dataType = "string", required = true)
    @JsonProperty("type")
    private String type;

    @ApiModelProperty(value = "Is forced", dataType = "boolean", required = true)
    @JsonProperty("forced")
    private boolean forced;

    public VersionRespones wapperUrl(String url){
         if(StringUtils.isBlank(url)){
             return this;
         }
         this.url = url;
        return this;
    }
}
