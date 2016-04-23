package com.dsecet.admin.controller;

import com.dsecet.core.service.ImageService;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: liuchang
 * Date: 14-8-28
 */
@Controller
public class ImageUploadController{

    @Autowired
    private SystemDefaultPropertyService systemDefaultPropertyService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/news/img/upload1")
    public void upload(@RequestParam("newsId") Long newsId, @RequestParam("upload") MultipartFile file, @RequestParam("CKEditorFuncNum") String funcNum, HttpServletResponse response) throws IOException {
        String viewPath = imageService.saveNewsImage(newsId, file);
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setHeader("X-Content-Type-Options", "MIME-sniffing");
        response.getWriter().write("<script>window.parent.CKEDITOR.tools.callFunction(" + funcNum + ", '" + viewPath + "')</script>");
    }

}
