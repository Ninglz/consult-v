package com.dsecet.core.domain.mongo;

import com.dsecet.util.ModelUtils;
import com.dsecet.util.TimeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * @author: lxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "trend_data_share")
public class TrendDataShare{

    @Id
    private String id;

    @NotNull
    private String user;

    @NotNull
    private Long created;

    @NotNull
    private String data;

    @NotNull
    private String mac;

    @NotNull
    private Long from;

    @NotNull
    private Long to;

    @NotNull
    private int days;

    @NotNull
    private String analysis;

    @NotNull
    private String url;

    public static TrendDataShare of(int days,
                                    String mac,
                                    String data,
                                    String user,
                                    String analysis){
        TrendDataShare t = new TrendDataShare();
        t.setId(ModelUtils.getSerial());
        t.setData(data);
        t.setMac(mac);
        t.setFrom(TimeUtils.minusDays(TimeUtils.currentMillis(),days + 1));
        t.setTo(TimeUtils.minusDays(TimeUtils.currentMillis(),1));
        t.setDays(days);
        t.setCreated(TimeUtils.currentMillis());
        t.setUser(user);
        t.setAnalysis(analysis);
        t.setUrl("TEST");
        return t;
    }

    public TrendDataShare updateUrl(String url){
        this.url = url;
        return this;
    }

}
