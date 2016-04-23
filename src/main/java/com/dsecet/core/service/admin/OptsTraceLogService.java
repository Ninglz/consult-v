package com.dsecet.core.service.admin;

import com.dsecet.core.domain.admin.OptsTraceLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lxl on 14-9-1.
 */
public interface OptsTraceLogService{

    OptsTraceLog save(OptsTraceLog optsTraceLog);

    public Page<OptsTraceLog> findOptsTraceLogByConditions(String username, Long begDate, Long endDate, String operation, Pageable pageable);


}
