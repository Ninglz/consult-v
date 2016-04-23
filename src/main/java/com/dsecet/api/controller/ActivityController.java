package com.dsecet.api.controller;

import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static com.dsecet.foundation.model.StatusResponse.success;

import javax.ws.rs.FormParam;
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

import com.dsecet.api.security.SessionToken;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.service.ActivityService;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.query.ActivityVo;
import com.dsecet.util.ModelUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import lombok.extern.slf4j.Slf4j;

@Api(value = "/activity", description = "Activity APIs", position = 8)
@Path("/activity")
@Slf4j
@RestController
@RequestMapping(value = "/activity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityController extends BaseController{
	

	
	@Autowired
	private ActivityService activityService;
	
	@javax.ws.rs.GET
    @Path("/list")
    @ApiOperation(value = "Activity list", response = StatusResponse.class, position = 0)
    @ApiResponses({@ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class),
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    StatusResponse list(
    		@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(required=true,value=TOKEN_HEADER_KEY)  String token,
			    		@ApiParam(name = "name",required = false,value="标题，用于搜索") @FormParam("name") String name,
                        @ApiParam(name = "page",required = true) @QueryParam("page") Integer page,
                        @ApiParam(name = "size",required = true) @QueryParam("size") Integer size
    ){
		validationToken(token);
        return success(
        		activityService.findCondition(name, new PageRequest(page, size))
        				);
    }

}
