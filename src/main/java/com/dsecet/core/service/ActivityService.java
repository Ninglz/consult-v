package com.dsecet.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.domain.system.Activity;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.ActivityVo;

/**
 *   活动接口
 * @author heyue
 * */
public interface ActivityService {

	Page<Activity> findPage(String keyword, Pageable pageable);

	Activity findOne(Long id);

	Activity save(Activity activity);

	Activity create(String name, MultipartFile img, String describe, String url);

	PageableResponse<ActivityVo> findCondition(String name, Pageable pageable);

}
