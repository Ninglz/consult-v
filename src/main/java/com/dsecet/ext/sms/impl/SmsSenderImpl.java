package com.dsecet.ext.sms.impl;

import com.dsecet.ext.sms.SmsSender;
import com.dsecet.util.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Calendar;
import java.util.List;

/**
 * @author: lxl
 */
@Service
@Slf4j
public class SmsSenderImpl implements SmsSender {

    @Value("${sms.lingkai.crop.id}")
    private String corpId;

    @Value("${sms.lingkai.pwd}")
    private String pwd;

    @Value("${sms.lingkai.url.template}")
    private String urlTemplate;

    @Override
    public boolean send(String content,@NotNull @Size(min = 1) List<String> mobiles) {
       /* boolean result = baoSend(content, mobiles);
        if(!result) {
            result = lingKaiSend(content, mobiles);
        }*/
        return lingKaiSend(content, mobiles);
    }

  /*  private boolean baoSend(String content,@NotNull @Size(min = 1)  List<String> mobiles) {
        try {
            content = URLEncoder.encode(content, "UTF-8");
            URL url = new URL(String.format(baoUrlTemplate, baoUserName, EncryptUtils.md5(baoPassword), StringUtils.join(mobiles, ","), content));
            ReadableByteChannel channel = Channels.newChannel(url.openStream());
            ByteBuffer buffer = ByteBuffer.allocate(4);
            channel.read(buffer);
            buffer.flip();
            int backCode = Integer.valueOf(new String(buffer.array()).trim());
            log.debug("Sending registration baoSms for : " + StringUtils.join(mobiles, ",") + " content: " + content + ", return code is : " + backCode);
            buffer.clear();
            if (backCode == 0) return true;
            //30:密码错误 ,40:账号不存在,41:余额不足 ,42:帐号过期 ,43:IP地址限制
            *//*if(!(backCode == 30 || backCode == 40 || backCode == 41 || backCode == 42 || backCode == 43)) return true;*//*
            return false;
        } catch (Exception e){
            log.error("baoSms has exception" + e);
            return false;
        }
    }*/

    private boolean lingKaiSend(String content,@NotNull @Size(min = 1) List<String> mobiles) {
        try {
            content = URLEncoder.encode(content, "GB2312");
            String sendTime = DateFormatUtils.format(Calendar.getInstance(), "yyyyMMddHHmmss");
            URL url = new URL(String.format(urlTemplate, corpId, pwd, StringUtils.join(mobiles, ","), content, sendTime));
            ReadableByteChannel channel = Channels.newChannel(url.openStream());
            ByteBuffer buffer = ByteBuffer.allocate(4);
            channel.read(buffer);
            buffer.flip();
            int backCode = Integer.valueOf(new String(buffer.array()).trim());
            log.debug("Sending registration LingKaiSms for : " + StringUtils.join(mobiles, ",") + " content: " + content + ", return code is : " + backCode);
            buffer.clear();
            if (backCode >= 0) return true;
            return false;
        } catch (Exception e){
            log.error("lingKaiSms has exception" + e);
            return false;
        }
    }
}

