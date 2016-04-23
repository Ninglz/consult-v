package com.dsecet.api.controller;


import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.util.TimeUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import static com.dsecet.foundation.model.StatusResponse.success;

/**
 * @author: lxl
 */
@Api(value = "system", description = "System Cfg APIs", position = 1)
//@Profile("prod")
@Path("/system")
@Slf4j
@RestController
@RequestMapping(value = "system", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemController{

    @GET
    @Path("/current/millis")
    @ApiOperation(value = "Get currentMillis", response = Long.class, position = 1)
    @RequestMapping(value = "/current/millis", method = RequestMethod.GET)
    public
    @ResponseBody
    StatusResponse getCurrentMillis(){
        return success(TimeUtils.currentMillis());
    }


}
