package com.dsecet.api.controller;

import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.CELL_NO_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.CITY_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.DESCRIPTION_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.DESCRIPTION_OR_CERTIFICATEIMG_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.EXPERTS_NOT_AUDIT;
import static com.dsecet.foundation.model.ErrorCode.EXPERTS_REGISTERED;
import static com.dsecet.foundation.model.ErrorCode.EXPERTS_UPDATED;
import static com.dsecet.foundation.model.ErrorCode.GOODAT_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.IDCARD_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.IMG_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.INDUSTRY_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.METHOD_ARGUMENT_NOT_VALID;
import static com.dsecet.foundation.model.ErrorCode.NAME_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.SENIORITY_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static com.dsecet.foundation.model.StatusResponse.error;
import static com.dsecet.foundation.model.StatusResponse.success;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.nlpcn.commons.lang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.api.controller.ConsumerController.UpdateCellRequest;
import com.dsecet.api.security.SessionToken;
import com.dsecet.api.vo.ExpertsShowVo;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.domain.user.ExpertsRecord;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.ExpertsService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.query.ExpertsDetailVo;
import com.dsecet.foundation.model.query.ExpertsListVo;
import com.dsecet.util.ModelUtils;
import com.dsecet.util.TimeUtils;
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
 * 专家接口
 * 
 * @author heyue
 */
