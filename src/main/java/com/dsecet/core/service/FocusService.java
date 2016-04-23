package com.dsecet.core.service;

import org.springframework.data.domain.Pageable;

import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.ExpertsListVo;

/**
 * 关注接口
 * @author heyue
 * */
public interface FocusService {

	void save(Long conusmerId, Long expertsId,Boolean active);

	PageableResponse<ExpertsListVo> findByConditions(String name, String industryId, String evaluation, String price,Long id,Pageable pageable);

}
