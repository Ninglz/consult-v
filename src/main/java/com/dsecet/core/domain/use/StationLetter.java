package com.dsecet.core.domain.use;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.foundation.model.Domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created on 2015年10月15日
 * <p>Title:       礼猜_举报/p>
 * <p>Description: 举报实体内</p>
 * <p>Copyright:   Copyright (c) 2015</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@Entity
@Table(name = "station_letter")
@Data
@ToString(callSuper = true, exclude = {"consumer"})
@EqualsAndHashCode(callSuper = true, exclude = {"consumer"})
public class StationLetter extends Domain<Long>{

    @Column(length = 500)
    private String content;

    @OneToOne
    @JoinColumn(name = "owner_id")
    @NotNull
    private Consumer owner;
    
    @OneToOne
    @JoinColumn(name = "consumer_id")
    @NotNull
    private Consumer consumer;
    
    public String getShortContent(){
        if (this.content!=null){
            if (this.content.length()>20)
                return content.substring(0,20)+"...";
            return content;
        }
        return null;
    }
}
