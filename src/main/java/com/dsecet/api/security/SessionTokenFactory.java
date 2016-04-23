package com.dsecet.api.security;

import com.dsecet.core.service.RedisService;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.ErrorCode;
import com.dsecet.foundation.model.Token;
import com.dsecet.foundation.model.TokenCache;
import com.dsecet.foundation.model.TokenFactory;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author Fablenas
 * 
 */
public class SessionTokenFactory implements TokenFactory<Long>{

    private RedisService redisService;

    @Autowired
    public SessionTokenFactory(@NotNull RedisService redisService){
        this.redisService = redisService;
    }

	@Override
	public Token<Long> createToken(@NotNull Long key) {
        SessionToken token = new SessionToken(key);
        redisService.put(String.valueOf(key), token.toString());
        return token;
	}

	@Override
	public Token<Long> get(@NotNull Long key) {
        String serializedToken = redisService.get(String.valueOf(key));
        if(StringUtils.isBlank(serializedToken))
            throw new RespondableException(ErrorCode.TOKEN_EXPIRED);
        return SessionToken.valueOf(serializedToken);
	}

	@Override
	public void remove(@NotNull Long key) {
        redisService.remove(String.valueOf(key));
	}

}
