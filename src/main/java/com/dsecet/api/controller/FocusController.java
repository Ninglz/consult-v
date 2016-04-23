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
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.FocusService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.query.ExpertsListVo;
import com.dsecet.util.ModelUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "focus", description = "Focus APIs", position = 6)
@Path("/focus")
//@Slf4j
@RestController
@RequestMapping(value = "focus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class FocusController {

	@Autowired
	private FocusService focusService;
	
	@Autowired
	private ConsumerService consumerService;
	
	/**
	 * 咨询者 关注/取消关注 咨询师
	 * */
	@javax.ws.rs.GET
    @Path("/update")
    @ApiOperation(value = "Focus", response = StatusResponse.class, position = 1)
    @ApiResponses({@ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class),
    })
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public @ResponseBody
    StatusResponse focus(@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
    					 @ApiParam(name = "expertsId",required = true,value="Experts ID")@QueryParam("expertsId") @FormParam("expertsId") Long expertsId,
    					 @ApiParam(name = "active",required = true,value="true:关注 || false:取消关注")@QueryParam("active") @FormParam("active") Boolean active){
		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.get(sessionToken.getKey());
        ModelUtils.checkLock(consumer);
        
        focusService.save(sessionToken.getKey(),expertsId,active);
        return success();
    }
	/**
	 * 关注列表
	 * @author heyue
	 * */
	@javax.ws.rs.GET
    @Path("/list")
    @ApiOperation(value = "Focus list", response = StatusResponse.class, position = 2)
    @ApiResponses({@ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class),
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    StatusResponse list(@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(name = "name", required = false, value = "专家姓名，用于搜索") @FormParam("name") String name,
			@ApiParam(name = "industryId", required = false, value = "行业id") @FormParam("industryId") String industryId,
			@ApiParam(name = "evaluation : asc || desc", required = false, value = "评分排序") @FormParam("evaluation") String evaluation,
			@ApiParam(name = "price: asc || desc ", required = false, value = "资费排序") @FormParam("price") String price,
			@ApiParam(name = "page", required = true) @QueryParam("page") Integer page,
			@ApiParam(name = "size", required = true) @QueryParam("size") Integer size) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.get(sessionToken.getKey());
        ModelUtils.checkLock(consumer);
        
		PageableResponse<ExpertsListVo> expertsPage = focusService.findByConditions(name, industryId, evaluation, price,sessionToken.getKey(),
					new PageRequest(page+1, size));
		return success(PageableResponse.of(expertsPage.getCollection(), expertsPage.getQueryRecordCount(),
				expertsPage.getTotalRecordCount()));
	}
}
