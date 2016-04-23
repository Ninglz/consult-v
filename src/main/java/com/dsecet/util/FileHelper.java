package com.dsecet.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

/**
 * @author: lxp
 * Date: 2015/5/12 11:31
 * 66cf-v2
 */
@Slf4j
public final class FileHelper{

    public static final int BUFFER_SIZE = 4096;

    private static final String EXTENSION_SEPARATOR = ".";

    public static String getPathString(String... names){
        checkNull(names, "names must be not null");
        StringBuilder builder = new StringBuilder();
        for(String name : Arrays.asList(names)){
            builder.append("/").append(name);
        }
        return builder.toString();
    }

    public static String getExtension(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    /** return File if not exists then mkdir */
    public static File getDirFile(String... names) throws IOException{
        File currentFile = FileUtils.getFile(names);
        if(!currentFile.exists() && !currentFile.mkdirs()){
            throw  new IOException("Directory '" + currentFile + "' could not be created");
        }
        return currentFile;
    }

    /**  */
    public static File getFile(String...names) throws IOException{
        File currentFile = FileUtils.getFile(names);
        File parent = currentFile.getParentFile();
        if(!parent.exists() && !parent.mkdirs()){
            throw  new IOException("Directory '" + currentFile + "' could not be created");
        }
        if(!currentFile.exists()){
            currentFile.createNewFile();
        }
        return currentFile;
    }

    /**
     * 删除该文件夹下面所有的东西
     */
    public static void deleteAll(String path) throws IOException {
        try{
            FileUtils.deleteDirectory(new File(path));
        }catch(IOException e){
            log.error("FileHepler deleteAll exception:{}", e.toString());
        }
    }

    public static void cleanDirIfExsit(String dirPath) throws IOException{
        try{
            File currentFile = new File(dirPath);
            if(currentFile.exists()){
                FileUtils.cleanDirectory(new File(dirPath));
            }
        }catch(IOException e){
            log.error("FileHepler Clean directory exception:{}", e.toString());
        }
    }

    public static String readerToString(String filePath) throws IOException{
        try{
            File file = getFile(filePath);
            Reader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            return IOUtils.toString(in);
        }catch(IOException e){
            log.error("FileHepler file IOException :{} ", e.toString());
            return null;
        }
    }

    public static void writerStringToFile(String filePath, String content){
        File file = null;
        Writer out = null;
        try{
            file = getFile(filePath);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            IOUtils.write(content, out);
            out.flush();
        }catch(IOException e){
            log.error("FileHepler file IOException :{} ", e.toString());
        }finally{
            if(null != out){
                try{
                    out.close();
                }catch(IOException e){
                }
            }
        }
    }

    private static void copy(Reader in, Writer out) throws IOException {
        checkNull(in, "No Reader specified");
        checkNull(out, "No Writer specified");
        IOUtils.copy(in, out);
    }

    private static void checkNull(Object o, String message){
        if(null == o){
            throw new IllegalArgumentException(message);
        }
    }
    
    /**
     * <p>Description:以字节写入文件</p>
     * @author:宁良竹
     * @update: 2016年3月24日
     * @param path
     * @param file
     */
    public static void writeByteToFile(String path, MultipartFile file){
        try{
            FileUtils.writeByteArrayToFile(new File(path), file.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
