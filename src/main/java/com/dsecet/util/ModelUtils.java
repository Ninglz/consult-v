package com.dsecet.util;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.Customer;
import com.dsecet.foundation.model.ErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.ReflectionUtils;

import javax.validation.constraints.NotNull;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author: Fablenas
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModelUtils{

    public static final String getSerial(){
        return UUID.randomUUID().toString();
    }


    public static final String getEmptyStr(String s){
        return StringUtils.isBlank(s) ? "" : s;
    }

    public static final long parseToMillis(String formatDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date dt = sdf.parse(formatDate);
        return dt.getTime();
    }

    public static final long getBeforeDaysMillis(long l) throws ParseException{
        return parseToMillis(getBeforeDays(l).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));
    }

    public static final LocalDateTime getBeforeDays(long l){
        return LocalDateTime.of(LocalDate.now().minus(l, ChronoUnit.DAYS), LocalTime.MIN);
    }

    public static final long getBeforeMonthsMillis(long l) throws ParseException{
        return parseToMillis(getBeforeMonths(l).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));
    }

    public static final LocalDateTime getBeforeMonths(long l){
        return LocalDateTime.of(LocalDate.now().minus(l, ChronoUnit.MONTHS),LocalTime.MIN);
    }

    public static final void checkNull(Object o){
        if(Objects.isNull(o))
            throw new RespondableException(ErrorCode.ENTITY_NO_SUCH);
    }
    
    public static final void checkIsNull(Object o){
    	ErrorCodeMap map = new ErrorCodeMap();
        if(Objects.isNull(o));
            throw new RespondableException(ErrorCode.ENTITY_NO_SUCH,
            		map.toErrorString(ErrorCode.TOKEN_INVALID));
    }
    
    public static final void checkToken(Object o){
    	ErrorCodeMap map = new ErrorCodeMap();
        if(Objects.isNull(o));
            throw new RespondableException(ErrorCode.TOKEN_INVALID,
            		map.toErrorString(ErrorCode.TOKEN_INVALID));
    }

    public static final void checkLock(Consumer consumer){
    	checkNull(consumer);
    	if(!consumer.isActive())
    		throw new RespondableException(ErrorCode.CUSTOMER_HAS_LOCKED,"The user has been locked");
    }


    /**
     * @param dest
     * @param orig
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    @SuppressWarnings("rawtypes")
    public static final void copyNotNullProperties(@NotNull Object dest,
                                                   @NotNull Object orig) throws IllegalAccessException,
            InvocationTargetException{
        BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
        // Copy the properties, converting as necessary
        if(orig instanceof DynaBean){
            DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass()
                    .getDynaProperties();
            for(int i = 0; i < origDescriptors.length; i++){
                String name = origDescriptors[i].getName();
                // Need to check isReadable() for WrapDynaBean
                // (see Jira issue# BEANUTILS-61)
                if(beanUtils.getPropertyUtils().isReadable(orig, name)
                        && beanUtils.getPropertyUtils().isWriteable(dest, name)){
                    Object value = ((DynaBean) orig).get(name);
                    beanUtils.copyProperty(dest, name, value);
                }
            }
        }else if(orig instanceof Map){
            Iterator entries = ((Map) orig).entrySet().iterator();
            while(entries.hasNext()){
                Map.Entry entry = (Map.Entry) entries.next();
                String name = (String) entry.getKey();
                if(beanUtils.getPropertyUtils().isWriteable(dest, name)){
                    beanUtils.copyProperty(dest, name, entry.getValue());
                }
            }
        }else /* if (orig is a standard JavaBean) */{
            PropertyDescriptor[] origDescriptors = beanUtils.getPropertyUtils()
                    .getPropertyDescriptors(orig);
            for(int i = 0; i < origDescriptors.length; i++){
                String name = origDescriptors[i].getName();
                if("class".equals(name)){
                    continue; // No point in trying to set an object's class
                }
                if(beanUtils.getPropertyUtils().isReadable(orig, name)
                        && beanUtils.getPropertyUtils().isWriteable(dest, name)){
                    try{
                        Object value = beanUtils.getPropertyUtils()
                                .getSimpleProperty(orig, name);
                        if(null == value)
                            continue;
                        Class<? extends Object> valueType = value.getClass();
                        if(value instanceof Collection || value instanceof Map){
                            Method method = ReflectionUtils.findMethod(
                                    valueType, "size");
                            int size = (int) method.invoke(value);
                            if(0 == size)
                                continue;
                        }
                        if(valueType.isArray())
                            if(0 == Array.getLength(value))
                                continue;
                        beanUtils.copyProperty(dest, name, value);
                    }catch(NoSuchMethodException e){
                        // Should not happen
                    }
                }
            }
        }
    }

    public static void checkCustomer(Customer o) {
            if(Objects.isNull(o)){
            throw new RespondableException(ErrorCode.USER_NO_SUCH);}
    }

    public static void main(String[] args){
        PathMatcher pm = new AntPathMatcher();
        System.out.println(pm.match("/admin/new/**","/admin/new/"));
    }
}
