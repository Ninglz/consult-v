package com.dsecet.core.service.impl;

import com.dsecet.core.service.ImageService;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import com.dsecet.util.SystemPropertiesCnf;
import com.dsecet.util.image.ImageType;
import com.dsecet.util.image.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



/**
 * User: lxl
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private SystemPropertiesCnf systemPropertiesCnf;

    @Autowired
    private SystemDefaultPropertyService systemDefaultPropertyService;


    @Override
    public String saveNewsImage(Long newsId, MultipartFile file){
         return null;
    }

    @Override
    public void saveAvatarImage(String path, File oriFile){
        saveImage(path, ImageType.AVATAR, oriFile);
    }

    @Override
    public void saveImage(String path, ImageType imageType, File oriFile){
        int width = systemPropertiesCnf.getImageWidth(imageType);
        int height = systemPropertiesCnf.getImageHeight(imageType);
        saveImage(path, width, height, oriFile);
    }

    @Override
    public void saveImage(String path, int width, int height, File oriFile){
        String extension = path.substring(path.lastIndexOf(".") + 1);
        String scaleImageName = path.replace("." + extension, "") + "_" + width + "x" + height + "." + extension;
        ImageUtil.resize(oriFile, new File(scaleImageName), width, height, 1.0f);
    }

    @Override
    public Path getImage(String fileName, ImageType imageType){
        String prefix = systemPropertiesCnf.getProductImgStorePrefix();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String fileNameWithOutExt = fileName.substring(0, fileName.lastIndexOf("."));
        String filePath;
        if(imageType != null){
            filePath = prefix + fileNameWithOutExt + "_" + systemPropertiesCnf.getImageWidth(imageType) + "x" + systemPropertiesCnf.getImageHeight(imageType) + extension;
        }else{
            filePath = prefix + fileName;
        }
        Path path = Paths.get(filePath);
        return Files.notExists(path) ? null : path;
    }

    @Override
    public Path getImagePrevious(ServletRequest request, String fileName, ImageType imageType){
        String filePath = request.getServletContext().getRealPath("/resources/website/images/" + fileName);
        Path path = Paths.get(filePath);
        return Files.notExists(path) ? null : path;
    }
}
