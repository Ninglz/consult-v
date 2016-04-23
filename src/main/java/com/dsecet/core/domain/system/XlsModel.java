package com.dsecet.core.domain.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: lxp
 * Date: 2015/6/27 14:47
 * safeguard-v1
 */
@Entity
@Getter
@Setter
@Table(name = "xls_model")
@ToString
@EqualsAndHashCode
public class XlsModel implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "category", nullable = false)
    @Enumerated
    private ExportCategory category;

    @JsonProperty("templatePath")
    @Column(name = "template_path", nullable = false)
    private String templatePath;

    public enum ExportCategory {
        EQUIPMENT_STATISTICS,
        EQUIPMENT_RECORD,
        EQUIPMENT_USE,
        EQUIPMENT_CONSUMER_RECORD,
        CONSUMER_LIST,
        CONSUMER_DETAIL,
        INFRARED_TEMPLATE,
        CHANNEL_TEMPLATE,
        ADJUST_TEMPLATE
    }
}
