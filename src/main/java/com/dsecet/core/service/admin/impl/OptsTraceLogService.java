package com.dsecet.core.service.admin.impl;

import com.dsecet.core.domain.admin.OptsTraceLog;
import com.dsecet.core.repository.admin.OptsTraceLogListRepository;
import com.dsecet.core.repository.admin.OptsTraceLogListRepositoryImpl;
import com.dsecet.core.repository.admin.OptsTraceLogRepository;
import com.dsecet.core.service.admin.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lxl on 14-12-124.
 */
@Service
@Transactional
public class OptsTraceLogService implements com.dsecet.core.service.admin.OptsTraceLogService {

    @Autowired
    OptsTraceLogRepository optsTraceLogRepository;

    @Autowired
    OptsTraceLogListRepository optsTraceLogListRepository;

    @Autowired
    StaffService staffService;

    @Override
    public Page<OptsTraceLog> findOptsTraceLogByConditions(String username, Long begDate,
                                                           Long endDate, String operation,
                                                           Pageable pageable){
        OptsTraceLogListRepositoryImpl.OptsTraceLogQueryBuilder builder = OptsTraceLogListRepositoryImpl.OptsTraceLogQueryBuilder.newBuilder();
        builder.usernameCondiftion(username)
                .createdQueryCondiftion(begDate,endDate)
                .operationCondiftion(operation);
        return optsTraceLogListRepository.findOptsTraceLogList(builder,pageable);
    }

    @Override
    public OptsTraceLog save(OptsTraceLog optsTraceLog) {
        return optsTraceLogRepository.save(optsTraceLog);
    }
}
