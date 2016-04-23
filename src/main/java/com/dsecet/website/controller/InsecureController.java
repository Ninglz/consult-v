package com.dsecet.website.controller;

import static com.dsecet.foundation.model.ErrorCode.BAD_CREDENTIAL;
import static com.dsecet.foundation.model.ErrorCode.CELL_NO_INVALID;
import static com.dsecet.foundation.model.ErrorCode.PASSWORD_DIFFERENCE;
import static com.dsecet.foundation.model.ErrorCode.PASSWORD_INVALID;
import static com.dsecet.foundation.model.ErrorCode.REGISTER_CELLNO_DUPLICATE;
import static com.dsecet.foundation.model.ErrorCode.UNDEFINDED;
import static com.dsecet.foundation.model.StatusResponse.error;
import static com.dsecet.foundation.model.StatusResponse.success;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsecet.core.domain.user.Consumer.AppType;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import com.dsecet.ext.sms.SmsSender;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.VerificationCache;
import com.dsecet.foundation.model.query.ExpertsDetailVo;
import com.dsecet.util.Validations;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: heyue
 */
@Slf4j
@Controller
@RequestMapping(value = "/insecure")
public class InsecureController{


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


//    @javax.ws.rs.POST
//    @Path("/verify_code")
//    @RequestMapping(value = "/verify_code", method = POST)
//    public
//    @ResponseBody
//    StatusResponse getCode(@ApiParam(required = true) @Valid @NotNull @RequestBody VerifyCodeRequest verifyCodeRequest,  Locale locale){
//
//        String cellNo = verifyCodeRequest.getCell();
//        String type = verifyCodeRequest.getType();
//
//        if(StringUtils.isBlank(cellNo)){
//            return error(CELL_NO_EMPTY);
//        }
//
//        if(!Validations.validateCellNo(cellNo)){
//            return error(CELL_NO_INVALID);
//        }
//
//        if(!Lists.newArrayList(Constant.MODIFY, Constant.REGISTER, Constant.RESTPWD).contains(type)){
//            return error(ILLEGAl_PARAMETER);
//        }
//
//        boolean isExists = consumerService.userNameExists(cellNo);
//
//        if(Constant.REGISTER.equals(type) && isExists){
//            return error(REGISTER_CELLNO_DUPLICATE);
//        }else if(Constant.RESTPWD.equals(type) && !isExists){
//            return error(RESET_CELLNO_NOTEXIST);
//        }else if(Constant.MODIFY.equals(type) && !isExists){
//            return error(MODIFY_CELLNO_NOTEXIST);
//        }
//
//        Map<String, String> smsTemplate = Maps.newHashMap();
//        smsTemplate.put(Constant.REGISTER, "register.sms.template");
//        smsTemplate.put(Constant.RESTPWD, "findPwd.sms.template");
//        smsTemplate.put(Constant.MODIFY, "modify.sms.template");
//
//        log.info("User [{}] is attempting to get code.", cellNo);
//
//        String code = verifyCache.produceCode(cellNo);
//        log.info("Verification code [{}] for user [{}] created.", code, cellNo);
//
//        if(!smsSender.send(messageSource.getMessage(smsTemplate.get(type), new String[]{code}, locale), Lists.newArrayList(cellNo))){
//            return error(SMS_SEND_FAILED);
//        }
//        return success();
//    }
    
    private void  setSessionAttribute(Experts experts,HttpSession session){
    	session.setAttribute("user", ExpertsDetailVo.of(experts));
    	if(experts.getExpertsRecord()!=null&&experts.getExpertsRecord().size()>0){
    		session.setAttribute("city", experts.getCity().getName());
    		session.setAttribute("province", experts.getCity().getProvince().getName());
    		session.setAttribute("industryName", experts.getExpertsRecord().get(0).getIndustry().getName());
    		session.setAttribute("goodAt", experts.getExpertsRecord().get(0).getGoodAt());
    	}
    }

