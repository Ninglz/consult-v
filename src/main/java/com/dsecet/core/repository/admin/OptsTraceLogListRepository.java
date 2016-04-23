package com.dsecet.core.repository.admin;

import com.dsecet.core.domain.admin.OptsTraceLog;
import com.dsecet.core.repository.RecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * User: lxl
 * Date: 14/12/11
 */
@NoRepositoryBean
public interface OptsTraceLogListRepository extends RecordRepository<OptsTraceLog>{
    Page<OptsTraceLog> findOptsTraceLogList(OptsTraceLogListRepositoryImpl.OptsTraceLogQueryBuilder builder, Pageable pageable);
}
