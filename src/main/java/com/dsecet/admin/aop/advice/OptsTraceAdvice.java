package com.dsecet.admin.aop.advice;

import com.dsecet.admin.aop.annotation.OptsTrace;
import com.dsecet.core.domain.admin.OptsTraceLog;
import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.service.admin.OptsTraceLogService;
import com.dsecet.core.service.admin.StaffService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * Created by lxl on 14-8-31.
 */
@Component
@Aspect
public class OptsTraceAdvice {

    @Autowired
    OptsTraceLogService optsTraceLogService;

    @Autowired
    StaffService staffService;

    @Autowired
    private ObjectMapper objectMapper;

    @Around("within(@org.springframework.stereotype.Controller *) && @annotation(optsTrace)")
    public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint, OptsTrace optsTrace) throws Throwable{
        OptsTraceLog.OperationType operation = optsTrace.value();
        Object[] args = proceedingJoinPoint.getArgs();
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Staff staff = staffService.getByUserName(username);
        OptsTraceLog optsTraceLog = new OptsTraceLog();
        optsTraceLog.setUsername(staff.getUsername());
        optsTraceLog.setOperationType(operation);
        optsTraceLog.setSucceed(true);
        optsTraceLog.setOperationId(staff.getId());
        optsTraceLog.setParams(objectMapper.writeValueAsString(args));
        try {
            Object result = proceedingJoinPoint.proceed();
            return result;
        } catch (Exception e) {
            optsTraceLog.setSucceed(false);
            optsTraceLog.setException(e.getMessage());
            throw e;
        } finally {
            try{
                optsTraceLogService.save(optsTraceLog);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
