package com.dsecet.foundation.model;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * User: liuchang
 * Date: 14/12/15
 */
@Embeddable
@Data
public class MediaItem{

    @Column(name = "file_path")
    String filePath;

    @Column(name = "file_name")
    String fileName;

    @Column(name = "original_name")
    String originalName;


    public void setOriginalName(String originalName){
        String str[] = originalName.split("\\.");
        String extension = str[1];
        String frontName = str[0];
        if(frontName.length() > 50){
            frontName = frontName.substring(0, 50);
        }
        this.originalName = frontName + "." + extension;
    }

    public static MediaItem of(@NotNull String filePath,
                               @NotNull String fileName,
                               @NotNull String originalName
                               ){
        MediaItem mediaItem = new MediaItem();
        mediaItem.setFilePath(filePath);
        mediaItem.setFileName(fileName);
        String str[] = originalName.split("\\.");
        String extension = str[1];
        String frontName = str[0];
        if(frontName.length() > 50){
            frontName = frontName.substring(0, 50);
        }
        mediaItem.setOriginalName(frontName + "." + extension);
        return mediaItem;
    }
    public boolean isExisted(){
        return StringUtils.isNotBlank(filePath) && StringUtils.isNotBlank(fileName) && StringUtils.isNotBlank(originalName);
    }
}
