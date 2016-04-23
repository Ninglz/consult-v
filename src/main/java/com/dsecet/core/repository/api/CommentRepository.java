package com.dsecet.core.repository.api;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.dsecet.core.domain.record.Comment;
import com.dsecet.core.repository.DomainRepository;
import com.dsecet.foundation.model.NLZPage;

/**
 * 评论Repository
 * @author heyue
 * */
@NoRepositoryBean
public interface CommentRepository extends DomainRepository<Comment>{

	NLZPage<Comment> findByCondition(Long expertsId, Pageable pageable);

}
