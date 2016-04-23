package com.dsecet.foundation.model.excel;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: lxl
 */
@Data
public class SalesRecordExcelVo{

    private String name;

    private String tbId;

    private String cellNo;

    private String purchaseDate;

    private int orderQuantity;

    private int quantityShipped;

    private BigDecimal price;

    private BigDecimal rebate;

    private BigDecimal salary;

    private BigDecimal shareholderPayment;

    private String shipAddress;

    private String delivery;

    private String referee;

    private String storeName;

    private String remarks;

}
