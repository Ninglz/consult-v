package com.dsecet.core.service;

import com.dsecet.util.image.ImageType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.File;
import java.nio.file.Path;

/**
 * @author: lxl
 */
public interface ImageService{

    String saveNewsImage(Long newsId, MultipartFile file);

    void saveAvatarImage(String path, File oriFile);

    void saveImage(String path, ImageType imageType, File oriFile);

    void saveImage(String path, int width, int height, File oriFile);

    Path getImage(String fileName, ImageType imageType);

    Path getImagePrevious(ServletRequest request, String fileName, ImageType imageType);
}
