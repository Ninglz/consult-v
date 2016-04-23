package com.dsecet.api.controller;

import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.REGISTER_CELLNO_DUPLICATE;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static com.dsecet.foundation.model.StatusResponse.error;
import static com.dsecet.foundation.model.StatusResponse.success;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsecet.api.controller.ConsumerController.UpdateCellRequest;
import com.dsecet.api.security.SessionToken;
import com.dsecet.api.vo.WithdrawVo;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.ExpertsService;
import com.dsecet.core.service.TradingService;
import com.dsecet.foundation.model.ErrorCode;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.query.RechargeVo;
import com.dsecet.util.ModelUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 交易记录
 * @author heyue
 * */

@Api(value = "/trading", description = "Trading APIs", position = 10)
@Path("/trading")
@Slf4j
@RestController
@RequestMapping(value = "/trading", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TradingController {
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private TradingService tradingService;
	
	@Autowired
	private ExpertsService expertsService;
	
	/**
	 * 用户充值
	 * @author heyue
	 * */
	@POST
	@Path("/recharge")
	@ApiOperation(value = "Consumer Recharge", response = Experts.class, responseContainer = "Array", position = 0)
	@ApiResponses({  @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class)})
	@RequestMapping(value = "/recharge", method = POST)
	public @ResponseBody StatusResponse recharge(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(required = true) @Valid @NotNull @RequestBody RechargeRequest rechargeRequest) {

		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.get(sessionToken.getKey());
		ModelUtils.checkLock(consumer);
		Double money = rechargeRequest.getMoney();
		tradingService.recharge(sessionToken.getKey(),money);
		return success();
	}
	
	/**
	 * 充值明细
	 * @author heyue
	 * */
	@javax.ws.rs.GET
	@Path("/recharge/detail")
	@ApiOperation(value = "Trading Racharge Detail", response = StatusResponse.class, position = 1)
	@ApiResponses({ @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class), })
	@RequestMapping(value = "/recharge/detail", method = RequestMethod.GET)
	public @ResponseBody StatusResponse rechargeDetail(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(name = "page", required = true) @QueryParam("page") Integer page,
			@ApiParam(name = "size", required = true) @QueryParam("size") Integer size) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.get(sessionToken.getKey());
		ModelUtils.checkLock(consumer);
		PageableResponse<RechargeVo> rechargePage = tradingService.findPage(sessionToken.getKey(), new PageRequest(page, size));
		return success(PageableResponse.of(rechargePage.getCollection(), rechargePage.getQueryRecordCount(),rechargePage.getTotalRecordCount()));
	}

	
	/**
	 * 专家提现判断支付宝
	 * @author heyue
	 * */
	@javax.ws.rs.GET
	@Path("/judge/aliapy")
	@ApiOperation(value = "Experts Judge AliapyUserName", response = Experts.class, responseContainer = "Array", position = 2)
	@ApiResponses({  @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class)})
	@RequestMapping(value = "/judge/aliapy", method = RequestMethod.GET)
	public @ResponseBody StatusResponse judgeAliapy(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.get(sessionToken.getKey());
		ModelUtils.checkLock(consumer);
		
		Experts experts = expertsService.findOne(sessionToken.getKey());
		String alipayUserName = experts.getAlipayUserName();
		if(StringUtils.isBlank(alipayUserName)){
			return error(ErrorCode.ALIAPYUSERNAME_EMPTY);
		}
		return success();
	}
	
	/**
	 * 专家提现
	 * @author heyue
	 * */
	@POST
	@Path("/withdraw")
	@ApiOperation(value = "Experts Withdraw", response = Experts.class, responseContainer = "Array", position = 3)
	@ApiResponses({  @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class)})
	@RequestMapping(value = "/withdraw", method = POST)
	public @ResponseBody StatusResponse withdraw(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(required = true) @Valid @NotNull @RequestBody WithdrawRequest withdrawRequest) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.get(sessionToken.getKey());
		ModelUtils.checkLock(consumer);
		
		Double sink = withdrawRequest.getSink();
		tradingService.withdraw(sessionToken.getKey(),sink);
		return success();
	}
	

	/**
	 * 提现明细
	 * @author heyue
	 * */
	@javax.ws.rs.GET
	@Path("/withdraw/detail")
	@ApiOperation(value = "Trading withdraw Detail", response = StatusResponse.class, position = 4)
	@ApiResponses({ @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class), })
	@RequestMapping(value = "/withdraw/detail", method = RequestMethod.GET)
	public @ResponseBody StatusResponse withdrawDetail(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(name = "page", required = true) @QueryParam("page") Integer page,
			@ApiParam(name = "size", required = true) @QueryParam("size") Integer size) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.get(sessionToken.getKey());
		ModelUtils.checkLock(consumer);
		
		PageableResponse<WithdrawVo> withdrawPage = tradingService.findWithdrawPage(sessionToken.getKey(), new PageRequest(page, size));
		return success(PageableResponse.of(withdrawPage.getCollection(), withdrawPage.getQueryRecordCount(),withdrawPage.getTotalRecordCount()));
	}
	
	 	@ApiModel
	    @Getter
	    @NoArgsConstructor
	    @EqualsAndHashCode
	    public static final class RechargeRequest{
	    	
	        @ApiModelProperty(value = "money", required = true, position = 0)
	        @JsonProperty("money")
	        @NotNull
	        private Double money;

	        @JsonCreator
	        public RechargeRequest(@JsonProperty("money") @NotNull Double money){
	            this.money = money;
	        }
	    }
	 	@ApiModel
	 	@Getter
	 	@NoArgsConstructor
	 	@EqualsAndHashCode
	 	public static final class WithdrawRequest{
	 		
	 		@ApiModelProperty(value = "sink", required = true, position = 0)
	 		@JsonProperty("sink")
	 		@NotNull
	 		private Double sink;
	 		
	 		@JsonCreator
	 		public WithdrawRequest(@JsonProperty("sink") @NotNull Double sink){
	 			this.sink = sink;
	 		}
	 	}
}
