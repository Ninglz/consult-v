package com.dsecet.util;

import com.dsecet.util.image.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * User: liuchang
 * Date: 14-8-29
 */
@Component
public class SystemPropertiesCnf{
    @Autowired
    private Properties sysProperties;

    public String getProductImgStorePrefix() {
        return sysProperties.getProperty("product.image.store.file.prefix");
    }

    public int getImageWidth(ImageType imageType) {
        return Integer.valueOf(sysProperties.getProperty("image.width." + imageType.toString().toLowerCase()));
    }

    public int getImageHeight(ImageType imageType) {
        return Integer.valueOf(sysProperties.getProperty("image.height." + imageType.toString().toLowerCase()));
    }



}
