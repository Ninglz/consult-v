package com.dsecet.api.controller;

import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static com.dsecet.foundation.model.StatusResponse.success;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.service.ConsultService;
import com.dsecet.foundation.model.StatusResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import lombok.extern.slf4j.Slf4j;

/**
 * 历史咨询记录接口
 * @author heyue
 * */
@Api(value = "/consult", description = "Consult APIs", position = 9)
@Path("/consult")
@Slf4j
@RestController
@RequestMapping(value = "/consult", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsultController extends BaseController{
	
	@Autowired
	private ConsultService consultService;
	
	@javax.ws.rs.GET
    @Path("/list")
    @ApiOperation(value = "Consult list", response = StatusResponse.class, position = 0)
    @ApiResponses({@ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class),
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    StatusResponse list(
    		@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(required=true,value=TOKEN_HEADER_KEY)  String token,
    		@ApiParam(name = "name",required = false) @QueryParam("name") String name,
            @ApiParam(name = "page",required = true) @QueryParam("page") Integer page,
            @ApiParam(name = "size",required = true) @QueryParam("size") Integer size
    ){
		Experts consumer = validationToken(token);
        return success(
        		consultService.findCondition(name, consumer.getId(),new PageRequest(page, size))
        		);
    }
}
