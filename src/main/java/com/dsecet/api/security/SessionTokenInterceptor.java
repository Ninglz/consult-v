package com.dsecet.api.security;

import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.ErrorCode;
import com.dsecet.foundation.model.Token;
import com.dsecet.foundation.model.TokenFactory;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_REQUIRED;

/**
 * @author: Fablenas
 */
@Slf4j
public class SessionTokenInterceptor extends HandlerInterceptorAdapter{

    public static final String TOKEN_HEADER_KEY = "Session-Token";

    @Setter private SessionTokenValidator sessionTokenValidator;

    @Setter private TokenFactory<Long> tokenFactory;

    @Setter private Long expiredTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
//        String serializedToken = request.getHeader(TOKEN_HEADER_KEY);
//
//        log.info(String.format("validate token[%s]", serializedToken));
//
//        if (StringUtils.isBlank(serializedToken))
//            throw new RespondableException(TOKEN_REQUIRED);
//
//        Token<Long> token = SessionToken.valueOf(serializedToken);
//        if (null == token)
//            throw new RespondableException(TOKEN_INVALID);
//
//        Token<Long> cachedToken = tokenFactory.get(token.getKey());
//        if (null == cachedToken)
//            throw new RespondableException(ErrorCode.TOKEN_EXPIRED);
//
//        if (cachedToken.isExpired(expiredTime)){
//            tokenFactory.remove(cachedToken.getKey());
//            throw new RespondableException(ErrorCode.TOKEN_EXPIRED);
//        }
//
//        if (!sessionTokenValidator.validate(token, cachedToken))
//            throw new RespondableException(TOKEN_INVALID);

        return true;
    }

}
