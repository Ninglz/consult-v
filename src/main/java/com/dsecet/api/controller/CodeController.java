package com.dsecet.api.controller;

import static com.dsecet.foundation.model.StatusResponse.success;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsecet.core.domain.record.City;
import com.dsecet.core.domain.record.Province;
import com.dsecet.core.domain.system.Industry;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.service.CityService;
import com.dsecet.core.service.ProvinceService;
import com.dsecet.core.service.admin.IndustryService;
import com.dsecet.foundation.model.StatusResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponses;

import lombok.extern.slf4j.Slf4j;

@Api(value = "code", description = "Code APIs", position = 0)
@Path("/code")
@Slf4j
@RestController
@RequestMapping(value = "code", consumes = MediaType.ALL_VALUE)
public class CodeController {

	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private IndustryService industryService;
	
	/**
     * 省份列表
     * */
    @GET
    @Path("/province/list")
    @ApiOperation(value = "Provice List", response = Consumer.class, responseContainer = "Array", position = 0)
    @ApiResponses({})
	@RequestMapping(value = "/province/list", method = RequestMethod.GET)
	public 
	@ResponseBody StatusResponse getProvince(){
        return success(
        		provinceService.findAll()
        		);
	}
    
    /**
     * 城市列表
     * */
    @GET
    @Path("/city/list")
    @ApiOperation(value = "City List", response = Consumer.class, responseContainer = "Array", position = 1)
    @ApiResponses({})
	@RequestMapping(value = "/city/list", method = RequestMethod.GET)
	public 
	@ResponseBody StatusResponse getConsumer(@ApiParam(name = "id",value="province id",required = true) @QueryParam("id")Long id){
        return success(
        		cityService.findCityByProvinceId(id)
        		);
	}
    
    /**
     * 行业列表
     * */
    @GET
    @Path("/industry/list")
    @ApiOperation(value = "Industry List", response = Consumer.class, responseContainer = "Array", position = 2)
    @ApiResponses({})
	@RequestMapping(value = "/industry/list", method = RequestMethod.GET)
	public 
	@ResponseBody StatusResponse getIndustry(){
        return success(
        		industryService.findAll()
        		);
	}
}
