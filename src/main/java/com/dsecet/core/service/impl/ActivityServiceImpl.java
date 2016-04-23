package com.dsecet.core.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.domain.system.Activity;
import com.dsecet.core.repository.ActivityRepository;
import com.dsecet.core.service.ActivityService;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.foundation.model.NLZPage;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.ActivityVo;
import com.dsecet.helper.ImageHelper;

/**
 * 活动接口实现类
 * 
 * @author: heyue
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public Page<Activity> findPage(String keyword, Pageable pageable) {
		return activityRepository.findpage(keyword, pageable);
	}

	@Override
	public Activity findOne(Long id) {
		return activityRepository.findOne(id);
	}

	@Override
	public Activity save(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public Activity create(String name, MultipartFile img, String describe, String url) {
		Activity activity = new Activity();
		activity.setImgUrl(
				SystemProperty.activityUrl + File.separator + activity.getSerial() + File.separator+ activity.getSerial());
		ImageHelper.writeByteToFile(activity.getImgUrl(), img);
		activity.setName(name);
		activity.setImg(activity.getImgUrl());
		activity.setDescription(describe);
		activity.setUrl(url);
		return activityRepository.save(activity);
	}

	@Override
	public PageableResponse<ActivityVo> findCondition(String name, Pageable pageable) {
		NLZPage<Activity> page = activityRepository.findpage(name, pageable);
		return PageableResponse.of(page.setCollection(ActivityVo.of(page)));
	}
}
