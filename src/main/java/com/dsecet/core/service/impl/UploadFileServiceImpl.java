package com.dsecet.core.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.service.UploadFileService;

import java.io.File;
import java.io.IOException;

/**
 * Created with 66cf-v2
 * User : Ting.Yao
 * Date : 2014/12/24.
 */
@Service
public class UploadFileServiceImpl implements UploadFileService{
    @Override
    public void save(String path, MultipartFile file){
        try{
            FileUtils.writeByteArrayToFile(new File(path), file.getBytes());

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void del(String path){
        FileUtils.deleteQuietly(new File(path));
    }
}
