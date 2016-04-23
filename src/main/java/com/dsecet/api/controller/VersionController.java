//package com.dsecet.api.controller;
//
//import com.dsecet.api.vo.VersionBuilder;
//import com.dsecet.api.vo.VersionRespones;
//import com.dsecet.core.domain.system.SystemDefaultProperty;
//import com.dsecet.core.domain.version.AppVersion;
//import com.dsecet.core.service.HardwareVersionService;
//import com.dsecet.core.service.VersionService;
//import com.dsecet.core.service.admin.SystemDefaultPropertyService;
//import com.dsecet.foundation.model.StatusResponse;
//import com.dsecet.util.EnumUtils;
//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//import com.wordnik.swagger.annotations.ApiResponse;
//import com.wordnik.swagger.annotations.ApiResponses;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.QueryParam;
//
//import static com.dsecet.foundation.model.ErrorCode.ILLEGAl_PARAMETER;
//import static com.dsecet.foundation.model.ErrorCode.UNDEFINDED;
//
///**
// * @author: lxp
// */
//@Api(value = "version", description = "Version APIs", position = 2)
//@Path("/version")
//@Slf4j
//@RestController
//@RequestMapping(value = "version", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//public class VersionController{
//
//    @Autowired
//    private VersionService versionService;
//
//    @Autowired
//    private HardwareVersionService hardwareVersionService;
//
//    @Autowired
//    private SystemDefaultPropertyService systemDefaultPropertyService;
//
//    @GET
//    @Path("/apk")
//    @ApiOperation(value = "Get laster version default get android apk version", response = VersionRespones.class, position = 0)
//    @ApiResponses({
//            @ApiResponse(code = ILLEGAl_PARAMETER, message = "Illegal parameter", response = StatusResponse.class),
//            @ApiResponse(code = UNDEFINDED, message = "Unknown Error", response = StatusResponse.class)})
//    @RequestMapping(value = "/apk", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    StatusResponse version(){
//        AppVersion appVersion = versionService.findLasterVersion();
//        String urlPrefix = null;
//        if(null == appVersion){
//            return StatusResponse.success();
//        }
//        SystemDefaultProperty systemDefaultProperty = systemDefaultPropertyService.getUsed();
//            urlPrefix = systemDefaultProperty.getWebsiteStaticUrl() + systemDefaultProperty.getAppPrefix();
//        String downloadURL = urlPrefix + appVersion.getRelativePath();
//        return StatusResponse.success(VersionBuilder.buildVersionRespones(appVersion).wapperUrl(downloadURL));
//    }
//
//    @GET
//    @Path("/hardwares")
//    @ApiOperation(value = "Get hardware list", response = VersionRespones.class, responseContainer = "Array", position = 1)
//    @ApiResponses({
//            @ApiResponse(code = ILLEGAl_PARAMETER, message = "Illegal parameter", response = StatusResponse.class),
//            @ApiResponse(code = UNDEFINDED, message = "Unknown Error", response = StatusResponse.class)})
//    @RequestMapping(value = "/hardwares", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    StatusResponse hardwares(){
//        return StatusResponse.success(hardwareVersionService.findAll());
//    }
//
//    private Pageable wapperRequest(Integer offset, Integer limit){
//        if(offset == null || limit == null)
//            return new PageRequest(0, 20);
//        if(offset < 0 || limit < 1)
//            return new PageRequest(0, 20);
//        return new PageRequest(offset, limit);
//    }
//}
