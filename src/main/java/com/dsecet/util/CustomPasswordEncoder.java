package com.dsecet.util;

import org.springframework.security.authentication.encoding.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder{

    @Override
    public String encodePassword(String rawPass, Object salt){
       //TODO setPasswordEncoder初始化调用encodePassword方法此时salt为空 产生冲突
        String fixedSalt = null == salt || !salt.toString().startsWith("$6$") ? "$6$" + salt : salt.toString();
        return EncryptUtils.password(rawPass, fixedSalt);
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt){
         if(encPass.equals(EncryptUtils.password(rawPass, (String)salt)))
            return true;
        else
            return false;
    }

}
