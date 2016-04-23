package com.dsecet.api.controller;

import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static com.dsecet.foundation.model.StatusResponse.success;

import java.util.List;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsecet.api.security.SessionToken;
import com.dsecet.core.domain.news.News;
import com.dsecet.core.domain.system.Activity;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.service.ActivityService;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.ExpertsService;
import com.dsecet.core.service.admin.NewsService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.StatusResponse;
import com.dsecet.foundation.model.query.ActivityVo;
import com.dsecet.foundation.model.query.ExpertsListVo;
import com.dsecet.foundation.model.query.NewsVo;
import com.dsecet.util.ModelUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(value = "/index", description = "Index APIs", position = 11)
@Path("/index")
@Slf4j
@RestController
@RequestMapping(value = "/index", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class IndexController {
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ExpertsService expertsService;
	
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 首页
	 * @author heyeu
	 */
	@javax.ws.rs.GET
	@Path("/list")
	@ApiOperation(value = "Index list", response = StatusResponse.class, position = 1)
	@ApiResponses({ @ApiResponse(code = TOKEN_INVALID, message = "Token Invalid", response = StatusResponse.class), })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody StatusResponse list(
			@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token) {
		SessionToken sessionToken = SessionToken.valueOf(token);
		Consumer consumer = consumerService.get(sessionToken.getKey());
		ModelUtils.checkLock(consumer);
		
		Page<News> newsPage = newsService.findPage(null, new PageRequest(0, 2));
		Page expertsPage = expertsService.findRecommendExpertsCondition(sessionToken.getKey(), null,new PageRequest(1, 5));
		Page<Activity> activityPage = activityService.findPage(null, new PageRequest(0, 2));
		return success(IndexResponse.of(NewsVo.of(newsPage),ExpertsListVo.of(expertsPage.getContent()),ActivityVo.of(activityPage)));
	}
	
	 	@ApiModel
	    @Getter
	    @NoArgsConstructor
	    @EqualsAndHashCode
	    public static final class IndexResponse{
	    	
	    	@ApiModelProperty(value = "NewsVo", position = 1)
	    	@JsonProperty("newsVo")
	    	private List<NewsVo> newsVo;
	        
	    	@ApiModelProperty(value = "expertsListVo", position = 2)
	    	@JsonProperty("expertsListVo")
	    	private List<ExpertsListVo> expertsListVo;
	    	
	    	@ApiModelProperty(value = "activityVo", position = 3)
	    	@JsonProperty("activityVo")
	    	private List<ActivityVo> activityVo;
	    	
	    	@ApiModelProperty(value = "type", position = 4)
	    	@JsonProperty("type")
	    	private Long type;
	    	
	    	
			public IndexResponse(@JsonProperty("newsVo") List<NewsVo> newsVo,
								 @JsonProperty("expertsListVo") List<ExpertsListVo> expertsListVo,
								 @JsonProperty("activityVo") List<ActivityVo> activityVo) {
				this.newsVo = newsVo;
				this.expertsListVo = expertsListVo;
				this.activityVo = activityVo;
			}
			
			public static IndexResponse of(List<NewsVo> newsVo,List<ExpertsListVo> expertsListVo,List<ActivityVo> activityVo){
				return new IndexResponse(newsVo,expertsListVo,activityVo);
			}
	    }
}
