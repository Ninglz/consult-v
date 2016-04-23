package com.dsecet.admin.vo;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: lxp
 * Date: 2015/6/30 21:34
 * safeguard-v1
 */
@Data
public class ExportConsumerVo{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("cellNo")
    private String cellNo;

    @JsonProperty("recordUserd")
    private Long recordUserd;

    @JsonProperty("lastUsed")
    private String lastUsed;

    @JsonProperty("equipments")
    private List<String> equipments;

    public static ExportConsumerVo of(Consumer consumer){
        ExportConsumerVo c = new ExportConsumerVo();
        //c.cellNo = consumer.getCellNo();
        c.id = consumer.getId();
        c.lastUsed = TimeUtils.millisToDateStr(consumer.getLastSigned());
        return c;
    }

    public ExportConsumerVo buildForEquipment(List<String> m){
        this.equipments = m;
        return this;
    }

    public ExportConsumerVo buildForRecordUserd(Long count){
        this.recordUserd = count;

        return this;
    }

//    public ExportConsumerVo buildForlastUsed(long date){
//        this.lastUsed = TimeUtils.millisToDate(date, TimeUtils.DATA_FROMAT);;
//        return this;
//    }
}
