package com.dsecet.api.controller;


import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.BAD_CREDENTIAL;
import static com.dsecet.foundation.model.ErrorCode.CELL_NO_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.CELL_NO_INVALID;
import static com.dsecet.foundation.model.ErrorCode.CODE_EXPIRE;
import static com.dsecet.foundation.model.ErrorCode.CODE_INVALID;
import static com.dsecet.foundation.model.ErrorCode.CODE_REQUIRED;
import static com.dsecet.foundation.model.ErrorCode.ILLEGAl_PARAMETER;
import static com.dsecet.foundation.model.ErrorCode.METHOD_ARGUMENT_NOT_VALID;
import static com.dsecet.foundation.model.ErrorCode.MODIFY_CELLNO_NOTEXIST;
import static com.dsecet.foundation.model.ErrorCode.PASSWORD_DIFFERENCE;
import static com.dsecet.foundation.model.ErrorCode.PASSWORD_INVALID;
import static com.dsecet.foundation.model.ErrorCode.REGISTER_CELLNO_DUPLICATE;
import static com.dsecet.foundation.model.ErrorCode.RESET_CELLNO_NOTEXIST;
import static com.dsecet.foundation.model.ErrorCode.SMS_SEND_FAILED;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_REQUIRED;
import static com.dsecet.foundation.model.ErrorCode.UNDEFINDED;
import static com.dsecet.foundation.model.ErrorCode.USER_LOCK;
import static com.dsecet.foundation.model.StatusResponse.error;
import static com.dsecet.foundation.model.StatusResponse.success;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsecet.api.security.SessionToken;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Consumer.AppType;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import com.dsecet.ext.sms.SmsSender;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.TokenFactory;
import com.dsecet.foundation.model.VerificationCache;
import com.dsecet.foundation.model.query.ClientVo;
import com.dsecet.util.Validations;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
 * @author: heyue
 */
