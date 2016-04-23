package com.dsecet.admin.aop.annotation;

/**
 * Created by lxl on 15--06-15.
 */
import com.dsecet.core.domain.admin.OptsTraceLog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptsTrace {
    OptsTraceLog.OperationType value();
}