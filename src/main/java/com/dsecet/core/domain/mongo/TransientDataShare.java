package com.dsecet.core.domain.mongo;

import com.dsecet.util.ModelUtils;
import com.dsecet.util.TimeUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;

/**
 * @author: lxl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transient_data_share")
public class TransientDataShare{

    @Id
    @Indexed
    private String id;

    @NotNull
    private String user;

    @NotNull
    private Long created;

    @NotNull
    @Embedded
    private DataType type;

    @NotNull
    private String data;

    @NotNull
    private String analysis;

    @NotNull
    private String url;

    public static TransientDataShare of(DataType dataType,
                                        String data,
                                        String user,
                                        String analysis){
        TransientDataShare t = new TransientDataShare();
        t.setId(ModelUtils.getSerial());
        t.setData(data);
        t.setType(dataType);
        t.setCreated(TimeUtils.currentMillis());
        t.setUser(user);
        t.setAnalysis(analysis);
        t.setUrl("test");
        return t;
    }


    public TransientDataShare updateUrl(String url){
        this.url = url;
        return this;
    }
}