@Api(value = "insecure", description = "Insecure APIs", position = 2)
@Path("/insecure")
@Slf4j
@RestController
@RequestMapping(value = "insecure", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class InsecureController{

    @Autowired
    private TokenFactory<Long> tokenFactory;

    @Autowired
    private SmsSender smsSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private VerificationCache<String, String, String> verifyCache;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private SystemDefaultPropertyService systemDefaultPropertyService;
    
    @Value("${ucpaas.Account.Sid}")
    private String accountSid;
    
    @Value("${auth_Token}")
    private String authToken;


    @javax.ws.rs.POST
    @Path("/verify_code")
    @ApiOperation(value = "Get Verification Code", response = StatusResponse.class, position = 0)
    @ApiResponses({
            @ApiResponse(code = ILLEGAl_PARAMETER, message = "Illegal parameter", response = StatusResponse.class),
            @ApiResponse(code = CELL_NO_INVALID, message = "Cell no invalid", response = StatusResponse.class),
            @ApiResponse(code = REGISTER_CELLNO_DUPLICATE, message = "Register: cell duplicate", response = StatusResponse.class),
            @ApiResponse(code = RESET_CELLNO_NOTEXIST, message = "FindPwd: cell no such", response = StatusResponse.class),
            @ApiResponse(code = MODIFY_CELLNO_NOTEXIST, message = "Modify: cell no such", response = StatusResponse.class),
            @ApiResponse(code = SMS_SEND_FAILED, message = "sms code send failed", response = StatusResponse.class),
            @ApiResponse(code = METHOD_ARGUMENT_NOT_VALID, message = "Request Entity Blank", response = StatusResponse.class),
            @ApiResponse(code = UNDEFINDED, message = "Unknown Error", response = StatusResponse.class)
    })
    @RequestMapping(value = "/verify_code", method = POST)
    public
    @ResponseBody
    StatusResponse getCode(@ApiParam(required = true) @Valid @NotNull @RequestBody VerifyCodeRequest verifyCodeRequest,  Locale locale){

        String cellNo = verifyCodeRequest.getCell();
        String type = verifyCodeRequest.getType();

        if(StringUtils.isBlank(cellNo)){
            return error(CELL_NO_EMPTY);
        }

        if(!Validations.validateCellNo(cellNo)){
            return error(CELL_NO_INVALID);
        }

        if(!Lists.newArrayList(Constant.MODIFY, Constant.REGISTER, Constant.RESTPWD).contains(type)){
            return error(ILLEGAl_PARAMETER);
        }

        boolean isExists = consumerService.userNameExists(cellNo);

        if(Constant.REGISTER.equals(type) && isExists){
            return error(REGISTER_CELLNO_DUPLICATE);
        }else if(Constant.RESTPWD.equals(type) && !isExists){
            return error(RESET_CELLNO_NOTEXIST);
        }else if(Constant.MODIFY.equals(type) && !isExists){
            return error(MODIFY_CELLNO_NOTEXIST);
        }

        Map<String, String> smsTemplate = Maps.newHashMap();
        smsTemplate.put(Constant.REGISTER, "register.sms.template");
        smsTemplate.put(Constant.RESTPWD, "findPwd.sms.template");
        smsTemplate.put(Constant.MODIFY, "modify.sms.template");

        log.info("User [{}] is attempting to get code.", cellNo);

        String code = verifyCache.produceCode(cellNo);
        log.info("Verification code [{}] for user [{}] created.", code, cellNo);

        if(!smsSender.send(messageSource.getMessage(smsTemplate.get(type), new String[]{code}, locale), Lists.newArrayList(cellNo))){
            return error(SMS_SEND_FAILED);
        }
        return success();
    }

    @javax.ws.rs.POST
    @Path("/signin")
    @ApiOperation(value = "User Sign In", response = SessionToken.class, position = 1)
    @ApiResponses({@ApiResponse(code = METHOD_ARGUMENT_NOT_VALID, message = "Request Entity Blank", response = StatusResponse.class),
            @ApiResponse(code = CELL_NO_INVALID, message = "Cell no invalid", response = StatusResponse.class),
            @ApiResponse(code = BAD_CREDENTIAL, message = "Bad Credential", response = StatusResponse.class),
            @ApiResponse(code = USER_LOCK, message = "Customer locked", response = StatusResponse.class),
            @ApiResponse(code = UNDEFINDED, message = "Unknown Error", response = StatusResponse.class)})
    @RequestMapping(value = "/signin", method = POST)
    public
    @ResponseBody
    StatusResponse signin(@ApiParam(required = true) @Valid @NotNull @RequestBody SigninRequest signinRequest){

        log.info("User [{}] is attempting to login.", signinRequest.getCellNo());

        if(!Validations.validateCellNo(signinRequest.getCellNo())){
            return error(BAD_CREDENTIAL);
        }

        Consumer consumer = null;
        try{
           consumer = consumerService.signin(signinRequest.getCellNo(), signinRequest.getPassword());
        }catch(RespondableException re){
            return error(re.getErrorCode());
        }catch(Exception e){
            return error(UNDEFINDED);
        }
        
        return success(ClientVo.of(consumer, tokenFactory.createToken(consumer.getId()).toString()));
    }

    @GET
    @Path("/signout")
    @ApiOperation(value = "User Sign Out", response = BooleanEntity.class, position = 2)
    @ApiResponses({@ApiResponse(code = TOKEN_REQUIRED, message = "Token Required", response = StatusResponse.class),
            @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class)})
    @RequestMapping(value = "/signout", method = GET)
    public
    @ResponseBody
    StatusResponse signOut(@ApiParam(required = true)@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY)  String serializedToken){
        SessionToken sessionToken = SessionToken.valueOf(serializedToken);
        tokenFactory.remove(sessionToken.getKey());
        return success(new BooleanEntity(true));
    }

    @javax.ws.rs.POST
    @Path("/register")
    @ApiOperation(value = "User Registration", response = SessionToken.class, position = 4)
    @ApiResponses({@ApiResponse(code = CODE_EXPIRE, message = "Code expire", response = StatusResponse.class),
            @ApiResponse(code = CODE_REQUIRED, message = "Code Required", response = StatusResponse.class),
            @ApiResponse(code = CODE_INVALID, message = "Code Invalid", response = StatusResponse.class),
            @ApiResponse(code = ILLEGAl_PARAMETER, message = "Illegal parameter", response = StatusResponse.class),
            @ApiResponse(code = CELL_NO_INVALID, message = "Cell no invalid", response = StatusResponse.class),
            @ApiResponse(code = REGISTER_CELLNO_DUPLICATE, message = "Cell no duplicate", response = StatusResponse.class),
            @ApiResponse(code = CELL_NO_EMPTY, message = "Cell no empty", response = StatusResponse.class),
            @ApiResponse(code = PASSWORD_INVALID, message = "Password invalid", response = StatusResponse.class),
            @ApiResponse(code = METHOD_ARGUMENT_NOT_VALID, message = "Request Entity Blank", response = StatusResponse.class),
            @ApiResponse(code = UNDEFINDED, message = "Unknown Error", response = StatusResponse.class),
            @ApiResponse(code = PASSWORD_DIFFERENCE, message = "Password Difference", response = StatusResponse.class)})
    @RequestMapping(value = "/register", method = POST)
    public
    @ResponseBody
    StatusResponse register(@ApiParam(required = true) @Valid @NotNull @RequestBody UserRegistration registration){

//        String code = registration.getCode();
        String cell = registration.getCell();
        String password = registration.getPassword();
        
        AppType type = registration.getType();
        String appToken = registration.getAppToken();

//        log.debug("User [{}] is attempting to register with cell [{}] and code [{}] and appType [{}] and appToken [{}].", cell, cell, code,type.name(),appToken);

        if(!Validations.validateCellNo(cell)){
            return error(CELL_NO_INVALID);
        }

        if(!Validations.validatePassword(registration.getPassword())){
            return error(PASSWORD_INVALID);
        }

        if(consumerService.userNameExists(cell)){
            return error(REGISTER_CELLNO_DUPLICATE);
        }

//        if(StringUtils.isBlank(code)){
//            return error(CODE_REQUIRED);
//        }

//        if(StringUtils.isBlank(code) || !verifyCache.consumeCode(cell, code)){
//            return error(CODE_INVALID);
//        }
        
        if(!registration.getConfirmPassword().equals(registration.getPassword())){
        	return error(PASSWORD_DIFFERENCE);
        }
        
        Consumer consumer = consumerService.register(cell, password,type,appToken);
        
        
        
        
        return success();
    }
    
    @javax.ws.rs.POST
    @Path("/find_pwd")
    @ApiOperation(value = "Find Password", response = BooleanEntity.class, position = 5)
    @ApiResponses({@ApiResponse(code = CODE_REQUIRED, message = "Ticket Required", response = StatusResponse.class),
            @ApiResponse(code = CODE_INVALID, message = "Ticket Invalid", response = StatusResponse.class),
            @ApiResponse(code = CODE_EXPIRE, message = "Ticket Invalid", response = StatusResponse.class),           @ApiResponse(code = RESET_CELLNO_NOTEXIST, message = "Find password cellNo no such", response = StatusResponse.class),
            @ApiResponse(code = METHOD_ARGUMENT_NOT_VALID, message = "Request Entity Blank", response = StatusResponse.class),
            @ApiResponse(code = UNDEFINDED, message = "Unknown Error", response = StatusResponse.class),
            @ApiResponse(code = PASSWORD_DIFFERENCE, message = "Password Difference", response = StatusResponse.class)})
    @RequestMapping(value = "/find_pwd", method = POST)
    public
    @ResponseBody
    StatusResponse findPassword(@ApiParam(required = true) @Valid @NotNull @RequestBody FindPasswordRequest findPasswordRequest){

        String code = findPasswordRequest.getCode();
        String cellNo = findPasswordRequest.getCellNo();
        String password = findPasswordRequest.getPassword();

//        if(StringUtils.isBlank(code)){
//            return error(CODE_REQUIRED);
//        }

        if(!Validations.validateCellNo(cellNo)){
            return error(CELL_NO_INVALID);
        }

        if(!consumerService.userNameExists(cellNo)){
            return error(RESET_CELLNO_NOTEXIST);
        }

        if(!Validations.validatePassword(password)){
            return error(PASSWORD_INVALID);
        }

        log.info("User [{}] is attempting to reset password with code [{}].", cellNo, code);

//        if(StringUtils.isBlank(code) || !verifyCache.consumeCode(cellNo, code)){
//            return error(CODE_INVALID);
//        }

        Consumer consumer = consumerService.identify(cellNo);

        boolean result = consumerService.findPassword(consumer.getId(), password);
        return success(new BooleanEntity(result));
    }
    
