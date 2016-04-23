package com.dsecet.api.controller;

import static com.dsecet.api.security.SessionTokenInterceptor.TOKEN_HEADER_KEY;
import static com.dsecet.foundation.model.ErrorCode.COMMENT_SCORE_EMPTY;
import static com.dsecet.foundation.model.ErrorCode.NO_COMMENT_YOURSELF;
import static com.dsecet.foundation.model.StatusResponse.error;
import static com.dsecet.foundation.model.StatusResponse.success;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.NotBlank;
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

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.service.CommentService;
import com.dsecet.foundation.model.StatusResponse;
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

@Api(value = "/comment", description = "Comment APIs", position = 7)
@Path("/comment")
@Slf4j
@RestController
@RequestMapping(value = "/comment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController extends BaseController{
	
	@Autowired
	private CommentService commentService;

	/**
	 * @author heyue
	 * 添加评论
	 * */
	@javax.ws.rs.POST
    @Path("/add")
    @ApiOperation(value = "Add Comment", response = StatusResponse.class, position = 0)
    @ApiResponses({
    	@ApiResponse(code = COMMENT_SCORE_EMPTY, message = "Comment Score Required", response = StatusResponse.class)})
    @RequestMapping(value = "/add", method = POST)
    public
    @ResponseBody
    StatusResponse add(
    		@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
    		@ApiParam(required = true) @Valid @NotNull @RequestBody CommentRequest commentRequest){
    	
    	Experts consumer = validationToken(token);
        
        if(commentRequest.getScore() == null){ 
        	return error(COMMENT_SCORE_EMPTY);
        }

        if(consumer.getId() == commentRequest.getCommentedId()){

        	return error(NO_COMMENT_YOURSELF);
        }

        commentService.saveComment(consumer,commentRequest);

        log.info("join consumer add comment method : commentId[{}],commentedId[{}],socre[{}],content[{}]");
        return success();
    }
	
	/**
	 * 评论列表
	 * @author heyue
	 * */
    @javax.ws.rs.GET
    @Path("/list")
    @ApiOperation(value = "Comment list", response = StatusResponse.class, position = 1)
    @ApiResponses({
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    StatusResponse list(@HeaderParam(TOKEN_HEADER_KEY) @NotBlank @RequestHeader(TOKEN_HEADER_KEY) String token,
    		@ApiParam(name = "expertsId",required = true,value="专家Id") @FormParam("expertsId") @RequestParam(required = true) Long expertsId,
    		@ApiParam(name = "page",required = true) @QueryParam("page") Integer page,
            @ApiParam(name = "size",required = true) @QueryParam("size") Integer size){
    	Consumer consumer = validationToken(token);
        return success(
        		commentService.findByCondition(expertsId,new PageRequest(page,size))
        		);
    }
    
    @ApiModel
    @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class CommentRequest{

        @ApiModelProperty(value = "commentedId", required = true, position = 1)
        @JsonProperty("commentedId")
        @NotNull
        private Long commentedId;
        
        @ApiModelProperty(value = "score", required = true, position = 2)
        @JsonProperty("score")
        @NotNull
        private Double score;

        @ApiModelProperty(value = "content", required = true, position = 3)
        @JsonProperty("content")
        private String content;
        
        @ApiModelProperty(value = "type", required = true, position = 4)
        @JsonProperty("type")
        @NotNull
        private Long type;

        @JsonCreator
        public CommentRequest(@JsonProperty("commentedId") @NotNull Long commentedId,
                              @JsonProperty("score") @NotNull Double score,
                              @JsonProperty("content") String content,
                              @JsonProperty("type") @NotNull Long type){
            this.commentedId =commentedId;
            this.score =score;
            this.content =content;
            this.type =type;
        }
    }
}
