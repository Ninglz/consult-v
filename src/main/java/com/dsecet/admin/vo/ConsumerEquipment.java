package com.dsecet.admin.vo;

import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: lxp
 * Date: 2015/6/25 15:38
 * safeguard-v1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ConsumerEquipment{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("equipmentId")
    private Long equipmentId;

    @JsonProperty("cellNo")
    private String cellNo;

    @JsonProperty("mac")
    private String mac;

    @JsonProperty("type")
    private String type;

    @JsonProperty("counting")
    private long counting;

    @JsonProperty("lastTime")
    private String lastTime;

    public static ConsumerEquipment of(Long id, String mac, String type){
        ConsumerEquipment consumerEquipment = new ConsumerEquipment();
        consumerEquipment.mac = mac;
        consumerEquipment.id = id;
        consumerEquipment.type = type;
        return consumerEquipment;
    }



    public ConsumerEquipment buildLastTime(long lastTime){
        this.lastTime = TimeUtils.millisToDate(lastTime, TimeUtils.DATA_FROMAT);
        return this;
    }

    public ConsumerEquipment buildCounting(long counting){
        this.counting = counting;
        return this;
    }
}