//    /**
//     * <p>Description:获取client账号密码/p>
//     * @author:宁良竹
//     * @update: 2016年4月1日
//     * @param serializedToken
//     * @param id
//     * @return
//     */
//    @GET
//    @Path("/getClientInfo")
//    @ApiOperation(value = "Get ClientInfo", response = BooleanEntity.class, position = 6)
//    @ApiResponses({@ApiResponse(code = TOKEN_REQUIRED, message = "Token Required", response = StatusResponse.class),
//            @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class)})
//    @RequestMapping(value = "/getClientInfo", method = GET)
//    public
//    @ResponseBody
//    StatusResponse getClientInfo(@ApiParam(required = false)@HeaderParam(TOKEN_HEADER_KEY) @RequestHeader(TOKEN_HEADER_KEY)  String serializedToken,
//    		@ApiParam(name = "ExpertsId", required = false, value = "专家id") @FormParam("id") Long id){
//    	Consumer consumer =null;
//    	if(id!=null){
//        SessionToken sessionToken = SessionToken.valueOf(serializedToken);
//        consumer = consumerService.findOne(sessionToken.getKey());
//    	}else
//    		consumer = consumerService.findOne(id);
//        return success(ClientVo.of(consumer));
//    }
    


    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class FindPasswordRequest{

        @ApiModelProperty(value = "Code", required = true, position = 0)
        @JsonProperty("code")
        @NotBlank
        private String code;

        @ApiModelProperty(value = "Cell No", required = true, position = 1)
        @JsonProperty("cell")
        @NotBlank
        private String cellNo;

        @ApiModelProperty(value = "Password", required = true, position = 2)
        @JsonProperty("password")
        @NotBlank
        private String password;
        

        @JsonCreator
        public FindPasswordRequest(@JsonProperty("code") @NotBlank String code,
                                   @JsonProperty("password") @NotBlank String password,
                                   @JsonProperty("cell") @NotBlank String cellNo){
            this.code = code;
            this.password = password;
            this.cellNo = cellNo;
        }
    }


    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class VerifyCodeRequest{

        @ApiModelProperty(value = "Cell no", required = true, position = 0)
        @JsonProperty("cell")
        @NotBlank
        private String cell;

        @ApiModelProperty(value = "Code type {register, restPwd, modify}", required = true, position = 1)
        @JsonProperty("type")
        @NotBlank
        private String type;

        @JsonCreator
        public VerifyCodeRequest(@JsonProperty("cell") @NotBlank String cell,
                                 @JsonProperty("type") @NotNull String type){
            this.cell = cell;
            this.type = type;
        }
    }


    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class SigninRequest{

        @ApiModelProperty(value = "Cell no", required = true, position = 0)
        @JsonProperty("cell")
        @NotBlank
        private String cellNo;

        @ApiModelProperty(value = "Password", required = true, position = 1)
        @JsonProperty("password")
        @NotBlank
        private String password;

        @JsonCreator
        public SigninRequest(@JsonProperty("cell") @NotBlank String cellNo,
                             @JsonProperty("password") @NotBlank String password){
            this.cellNo = cellNo;
            this.password = password;
        }
    }


    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class UserRegistration{

//        @ApiModelProperty(value = "Code", required = true, position = 0)
//        @JsonProperty("code")
//        @NotBlank
//        private String code;

        @ApiModelProperty(value = "Cell no", required = true, position = 1)
        @JsonProperty("cell")
        @NotBlank
        private String cell;

        @ApiModelProperty(value = "Password", required = true, position = 2)
        @JsonProperty("password")
        @NotBlank
        private String password;
        
        @ApiModelProperty(value = "ConfirmPassword", required = true, position = 3)
        @JsonProperty("confirmPassword")
        @NotBlank
        private String confirmPassword;
        
        @ApiModelProperty(value = "Type", required = true, position = 4)
        @JsonProperty("type")
        @NotNull
        private AppType type;
        
        @ApiModelProperty(value = "AppToken", required = true, position = 5)
        @JsonProperty("appToken")
        private String appToken;


        @JsonCreator
        public UserRegistration(/*@JsonProperty("code") @NotBlank String code,*/
                                @JsonProperty("cell") @NotBlank String cellNo,
                                @JsonProperty("confirmPassword") @NotBlank String confirmPassword,
                                @JsonProperty("password") @NotBlank String password,
                                @JsonProperty("type") @NotNull AppType type,
                                @JsonProperty("appToken") @NotBlank String appToken){
//            this.code = code;
            this.cell = cellNo;
            this.password = password;
            this.confirmPassword = confirmPassword;
            this.type = type;
            this.appToken =appToken;
        }
    }

    private Pageable wapperRequest(Integer offset, Integer limit){
        if(offset == null || limit == null)
            return new PageRequest(0, 20);
        if(offset < 0 || limit < 1)
            return new PageRequest(0, 20);
        return new PageRequest(offset, limit);
    }

    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class WapperRequest{

        @ApiModelProperty(value = "Offset", required = true)
        @JsonProperty("o")
        @NotNull
        private Integer offset;

        @ApiModelProperty(value = "Limit", required = true)
        @JsonProperty("l")
        @NotNull
        private Integer limit;

        @JsonCreator
        public WapperRequest(@JsonProperty("o") @NotNull Integer offset,
                             @JsonProperty("l") @NotNull Integer limit){
            this.offset = offset;
            this.limit = limit;
        }
    }
}
