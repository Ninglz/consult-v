package com.dsecet.api.vo;

import lombok.Data;

/**
 * @author: lxp
 * Date: 2015/6/18 15:11
 * safeguard-v1
 */
@Data
public class VersionForm{

    private Long id;

    private String versionNumber;

    private String desc;

    private String uri;

    private boolean forced;
}
