package com.dsecet.api.vo;

import com.dsecet.core.domain.news.News;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: lxp
 * Date: 2015/6/15 17:04
 * safeguard-v1
 */
@ApiModel
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode
public class NewsResponse{

    @ApiModelProperty(value = " News main tile", required = true, position = 0)
    @JsonProperty("nm")
    @NotBlank
    private String mainTitle;

    @ApiModelProperty(value = "News summary", required = true, position = 1)
    @JsonProperty("ns")
    @NotBlank
    private String subTitle;

    @ApiModelProperty(value = "News website url", required = true, position = 2)
    @JsonProperty("url")
    @NotBlank
    private String url;

    public static NewsResponse of(News news){
        NewsResponse response = new NewsResponse();
        //response.setMainTitle(news.getMainTitle());
        //response.setSubTitle(news.getSubTitle());
        return response;
    }

    public NewsResponse buildViewURL(String url){
        this.url = url;
        return this;
    }
}
