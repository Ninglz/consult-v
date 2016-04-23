package com.dsecet.api.controller;

import com.dsecet.api.security.SessionToken;
import com.dsecet.core.domain.mongo.DataType;
import com.dsecet.core.domain.mongo.TransientDataShare;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.util.ModelUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Map;

import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.*;
import static com.dsecet.foundation.model.StatusResponse.error;
import static com.dsecet.foundation.model.StatusResponse.success;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author: lxp
 * Date: 2015/6/16 11:07
 * safeguard-v1
 */
//@Api(value = "security", description = "Security APIs", position = 3)
//@Path("/security")
//@Slf4j
//@RestController
@RequestMapping(value = "security", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class SecurityController{

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SystemDefaultPropertyService systemDefaultPropertyService;
    

//    @javax.ws.rs.POST
//    @Path("/password")
//    @ApiOperation(value = "Update signin password", response = StatusResponse.class, position = 1)
//    @ApiResponses({@ApiResponse(code = TOKEN_REQUIRED, message = "Token Required", response = StatusResponse.class),
//            @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class),
//            @ApiResponse(code = TOKEN_EXPIRED, message = "Token Expired", response = StatusResponse.class),
//            @ApiResponse(code = METHOD_ARGUMENT_NOT_VALID, message = "Request Entity Blank", response = StatusResponse.class),
//            @ApiResponse(code = USER_NO_SUCH, message = "No Such Customer", response = StatusResponse.class),
//            @ApiResponse(code = PASSWORD_INVALID, message = "Password invalid", response = StatusResponse.class),
//            @ApiResponse(code = ILLEGAl_PARAMETER, message = "Illegal parameter", response = StatusResponse.class),
//            @ApiResponse(code = ORIGINAL_PASSWORD_BAD_CREDENTIAL, message = "Original password error", response = StatusResponse.class),
//            @ApiResponse(code = UNDEFINDED, message = "Unknown Error", response = StatusResponse.class)
//    })
    @RequestMapping(value = "/password", method = POST)
    public
    @ResponseBody
    StatusResponse password(@HeaderParam(TOKEN_HEADER_KEY) @ApiParam(required = true) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String serializedToken,
                            @ApiParam(required = true) @Valid @NotNull @RequestBody PasswordRequest passwordRequest){
        SessionToken token = SessionToken.valueOf(serializedToken);
        Consumer consumer = consumerService.get(token.getUserId());
        ModelUtils.checkCustomer(consumer);

        if(!consumer.verifyPassword(passwordRequest.getOriginalPassword())){
            return error(ORIGINAL_PASSWORD_BAD_CREDENTIAL);
        }
        try{
            consumerService.resetPassword(consumer.getId(), passwordRequest.getSettingPassword(), passwordRequest.getSettingPassword());
        }catch(RespondableException e){
            return error(e.getErrorCode());
        }catch(Exception e){
            return error(UNDEFINDED);
        }
        return success();
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
    public static final class TransientDataRequest{

        @ApiModelProperty(value = "Transient temperatures", required = true, position = 0)
        @JsonProperty("temperatures")
        @NotBlank
        private String[] temperatures;

        @ApiModelProperty(value = "Transient humiditys", required = true, position = 1)
        @JsonProperty("humiditys")
        @NotBlank
        private String[] humiditys;

        @ApiModelProperty(value = "Transient data", required = true, position = 2)
        @JsonProperty("datas")
        @NotBlank
        private String[] datas;

        @ApiModelProperty(value = "DataType", dataType = "enum", required = true, position = 3)
        @JsonProperty("dataType")
        @NotBlank
        private DataType dataType;

        @JsonCreator
        public TransientDataRequest(@JsonProperty("datas") @NotBlank String[] datas,
                                    @JsonProperty("temperatures") @NotBlank String[] temperatures,
                                    @JsonProperty("humiditys") @NotBlank String[] humiditys,
                                    @JsonProperty("dataType") @NotBlank DataType dataType){
            this.datas = datas;
            this.temperatures = temperatures;
            this.humiditys = humiditys;
            this.dataType = dataType;
        }
    }


    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class PasswordRequest{

        @ApiModelProperty(value = "Original password", required = true, position = 0)
        @JsonProperty("op")
        @NotBlank
        private String originalPassword;

        @ApiModelProperty(value = "Setting passowrd", required = true, position = 1)
        @JsonProperty("sp")
        @NotBlank
        private String settingPassword;

        @JsonCreator
        public PasswordRequest(@JsonProperty("op") @Valid String originalPassword,
                               @JsonProperty("sp") @Valid String settingPassword){
            this.originalPassword = originalPassword;
            this.settingPassword = settingPassword;
        }
    }

}
