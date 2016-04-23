package com.dsecet.core.service;

import org.springframework.data.domain.Pageable;

import com.dsecet.api.controller.CommentController.CommentRequest;
import com.dsecet.core.domain.record.Comment;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.CommentVo;

/**
 *   评论接口
 * @author heyue
 * */
public interface CommentService {

	Comment saveComment(Experts consume,CommentRequest commentRequest);

	PageableResponse<CommentVo> findByCondition(Long expertsId, Pageable pageable);

}
