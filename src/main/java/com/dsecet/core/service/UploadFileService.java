package com.dsecet.core.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with 66cf-v2
 * User : Ting.Yao
 * Date : 2014/12/24.
 */
public interface UploadFileService {
    void save(String path, MultipartFile file) ;

    void del(String path);
}