    @Path("/signin")
    @RequestMapping(value = "/signin", method = POST)
    public
    @ResponseBody
    StatusResponse signin(@NotBlank String cellNo,@NotBlank  String password,HttpSession session){

        log.info("User [{}] is attempting to login.", cellNo);

        if(!Validations.validateCellNo(cellNo)){
            return error(BAD_CREDENTIAL);
        }
        try{
        	setSessionAttribute(
        			(Experts)consumerService.signin(cellNo, password),
        			session);
        }catch(RespondableException re){
            return error(re.getErrorCode());
        }catch(Exception e){
            return error(UNDEFINDED);
        }
        
        return success();
    }


    @RequestMapping(value = "/register", method = POST)
    public
    @ResponseBody
    StatusResponse register(@NotBlank String cellNo,@NotBlank  String password,@NotBlank String confirmPassword,HttpSession session){

        
//        log.debug("User [{}] is attempting to register with cell [{}] and code [{}] and appType [{}] and appToken [{}].", cell, cell, code,type.name(),appToken);

        if(!Validations.validateCellNo(cellNo)){
            return error(CELL_NO_INVALID);
        }

        if(!Validations.validatePassword(password)){
            return error(PASSWORD_INVALID);
        }

        if(consumerService.userNameExists(cellNo)){
            return error(REGISTER_CELLNO_DUPLICATE);
        }

//        if(StringUtils.isBlank(code)){
//            return error(CODE_REQUIRED);
//        }

//        if(StringUtils.isBlank(code) || !verifyCache.consumeCode(cell, code)){
//            return error(CODE_INVALID);
//        }
        
        if(!confirmPassword.equals(password)){
        	return error(PASSWORD_DIFFERENCE);
        }
        
        setSessionAttribute(
        		(Experts)consumerService.register(cellNo, password,AppType.PC,null)
        		, session);
        
        return success();
    }
//    
//    @javax.ws.rs.POST
//    @Path("/find_pwd")
//    @RequestMapping(value = "/find_pwd", method = POST)
//    public
//    @ResponseBody
//    StatusResponse findPassword(@ApiParam(required = true) @Valid @NotNull @RequestBody FindPasswordRequest findPasswordRequest){
//
//        String code = findPasswordRequest.getCode();
//        String cellNo = findPasswordRequest.getCellNo();
//        String password = findPasswordRequest.getPassword();
//
////        if(StringUtils.isBlank(code)){
////            return error(CODE_REQUIRED);
////        }
//
//        if(!Validations.validateCellNo(cellNo)){
//            return error(CELL_NO_INVALID);
//        }
//
//        if(!consumerService.userNameExists(cellNo)){
//            return error(RESET_CELLNO_NOTEXIST);
//        }
//
//        if(!Validations.validatePassword(password)){
//            return error(PASSWORD_INVALID);
//        }
//
//        log.info("User [{}] is attempting to reset password with code [{}].", cellNo, code);
//
////        if(StringUtils.isBlank(code) || !verifyCache.consumeCode(cellNo, code)){
////            return error(CODE_INVALID);
////        }
//
//        Consumer consumer = consumerService.identify(cellNo);
//
//        boolean result = consumerService.findPassword(consumer.getId(), password);
//        return success(new BooleanEntity(result));
//    }
//    
//    
//
//
//    @ApiModel
//    @Getter
//    @NoArgsConstructor
//    @EqualsAndHashCode
//    public static final class FindPasswordRequest{
//
//        @ApiModelProperty(value = "Code", required = true, position = 0)
//        @JsonProperty("code")
//        @NotBlank
//        private String code;
//
//        @ApiModelProperty(value = "Cell No", required = true, position = 1)
//        @JsonProperty("cell")
//        @NotBlank
//        private String cellNo;
//
//        @ApiModelProperty(value = "Password", required = true, position = 2)
//        @JsonProperty("password")
//        @NotBlank
//        private String password;
//        
//
//        @JsonCreator
//        public FindPasswordRequest(@JsonProperty("code") @NotBlank String code,
//                                   @JsonProperty("password") @NotBlank String password,
//                                   @JsonProperty("cell") @NotBlank String cellNo){
//            this.code = code;
//            this.password = password;
//            this.cellNo = cellNo;
//        }
//    }
//
//
//    @ApiModel
//    @Getter
//    @NoArgsConstructor
//    @EqualsAndHashCode
//    public static final class VerifyCodeRequest{
//
//        @ApiModelProperty(value = "Cell no", required = true, position = 0)
//        @JsonProperty("cell")
//        @NotBlank
//        private String cell;
//
//        @ApiModelProperty(value = "Code type {register, restPwd, modify}", required = true, position = 1)
//        @JsonProperty("type")
//        @NotBlank
//        private String type;
//
//        @JsonCreator
//        public VerifyCodeRequest(@JsonProperty("cell") @NotBlank String cell,
//                                 @JsonProperty("type") @NotNull String type){
//            this.cell = cell;
//            this.type = type;
//        }
//    }
//
//
//
//    @ApiModel
//    @Getter
//    @NoArgsConstructor
//    @EqualsAndHashCode
//    public static final class UserRegistration{
//
////        @ApiModelProperty(value = "Code", required = true, position = 0)
////        @JsonProperty("code")
////        @NotBlank
////        private String code;
//
//        @ApiModelProperty(value = "Cell no", required = true, position = 1)
//        @JsonProperty("cell")
//        @NotBlank
//        private String cell;
//
//        @ApiModelProperty(value = "Password", required = true, position = 2)
//        @JsonProperty("password")
//        @NotBlank
//        private String password;
//        
//        @ApiModelProperty(value = "ConfirmPassword", required = true, position = 3)
//        @JsonProperty("confirmPassword")
//        @NotBlank
//        private String confirmPassword;
//        
//        @ApiModelProperty(value = "Type", required = true, position = 4)
//        @JsonProperty("type")
//        @NotNull
//        private AppType type;
//        
//        @ApiModelProperty(value = "AppToken", required = true, position = 5)
//        @JsonProperty("appToken")
//        private String appToken;
//
//
//        @JsonCreator
//        public UserRegistration(/*@JsonProperty("code") @NotBlank String code,*/
//                                @JsonProperty("cell") @NotBlank String cellNo,
//                                @JsonProperty("confirmPassword") @NotBlank String confirmPassword,
//                                @JsonProperty("password") @NotBlank String password,
//                                @JsonProperty("type") @NotNull AppType type,
//                                @JsonProperty("appToken") @NotBlank String appToken){
////            this.code = code;
//            this.cell = cellNo;
//            this.password = password;
//            this.confirmPassword = confirmPassword;
//            this.type = type;
//            this.appToken =appToken;
//        }
//    }
//
//    private Pageable wapperRequest(Integer offset, Integer limit){
//        if(offset == null || limit == null)
//            return new PageRequest(0, 20);
//        if(offset < 0 || limit < 1)
//            return new PageRequest(0, 20);
//        return new PageRequest(offset, limit);
//    }
//
//    @ApiModel
//    @Getter
//    @NoArgsConstructor
//    @EqualsAndHashCode
//    public static final class WapperRequest{
//
//        @ApiModelProperty(value = "Offset", required = true)
//        @JsonProperty("o")
//        @NotNull
//        private Integer offset;
//
//        @ApiModelProperty(value = "Limit", required = true)
//        @JsonProperty("l")
//        @NotNull
//        private Integer limit;
//
//        @JsonCreator
//        public WapperRequest(@JsonProperty("o") @NotNull Integer offset,
//                             @JsonProperty("l") @NotNull Integer limit){
//            this.offset = offset;
//            this.limit = limit;
//        }
//    }
}
