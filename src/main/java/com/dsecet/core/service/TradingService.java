package com.dsecet.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dsecet.api.vo.WithdrawVo;
import com.dsecet.core.domain.record.Trading;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.RechargeVo;

public interface TradingService {

	/**
	 * 充值
	 * */
	Trading recharge(Long id, Double money);

	PageableResponse<RechargeVo> findPage(Long id, Pageable pageable);

	Page<Trading> queryByCondition(String keyword, Long state, Pageable pageable);

	/**
	 * 提现
	 * */
	Trading withdraw(Long id, Double sink);

	PageableResponse<WithdrawVo> findWithdrawPage(Long id, Pageable pageable);

	Page<Trading> queryWithdrawByCondition(String keyword, Long status, Pageable pageable);
	
	Trading saveStatus(Long id,Long status);
	
	Trading findOne(Long id);

}
