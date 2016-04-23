package com.dsecet.security;

import com.dsecet.core.domain.admin.Staff;
import com.dsecet.core.service.admin.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lxl on 14-8-26.
 */
public class AdminAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private StaffService staffService;

    public AdminAuthenticationSuccessHandler(StaffService staffService, String targetUrl, boolean alwaysUse) {
        super();
        this.staffService = staffService;
        setDefaultTargetUrl(targetUrl);
        setAlwaysUseDefaultTargetUrl(alwaysUse);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Staff staff = staffService.getByUserName(authentication.getName());
            session.setAttribute("staff", staff);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
