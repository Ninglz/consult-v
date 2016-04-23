package com.dsecet.util;

import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.ErrorCode;
import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumSet;
import java.util.Map;

/**
 * @author: lxp
 * Date: 2015/6/12 16:59
 * safeguard-v1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EnumUtils{

    public static <T extends Enum<T>> T valueOf(String name, Class<T> clazz){
        try{
            return Enum.valueOf(clazz, name);
        }catch(IllegalArgumentException e){
            throw new RespondableException(ErrorCode.ILLEGAl_PARAMETER);
        }
    }

    public static <T extends Enum<T>> Map<String, String> wapperMap(String messageKeyPrefix, Class<T> clazz){
        Map<String, String> map = Maps.newLinkedHashMap();
        EnumSet<T> enumSet = EnumSet.allOf(clazz);
        enumSet.forEach(t -> {
            String name = t.name();
            map.put(messageKeyPrefix + name.toLowerCase(), t.name());
        });
        return map;
    }
}
