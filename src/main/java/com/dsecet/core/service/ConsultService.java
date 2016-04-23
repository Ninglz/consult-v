package com.dsecet.core.service;

import org.springframework.data.domain.Pageable;

import com.dsecet.admin.vo.ConsultListVo;
import com.dsecet.api.vo.ConsultVo;
import com.dsecet.foundation.model.PageableResponse;

/**
 * 咨询记录Service
 * @author heyue
 * */
public interface ConsultService {

	PageableResponse<ConsultVo> findCondition(String name,Long id, Pageable pageable);

	PageableResponse<ConsultListVo> queryByCondition(String keyword, Pageable pageable);

}
