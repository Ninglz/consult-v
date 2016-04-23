package com.dsecet.core.domain.mongo;

import javax.persistence.Embeddable;

/**
 * @author: lxl
 */
@Embeddable
public enum DataType{
    TEMPERATURE,
    FORMALDEHYDE,
    HUMIDITY,
    PM,
    TVOC,
    CARBON_DIOXIDE,
    CARBON_MONOXIDE,
}
