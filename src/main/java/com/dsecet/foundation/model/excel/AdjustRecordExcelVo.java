package com.dsecet.foundation.model.excel;

import lombok.Data;

/**
 * @author: lxl
 */
@Data
public class AdjustRecordExcelVo{

    private String mac;
    private String adjustDate;
    private int batch;
    private String voltageFirstMin;
    private String voltageFirstMax;
    private String voltageSecondMin;
    private String voltageSecondMax;
    private String voltageThirdMin;
    private String voltageThirdMax;
    private String voltageFourthMin;
    private String voltageFourthMax;
}
