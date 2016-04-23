package com.dsecet.helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.service.SystemProperty;
import com.dsecet.util.FileHelper;


public class ImageHelper {
	
	public static String extension;
	
	public static  String getExtension(MultipartFile img){
		String originalName = img.getOriginalFilename();
		extension =originalName.substring(originalName.lastIndexOf("."));
		return extension;
	}
	
	
    public static void writeByteToFile(String path, MultipartFile file){
        try{
            FileUtils.writeByteArrayToFile(new File(SystemProperty.storageFilePrefix +path+getExtension(file)), file.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    

    /**
     * <p>Description:删除该文件对应文件夹下面所有的东西</p>
     * @author:宁良竹
     * @update: 2016年4月21日
     * @param path
     * @param file
     */
    public static void deleteAllByFile(String path, MultipartFile file){
        try{
        	 FileUtils.deleteDirectory(new File(SystemProperty.storageFilePrefix+path).getParentFile());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void deleteAllByFileAndWriteByteToFile(String path, MultipartFile file){
    	deleteAllByFile(path, file);
    	writeByteToFile(path, file);
    }

}
