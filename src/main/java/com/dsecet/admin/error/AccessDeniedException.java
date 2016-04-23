package com.dsecet.admin.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lxl
 */
@Getter
@Setter
public class AccessDeniedException implements AccessDeniedHandler{

    private String errorPage;

    public AccessDeniedException() {
    }

    public AccessDeniedException(String errorPage) {
        this.errorPage = errorPage;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException{
        response.sendRedirect(errorPage);
    }

}