@Api(value = "experts", description = "Experts APIs", position = 4)
@Path("/experts")
@Slf4j
@RestController
@RequestMapping(value = "experts", consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpertsController extends BaseController{

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private ExpertsService expertsService;
	
	/**
	 * 咨询师填写详细资料
	 */
	@POST
	@Path("/save")
	@ApiOperation(value = "Save Experts", response = Experts.class, responseContainer = "Array", position = 0)
	@ApiResponses({ @ApiResponse(code = IDCARD_EMPTY, message = "IdCard Empty", response = StatusResponse.class),
			@ApiResponse(code = CELL_NO_EMPTY, message = "CellNo empty", response = StatusResponse.class),
			@ApiResponse(code = SENIORITY_EMPTY, message = "Seniority Empty", response = StatusResponse.class),
			@ApiResponse(code = INDUSTRY_EMPTY, message = "Industry Empty", response = StatusResponse.class),
			@ApiResponse(code = GOODAT_EMPTY, message = "Goodat Empty", response = StatusResponse.class),
			@ApiResponse(code = DESCRIPTION_EMPTY, message = "Description Empty", response = StatusResponse.class) })
	@RequestMapping(value = "/save", method = POST)
	public @ResponseBody StatusResponse saveExpertsInformation(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(name = "name", required = true, value = "姓名") @FormParam("name") String name,
			@ApiParam(name = "sex", required = false, value = "性别") @FormParam("sex") Long sex,
			@ApiParam(name = "idCard", required = true, value = "身份证号码") @FormParam("idCard") String idCard,
			@ApiParam(name = "avatar", required = true, value = "头像")@RequestParam("avatar") @FormParam("avatar") MultipartFile avatar,
			@ApiParam(name = "seniorityStart", required = true, value = "资历时间起") @FormParam("seniorityStart") Date seniorityStart,
			@ApiParam(name = "seniorityEnd", required = false, value = "资历时间至") @FormParam("seniorityEnd") Date seniorityEnd,
			@ApiParam(name = "industryId", required = true, value = "行业ID") @FormParam("industryId") Long industryId,
			@ApiParam(name = "cityId", required = true, value = "城市ID") @FormParam("cityId") Long cityId,
			@ApiParam(name = "goodAt", required = true, value = "擅长") @FormParam("goodAt") String goodAt,
			@ApiParam(name = "description", required = false, value = "说明") @FormParam("description") String description,
			@ApiParam(name = "alipayUserName", required = false, value = "支付宝账号") @FormParam("alipayUserName") String alipayUserName,
			@ApiParam(name = "certificateImg", required = false, value = "资历证书")@RequestParam("certificateImg") @FormParam("certificateImg") MultipartFile[] certificateImg
			) {

		Experts experts = validationToken(token);

		if (seniorityStart == null) 
			return error(SENIORITY_EMPTY);
		if (industryId == null) 
			return error(INDUSTRY_EMPTY);
		if (cityId == null) 
			return error(CITY_EMPTY);
		if (StringUtils.isBlank(goodAt)) 
			return error(GOODAT_EMPTY);
		if (avatar == null) 
			return error(IMG_EMPTY);
		if (StringUtil.isBlank(name)) 
			return error(NAME_EMPTY);
		if (StringUtil.isBlank(idCard)) 
			return error(IDCARD_EMPTY);
		if (StringUtils.isBlank(description) || certificateImg == null) 
			return error(DESCRIPTION_OR_CERTIFICATEIMG_EMPTY);
		

		Experts exprts = expertsService.saveInformation(name, sex, idCard, avatar, seniorityStart,seniorityEnd, industryId, cityId, goodAt,
				description, alipayUserName, certificateImg,experts.getId());
		return success();
	}

	/**
	 * 专家列表
	 */
	@javax.ws.rs.GET
	@Path("/list")
	@ApiOperation(value = "Experts list", response = StatusResponse.class, position = 1)
	@ApiResponses({ @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class), })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody StatusResponse list(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(name = "name", required = false, value = "专家姓名，用于搜索") @FormParam("name") String name,
			@ApiParam(name = "industryId", required = false, value = "行业id") @FormParam("industryId") String industryId,
			@ApiParam(name = "evaluation : asc || desc", required = false, value = "评分排序") @FormParam("evaluation") String evaluation,
			@ApiParam(name = "price: asc || desc ", required = false, value = "资费排序") @FormParam("price") String price,
			@ApiParam(name = "page", required = true) @QueryParam("page") Integer page,
			@ApiParam(name = "size", required = true) @QueryParam("size") Integer size) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		PageableResponse<ExpertsListVo> expertsPage = expertsService.findByConditions(name, industryId, evaluation, price,sessionToken.getKey(),
					new PageRequest(page+1, size));
		return success(PageableResponse.of(expertsPage.getCollection(), expertsPage.getQueryRecordCount(),
				expertsPage.getTotalRecordCount()));
	}

	/**
	 * 专家推荐
	 */
	@javax.ws.rs.GET
	@Path("/recommend")
	@ApiOperation(value = "Experts recommend", response = StatusResponse.class, position = 2)
	@ApiResponses({ @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class), })
	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public @ResponseBody StatusResponse list(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(name = "size", required = true) @QueryParam("size") Integer size) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		PageableResponse<ExpertsListVo> expertsPage = expertsService.findRecommendExperts(sessionToken.getKey(),null, new PageRequest(1, size));
		return success(PageableResponse.of(expertsPage.getCollection(), expertsPage.getQueryRecordCount(),
				expertsPage.getTotalRecordCount()));
	}

	@javax.ws.rs.GET
	@Path("/judge")
	@ApiOperation(value = "Judge Experts Sign In", response = SessionToken.class, position = 3)
	@ApiResponses({
			@ApiResponse(code = METHOD_ARGUMENT_NOT_VALID, message = "Request Entity Blank", response = StatusResponse.class) })
	@RequestMapping(value = "/judge", method = GET)
	public @ResponseBody StatusResponse judgeSingin(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		// 咨询师登录判断 判断登陆者是否是咨询师 若是 直接登录 若不是 填写资料
		Experts experts = expertsService.findOne(sessionToken.getKey());
		List<ExpertsRecord> expertsRecord = experts.getExpertsRecord();
		if (expertsRecord != null&& (experts.getHandle() == 3L || experts.getHandle() == 2L   //3L：审核通过、2L：变更审核通过、-2L变更审核失败
				|| experts.getHandle() == -2L )) {
			return success();
		} else {
			return success(ExpertsDetailVo.of(experts));
		}
	}

	/**
	 * 专家个人中心
	 * 
	 * @author heyue
	 */
	@GET
	@Path("/detail")
	@ApiOperation(value = "Experts Detail", response = Consumer.class, responseContainer = "Array", position = 4)
	@ApiResponses({ @ApiResponse(code = TOKEN_INVALID, message = "token invalid", response = StatusResponse.class) })
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public @ResponseBody StatusResponse getExperts(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @ApiParam(required = true) @RequestHeader(TOKEN_HEADER_KEY) String token) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		Experts experts = expertsService.fingByAuditState(sessionToken.getKey());
		ModelUtils.checkLock(experts);
		return success(ExpertsDetailVo.of(experts));
	}
	
	/**
	 * 咨询师修改资料
	 */
	@POST
	@Path("/update")
	@ApiOperation(value = "Update Experts", response = Experts.class, responseContainer = "Array", position = 5)
	@ApiResponses({ @ApiResponse(code = IDCARD_EMPTY, message = "IdCard Empty", response = StatusResponse.class),
			@ApiResponse(code = CELL_NO_EMPTY, message = "CellNo empty", response = StatusResponse.class),
			@ApiResponse(code = SENIORITY_EMPTY, message = "Seniority Empty", response = StatusResponse.class),
			@ApiResponse(code = INDUSTRY_EMPTY, message = "Industry Empty", response = StatusResponse.class),
			@ApiResponse(code = GOODAT_EMPTY, message = "Goodat Empty", response = StatusResponse.class),
			@ApiResponse(code = DESCRIPTION_EMPTY, message = "Description Empty", response = StatusResponse.class) })
	@RequestMapping(value = "/update", method = POST)
	public @ResponseBody StatusResponse updateExpertsInformation(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(name = "name", required = false, value = "姓名") @FormParam("name") String name,
			@ApiParam(name = "sex", required = false, value = "性别") @FormParam("sex") Long sex,
			@ApiParam(name = "idCard", required = false, value = "身份证号码") @FormParam("idCard") String idCard,
			@ApiParam(name = "avatar", required = false, value = "头像") @FormParam("avatar") MultipartFile avatar,
			@ApiParam(name = "seniorityStart", required = false, value = "资历时间起") @FormParam("seniorityStart") Date seniorityStart,
			@ApiParam(name = "seniorityEnd", required = false, value = "资历时间止") @FormParam("seniorityEnd") Date seniorityEnd,
			@ApiParam(name = "industryId", required = false, value = "行业ID") @FormParam("industryId") Long industryId,
			@ApiParam(name = "cityId", required = false, value = "城市ID") @FormParam("cityId") Long cityId,
			@ApiParam(name = "goodAt", required = false, value = "擅长") @FormParam("goodAt") String goodAt,
			@ApiParam(name = "description", required = false, value = "说明") @FormParam("description") String description,
			@ApiParam(name = "alipayUserName", required = false, value = "支付宝账号") @FormParam("alipayUserName") String alipayUserName,
			@ApiParam(name = "certificateImg", required = false, value = "资历证书")@RequestParam("certificateImg") @FormParam("certificateImg") MultipartFile[] certificateImg
			) {

		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.get(sessionToken.getKey());
		ModelUtils.checkLock(consumer);

		Experts exprts = expertsService.updateInformation(name, sex, idCard, avatar, seniorityStart, seniorityEnd,industryId, cityId, goodAt,
				description, alipayUserName, certificateImg, sessionToken.getKey());
		return success();
	}
	
	/**
	 * 专家更新上下线
	 * @author heyue
	 * */
	@POST
	@Path("/update/state")
	@ApiOperation(value = "Update Experts State", response = Experts.class, responseContainer = "Array", position = 6)
	@ApiResponses({})
	@RequestMapping(value = "/update/state", method = POST)
	public @ResponseBody StatusResponse updateExpertsState(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token) {

		SessionToken sessionToken = SessionToken.valueOf(token);
		Experts experts = expertsService.findOne(sessionToken.getKey());
		ModelUtils.checkLock(experts);
		
		if(experts.getState() == 0L){
			experts.setState(1L);    //设置上线状态
		}else if(experts.getState() == 1L){
			experts.setState(0L);    //设置下线状态
		}
		expertsService.save(experts);
		return success();
	}
	
	/**
	 * 专家更新最后活跃时间
	 * @author heyue
	 * */
	@POST
	@Path("/update/active")
	@ApiOperation(value = "Update Experts Active", response = Experts.class, responseContainer = "Array", position = 7)
	@ApiResponses({})
	@RequestMapping(value = "/update/active", method = POST)
	public @ResponseBody StatusResponse updateExpertsLastActive(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token) {
		
		SessionToken sessionToken = SessionToken.valueOf(token);
		Experts experts = expertsService.findOne(sessionToken.getKey());
		ModelUtils.checkLock(experts);
		
		experts.setLastActive(TimeUtils.currentMillis());
		expertsService.save(experts);
		return success();
	}
	
	/**
	 * 判断专家修改资料
	 * */
	@javax.ws.rs.GET
	@Path("/judge/update")
	@ApiOperation(value = "Judge Experts Update Information", response = SessionToken.class, position = 8)
	@ApiResponses({
			@ApiResponse(code = METHOD_ARGUMENT_NOT_VALID, message = "Request Entity Blank", response = StatusResponse.class) })
	@RequestMapping(value = "/judge/update", method = GET)
	public @ResponseBody StatusResponse judgeUpdate(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		// 判断专家是否修改过资料   若未修改   通过    
		Experts experts = expertsService.findOne(sessionToken.getKey());
		ModelUtils.checkLock(experts);
		if(experts.getHandle() == 3L || experts.getHandle() == -2L){
			return success(ExpertsDetailVo.of(experts));
		}else if(experts.getHandle() == 1L){
			return error(EXPERTS_REGISTERED);
		}else if(experts.getHandle() == -1L){
			return error(EXPERTS_NOT_AUDIT);
		}else{
			return error(EXPERTS_UPDATED);
		}
	}
	/**
	 * 专家绑定支付宝
	 * */
	@javax.ws.rs.POST
	@Path("/alipay")
	@ApiOperation(value = "Experts Update Alipay", response = SessionToken.class, position = 9)
	@ApiResponses({
		@ApiResponse(code = METHOD_ARGUMENT_NOT_VALID, message = "Request Entity Blank", response = StatusResponse.class) })
	@RequestMapping(value = "/alipay", method = POST)
	public @ResponseBody StatusResponse alipay(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(required = true) @Valid @NotNull @RequestBody AlipayRequest alipayRequest) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		Experts experts = expertsService.findOne(sessionToken.getKey());
		ModelUtils.checkLock(experts);
		
		String alipayUserName = alipayRequest.getAlipayUserName();
		expertsService.saveAlipay(sessionToken.getKey(),alipayUserName);
		return success();
	}
	
	/**
	 * 专家详情
	 * @author heyue
	 */
	@GET
	@Path("/show")
	@ApiOperation(value = "Experts Show", response = Consumer.class, responseContainer = "Array", position = 10)
	@ApiResponses({ @ApiResponse(code = TOKEN_INVALID, message = "token invalid", response = StatusResponse.class) })
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public @ResponseBody StatusResponse expertsShow(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @ApiParam(required = true) @RequestHeader(TOKEN_HEADER_KEY) String token,
			@ApiParam(name = "expertsId", required = true, value = "专家ID") @FormParam("expertsId") Long expertsId) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.findOne(sessionToken.getKey());
		ModelUtils.checkLock(consumer);
		
		ExpertsShowVo expertsShowVo = expertsService.findShow(sessionToken.getKey(),expertsId);
		return success(expertsShowVo);
	}
	
	 @ApiModel
	    @Getter
	    @NoArgsConstructor
	    @EqualsAndHashCode
	    public static final class AlipayRequest{
	    	
	        @ApiModelProperty(value = "alipayUserName", required = true, position = 0)
	        @JsonProperty("alipayUserName")
	        @NotBlank
	        private String alipayUserName;

	        @JsonCreator
	        public AlipayRequest(@JsonProperty("alipayUserName") @NotBlank String alipayUserName){
	            this.alipayUserName = alipayUserName;
	        }
	    }
}
