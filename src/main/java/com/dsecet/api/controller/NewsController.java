package com.dsecet.api.controller;

import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static com.dsecet.foundation.model.StatusResponse.success;

import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.StringUtils;
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
import com.dsecet.core.domain.news.News;
import com.dsecet.core.domain.system.SystemDefaultProperty;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.core.service.admin.NewsService;
import com.dsecet.core.service.admin.SystemDefaultPropertyService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.query.ExpertsVo;
import com.dsecet.foundation.model.query.NewsVo;
import com.dsecet.util.FileHelper;
import com.dsecet.util.ModelUtils;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import lombok.extern.slf4j.Slf4j;

@Api(value = "news", description = "News APIs", position = 5)
@Path("/news")
@Slf4j
@RestController
@RequestMapping(value = "news", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {
	
	
	/**
	 * <p>Description:新闻列表</p>
	 * @author:宁良竹
	 * @update: 2016年3月24日
	 * @param token
	 * @param title
	 * @param timeSort
	 * @param page
	 * @param size
	 * @return
	 */
	@javax.ws.rs.GET
    @Path("/list")
    @ApiOperation(value = "News list", response = StatusResponse.class, position = 0)
    @ApiResponses({@ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class),
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    StatusResponse list(
    		@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(required=true,value=TOKEN_HEADER_KEY)  String token,
			    		@ApiParam(name = "name",required = false,value="标题，用于搜索") @FormParam("name") String title,
            			@ApiParam(name = "timeSort: ASC || DESC ",required = false,value="时间排序") @FormParam("timeSort") String timeSort,
            			@ApiParam(name = "industryId ",required = false,value="行业id") @FormParam("industryId") Long industryId,
                        @ApiParam(name = "page",required = true) @QueryParam("page") Integer page,
                        @ApiParam(name = "size",required = true) @QueryParam("size") Integer size
    ){
		SessionToken sessionToken = SessionToken.valueOf(token);
        Consumer consumer = consumerService.get(sessionToken.getKey());
        ModelUtils.checkLock(consumer);
        
        PageableResponse<NewsVo> newsPage = newsService.findPage(title, timeSort,industryId, new PageRequest(page, size));
        return success(PageableResponse.of(newsPage.getCollection(), newsPage.getQueryRecordCount(), newsPage.getTotalRecordCount()));
    }
	
	/**
	 * <p>Description:获取新闻详情</p>
	 * @author:宁良竹
	 * @update: 2016年3月24日
	 * @param token
	 * @param newsId
	 * @return
	 */
	@javax.ws.rs.GET
    @Path("/detail")
    @ApiOperation(value = "News detail", response = StatusResponse.class, position = 1)
    @ApiResponses({@ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class),
    })
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public @ResponseBody
    StatusResponse detail(
    		@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(required=true,value=TOKEN_HEADER_KEY)  String token,
                        @ApiParam(name = "newsId",required = true,value="新闻id") @QueryParam("newsId") Long newsId
    ){
		SessionToken sessionToken = SessionToken.valueOf(token);
        Consumer consumer = consumerService.get(sessionToken.getKey());
        ModelUtils.checkLock(consumer);
        
        News news = newsService.findOne(newsId);
        String content = "";
        if(StringUtils.isNotBlank(news.getStoragePath())){
            String filePath = SystemProperty.storageFilePrefix + news.getStoragePath();
            try{
                content = FileHelper.readerToString(filePath);
            }catch(IOException e){
            }
            }
        return success(NewsVo.detailOf(news,content));
    }
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ConsumerService consumerService;
	
}
