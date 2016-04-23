package com.dsecet.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * User: liuchang
 * Date: 14/10/28
 */
@Slf4j
public class WebsiteLoginUrlAuthEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public WebsiteLoginUrlAuthEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String loginUrl = getLoginFormUrl();
        String callBackUrl = request.getParameter("callBackUrl");
        if (StringUtils.isNotBlank(callBackUrl)) {
            try {
                loginUrl += "?callBackUrl=" + URLEncoder.encode(callBackUrl, "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error("Can not encode callback url.", e);
            }
        }
        return loginUrl;
    }
}
