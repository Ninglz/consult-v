package com.dsecet.core.domain.admin;

import com.dsecet.foundation.model.Record;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Created by lxl on 15-06-15.
 */
@Table(name = "admin_opts_track_log")
@Entity
@Data
public class OptsTraceLog extends Record<Long>{

    @Column(name = "operation_id", nullable = false)
    private Long operationId;

    @NotBlank
    @Column(name = "username", nullable = false)
    private String username;

    @Column(length = 64)
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column
    private String params;

    @Column(nullable = false)
    private boolean succeed;

    @Lob
    @Column(name = "exception", nullable = true, length = 65535)
    private String exception;

    public enum OperationType {
        CUSTOMER_STATUS,
        STAFF_STATUS,
        STAFF_RESET_PASSWORD,
        STAFF_ADD,
        STAFF_EDIT,
        STAFF_DEL,
        STAFF_CHANGE_ROLE,
        CHANGE_ROLE_RESOURCE,
        NEWS_ADD,
        NEWS_EDIT,
        VERSION_CHANGE,
        VERSION_ADD,
        SYSTEM_CONFIG_CHANGE,
    }

}
