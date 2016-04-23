package com.dsecet.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.api.vo.ExpertsShowVo;
import com.dsecet.core.domain.user.Consumer.AppType;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.ExpertsListVo;

/**
 *   专家接口
 * @author heyue
 * */
public interface ExpertsService {

	/**
	 * 完善专家注册资料
	 * @author heyue
	 * */
	Experts saveInformation(String name,Long sex,String idCard,MultipartFile imgs,Date seniorityStart,Date seniorityEnd, Long industryId, Long cityId,String goodAt, String description, String alipayUserName,
			MultipartFile[] img,Long id);

	/**
	 * 专家分页
	 * */
	PageableResponse<ExpertsListVo> findByConditions(String name, String industryId, String evaluation, String price,Long id,Pageable pageable);

	/**
	 * 推荐专家
	 * */
	PageableResponse<ExpertsListVo> findRecommendExperts(Long id,String evaluation,Pageable pageable);

	Experts findOne(Long id);

	/**
	 * 后台专家分页
	 * */
	Page<Experts> queryByCondition(String keyword, String active, Long expertState, Pageable pageable);

	Experts save(Experts experts);

	/**
	 * 专家修改资料
	 * */
	Experts updateInformation(String name, Long sex, String idCard, MultipartFile imgs, Date seniorityStart,Date seniorityEnd,
			Long industryId, Long cityId, String goodAt, String description, String alipayUserName, MultipartFile[] img,
			Long id);

	void delete(Long recordId);
	
	Boolean updateBatch(Long time);

	/**
	 * IOS/Android 用户
	 * */
	List<String> findAndroidOrIosByCondition(AppType appType, String keyword, String active, Long expertState);

	/**
	 * 专家审核
	 * */
	Experts saveAuth(Long id,boolean flag,String reason);

	/**
	 * 专家个人中心显示(AuditState = true)
	 * */
	Experts fingByAuditState(Long id);

	/**
	 * 专家绑定支付宝
	 * */
	Experts saveAlipay(Long id, String alipayUserName);

	/**
	 * 推荐专家
	 * */
	Page findRecommendExpertsCondition(Long id,String evaluation,Pageable pageable);

	/**
	 * 专家详情
	 * */
	ExpertsShowVo findShow(Long id,Long expertsId);

	/**
	 * 专家调整积分
	 * */
	Experts updateIntegral(Long id, Double integral);
}
