package com.dsecet.api.controller;

import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.CELL_NO_INVALID;
import static com.dsecet.foundation.model.ErrorCode.CITY_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.CODE_REQUIRED;
import static com.dsecet.foundation.model.ErrorCode.IDCARD_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.IMG_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.NAME_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.REGISTER_CELLNO_DUPLICATE;
import static com.dsecet.foundation.model.ErrorCode.RESET_CELLNO_NOTEXIST;
import static com.dsecet.foundation.model.ErrorCode.SEX_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static com.dsecet.foundation.model.StatusResponse.error;
import static com.dsecet.foundation.model.StatusResponse.success;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.api.security.SessionToken;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.VerificationCache;
import com.dsecet.util.ModelUtils;
import com.dsecet.util.Validations;
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

@Api(value = "consumer", description = "Consumer APIs", position = 3)
@Path("/consumer")
@Slf4j
@RestController
@RequestMapping(value = "consumer", consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsumerController extends BaseController{

	@Autowired
	private ConsumerService consumerService;
	
	 @Autowired
	 private VerificationCache<String, String, String> verifyCache;
	/**
	 * 咨询者完善资料/修改
	 * @author heyue
	 * */
    @javax.ws.rs.POST
    @Path("/edit")
    @ApiOperation(value = "Edit Consumer Detail Information", response = StatusResponse.class, position = 0)
    @ApiResponses({
    	@ApiResponse(code = NAME_EMPTY, message = "Name Required", response = StatusResponse.class),
    	@ApiResponse(code = IDCARD_EMPTY, message = "IdCard Required", response = StatusResponse.class),
    	@ApiResponse(code = SEX_EMPTY, message = "Sex Required", response = StatusResponse.class),
    	@ApiResponse(code = CITY_EMPTY, message = "City Required", response = StatusResponse.class),
    	@ApiResponse(code = IMG_EMPTY, message = "Img Required", response = StatusResponse.class)
    })
    @RequestMapping(value = "/edit", method = POST)
    public
    @ResponseBody
    StatusResponse edit(
    		@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
    		@ApiParam(name = "nickName",required = false,value="昵称") @FormParam("nickName") String nickName,
			@ApiParam(name = "sex",required = false,value="性别") @FormParam("sex") Long sex,
			@ApiParam(name = "avatar",required = false,value="头像") @FormParam("avatar") @RequestParam(value="avatar",required = false) MultipartFile avatar
			){
    	Experts consumer = validationToken(token);
        log.info("join consumer save information method : name[{}],sex[{}],cell[{}],img[{}]");
		if(nickName != null)
			consumer.setNickName(nickName);
		if(sex != null)
			consumer.setSex(sex);
		else
			consumer.setSex(2L);
        consumerService.saveInformation(avatar,consumer);
        return success();
    }
    
    /**
     * 用户个人中心/个人资料
     * */
    @GET
    @Path("/detail")
    @ApiOperation(value = "Consumer Detail", response = Consumer.class, responseContainer = "Array", position = 1)
    @ApiResponses({
    	@ApiResponse(code = TOKEN_INVALID, message = "token invalid", response = StatusResponse.class)
    })
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public 
	@ResponseBody StatusResponse getConsumer(@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @ApiParam(required = true) @RequestHeader(TOKEN_HEADER_KEY)String token){
    	Experts consumer = validationToken(token);
        ConsumerResponse consumerResponse = ConsumerResponse.of(consumer);
        return success(consumerResponse);
	}
    /**
     * 用户修改联系电话
     * */
    @POST
    @Path("/update/cell")
    @ApiOperation(value = "Update Consumer CellNo", response = Consumer.class, responseContainer = "Array", position = 2)
    @ApiResponses({
    	@ApiResponse(code = TOKEN_INVALID, message = "token invalid", response = StatusResponse.class),
    	@ApiResponse(code = CODE_REQUIRED, message = "code required", response = StatusResponse.class)
    })
    @RequestMapping(value = "/update/cell", method = RequestMethod.POST)
    public 
    @ResponseBody StatusResponse updateCell(@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @ApiParam(required = true) @RequestHeader(TOKEN_HEADER_KEY)String token,
    		@ApiParam(required = true) @Valid @NotNull @RequestBody UpdateCellRequest updateCellRequest){
    	Experts consumer = validationToken(token);
    	
    	String cell = updateCellRequest.getCell();
    	String code = updateCellRequest.getCode();
//    	if(StringUtils.isBlank(code)){
//          return error(CODE_REQUIRED);
//    	}
    	
    	if(!Validations.validateCellNo(cell)){
            return error(CELL_NO_INVALID);
        }

        if(consumerService.userNameExists(cell)){
            return error(RESET_CELLNO_NOTEXIST);
        }
        
//        if(StringUtils.isBlank(code) || !verifyCache.consumeCode(cell, code)){
//          return error(CODE_INVALID);
//        }

        if(!StringUtils.isBlank(cell)){
			consumer.setUsername(cell);
			consumer.getAuthentication().getCellNo().setData(cell);
			consumer.getAuthentication().getCellNo().setAuthenticated(true);
		}
        consumerService.save(consumer);

    	return success();
    }
    
    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class ConsumerResponse{
    	
    	@ApiModelProperty(value = "Consumer name", position = 1)
    	@JsonProperty("name")
    	private String name;
        
    	@ApiModelProperty(value = "Consumer avatar", position = 2)
    	@JsonProperty("avatar")
    	private String avatar;
    	
    	@ApiModelProperty(value = "Consumer sex", position = 3)
    	@JsonProperty("sex")
    	private Long sex;
    	
    	
    	@ApiModelProperty(value = "Consumer email", position = 4)
    	@JsonProperty("email")
    	private String email;
    	
    	@ApiModelProperty(value = "Consumer consumerLevels", position = 5)
    	@JsonProperty("consumerLevels")
    	private Integer consumerLevels;
    	

    	@ApiModelProperty(value = "sink", position = 6)
    	@JsonProperty("sink")
    	private Double sink;
    	
    	@ApiModelProperty(value = "integral", position = 7)
    	@JsonProperty("integral")
    	private Double integral;
    	
    	@ApiModelProperty(value = "Consumer cell", position = 8)
    	@JsonProperty("cell")
    	private String cell;
    	
    	@ApiModelProperty(value = "serviceCell", position = 9)
    	@JsonProperty("serviceCell")
    	private String serviceCell;
    	
		public ConsumerResponse(@JsonProperty("name") String name,
				   				 @JsonProperty("cell") String cell,
				   				 @JsonProperty("avatar") String avatar,
				   				 @JsonProperty("sex") Long sex,
				   				 @JsonProperty("email") String email,
				   				 @JsonProperty("consuemrLevels") Integer consumerLevels,
				   				 @JsonProperty("sink") Double sink,
				   				 @JsonProperty("integral") Double integral,
				   				 @JsonProperty("serviceCell") String serviceCell
				   				 ) {
			this.name = name;
			this.cell = cell;
			this.avatar = avatar;
			this.sex = sex;
			this.email = email;
			this.consumerLevels = consumerLevels;
			this.sink = sink;
			this.integral = integral;
			this.serviceCell = serviceCell;
		}
		
		public static ConsumerResponse of(Consumer consumer){
			if(consumer==null){
				return null;
			}
			return new ConsumerResponse(consumer.getNickName(),consumer.getUsername(),
					consumer.getAvatar()==null?null:SystemProperty.webUrl + consumer.getAvatar(),
					consumer.getSex(),consumer.getEmail(),
					consumer.getConsumerLevel(),
					consumer.getSink(),
					consumer.getIntegral(),SystemProperty.tel);
		}
    }
    
    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class UpdateCellRequest{
    	
        @ApiModelProperty(value = "Cell no", required = true, position = 0)
        @JsonProperty("cell")
        @NotBlank
        private String cell;

        @ApiModelProperty(value = "code", required = true, position = 1)
        @JsonProperty("code")
        @NotBlank
        private String code;

        @JsonCreator
        public UpdateCellRequest(@JsonProperty("code") @NotBlank String code,
                             @JsonProperty("cell") @NotBlank String cell){
            this.cell = cell;
            this.code = code;
        }
    }
}
