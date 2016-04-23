package com.dsecet.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.api.controller.CommentController.CommentRequest;
import com.dsecet.core.domain.record.Comment;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.repository.api.CommentRepository;
import com.dsecet.core.service.CommentService;
import com.dsecet.core.service.ExpertsService;
import com.dsecet.foundation.model.NLZPage;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.CommentVo;

/**
 * 评论接口实现类
 * @author: heyue
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	@Autowired
	private ExpertsService expertsService;

	@Override
	public Comment saveComment(Experts consumer,CommentRequest commentRequest) {
		Comment comment = new Comment();
		Experts experts = expertsService.findOne(commentRequest.getCommentedId());
		comment.setConsumer(consumer);
		comment.setExperts(experts);
		comment.setScore(commentRequest.getScore());
		comment.setContent(commentRequest.getContent()== null?"":commentRequest.getContent());
		comment.setType(commentRequest.getType());
		if(commentRequest.getType() == 1L){ //用户评价
			consumer.getConsumerDetails().setExpertsNum(consumer.getConsumerDetails().getExpertsNum() + 1);
			consumer.getConsumerDetails().setExpertsTotal(consumer.getConsumerDetails().getExpertsTotal() + commentRequest.getScore());
		}else{  //type==2L   专家评价
			consumer.getConsumerDetails().setConsumerNum(consumer.getConsumerDetails().getConsumerNum()+1);
			consumer.getConsumerDetails().setConsumerTotal(consumer.getConsumerDetails().getConsumerTotal() + commentRequest.getScore());
		}
		return commentRepository.save(comment);
	}

	@Override
	@SuppressWarnings("unchecked")
	public PageableResponse<CommentVo> findByCondition(Long expertsId,
			Pageable pageable) {
		NLZPage<Comment> commentPage = CommentVo.of(
				commentRepository.findByCondition(expertsId,pageable)
				);
		return PageableResponse.of(commentPage);
	}
}
