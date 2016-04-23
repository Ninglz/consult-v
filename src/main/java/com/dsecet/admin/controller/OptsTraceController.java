package com.dsecet.admin.controller;

import com.dsecet.core.domain.admin.OptsTraceLog;
import com.dsecet.core.service.admin.OptsTraceLogService;
import com.dsecet.foundation.model.OptTraceLogVo;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.util.ModelUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: lxl
 */
@Controller
@RequestMapping(value = "/optslog")
public class OptsTraceController{

    @Autowired
    private OptsTraceLogService optsTraceLogService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "admin_operation_list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    PageableResponse getOptLog(@RequestParam(required = false) String username,
                                  @RequestParam(required = false) String optLogDate,
                                  @RequestParam(required = false) String operation,
                                  Pageable pageable) throws Exception{
        Long beg = null;
        Long end = null;
        if (!StringUtils.isBlank(optLogDate)) {
            String[] dates = optLogDate.split("/");
            beg = ModelUtils.parseToMillis(dates[0] + " 00:00:00:000");
            end = ModelUtils.parseToMillis(dates[1] + " 23:59:59:999");
        }
        Page<OptsTraceLog> logsPage = optsTraceLogService.findOptsTraceLogByConditions(username,beg,end,operation,pageable);
        return PageableResponse.of(OptTraceLogVo.of(logsPage), logsPage.getContent().size(), (int) logsPage.getTotalElements());
    }
}
