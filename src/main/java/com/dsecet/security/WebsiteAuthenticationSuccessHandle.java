package com.dsecet.security;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.service.ConsumerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lxl on 2014-09-01.
 */
public class WebsiteAuthenticationSuccessHandle extends SavedRequestAwareAuthenticationSuccessHandler {

    static final String LOGIN_SUCCESS_MARK = "ConsumerInfo";

    private String targetUrlParam;

    private ConsumerService consumerService;

    public WebsiteAuthenticationSuccessHandle(String targetUrl, boolean alwaysUse, String targetUrlParam ,ConsumerService consumerService) {
        super();
        this.targetUrlParam = targetUrlParam;
        this.consumerService = consumerService;
        setDefaultTargetUrl(targetUrl);
        setAlwaysUseDefaultTargetUrl(alwaysUse);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (StringUtils.isNotBlank(request.getParameter(targetUrlParam))) {
            this.setTargetUrlParameter(targetUrlParam);
        }
        HttpSession session = request.getSession(false);
        String cellNo = authentication.getName();
        if (session != null) {
            Consumer consumer = consumerService.identify(cellNo);
            session.setAttribute(LOGIN_SUCCESS_MARK, consumer);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
