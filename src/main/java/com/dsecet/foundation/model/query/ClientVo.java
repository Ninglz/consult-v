package com.dsecet.foundation.model.query;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.foundation.model.Token;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created on 2016年4月1日
 * <p>Title:       Client</p>
 * <p>Description: </p>
 * <p>Copyright:   Copyright (c) 2016</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
@Data
public class ClientVo{
	
	 @JsonProperty("t")
	  private String token;

    @JsonProperty("clientNum")
    private String clientNum;
    
    @JsonProperty("clientPwd")
    private String clientPwd;
    
    @JsonProperty("appToken")
    private String appToken;
    
    

    public static ClientVo of(Consumer s,String token){
        ClientVo d = new ClientVo();
        d.setClientNum(s.getClientNum());
        d.setClientPwd(s.getClientPwd());
        d.setToken(token);
        d.setAppToken(s.getAppToken());
        return d;
    }
}
