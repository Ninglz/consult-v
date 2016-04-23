package com.dsecet.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: lxl
 */
@Controller
public class AdminExceptionController{

    @RequestMapping("/error/403")
    public String generalError(HttpServletRequest request, HttpServletResponse response, Model model){
        // retrieve some useful information from the request

        return "prompt/403";
    }
}
