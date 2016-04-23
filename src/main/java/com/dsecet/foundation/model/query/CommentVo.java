package com.dsecet.foundation.model.query;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dsecet.core.domain.record.Comment;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.foundation.model.NLZPage;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import lombok.Data;

/**
 * @author: heyue
 * 评论VO
 */
@Data
public class CommentVo{

    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("created")
    private String created;
    
    @JsonProperty("avatar")
    private String avatar;
    
    @JsonProperty("nickName")
    private String nickName;
    
    @JsonProperty("content")
    private String content;
    
    @JsonProperty("score")
    private Double score;
    
    public static NLZPage of(NLZPage<Comment> page){
        List<CommentVo> reslut = Lists.newArrayList();
        page.getContent().stream().forEach(
        		s -> {reslut.add(CommentVo.of(s));});
        return page.setCollection(reslut);
    }
    
//    public static List<CommentVo> of(Page<Comment> page){
//        List<CommentVo> reslut = Lists.newArrayList();
//        page.getContent().stream().forEach(
//        		s -> {reslut.add(CommentVo.of(s));});
//        return reslut;
//    }

    public static CommentVo of(Comment c){
        CommentVo vo = new CommentVo();
        vo.setId(c.getId());
        vo.setCreated(TimeUtils.getDateToString(c.getCreated()));
        vo.setNickName(c.getConsumer().getNickName());
        vo.setAvatar(c.getConsumer().getAvatar() == null ?"":SystemProperty.webUrl + c.getConsumer().getAvatar());
        vo.setContent(c.getContent() == null?"":c.getContent());
        vo.setScore(c.getScore());
        return vo;
    }
}
