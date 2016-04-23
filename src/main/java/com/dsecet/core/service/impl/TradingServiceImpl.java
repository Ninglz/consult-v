package com.dsecet.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsecet.api.vo.WithdrawVo;
import com.dsecet.core.domain.record.Trading;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.repository.TradingRepository;
import com.dsecet.core.service.ExpertsService;
import com.dsecet.core.service.TradingService;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.ErrorCode;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.RechargeVo;


@Service
@Transactional
public class TradingServiceImpl implements TradingService {
	
	@Autowired
	private TradingRepository tradingRepository;
	
	@Autowired
	private ExpertsService expertsService;

	@Override
	public Trading recharge(Long id, Double money) {
		Trading trading = new Trading();
		Experts experts = expertsService.findOne(id);
		trading.setSink(money * 100);
		trading.setExperts(experts);
		trading.setType(1L);
		tradingRepository.save(trading);
		
		if(trading.getSink() == (money * 100)){
			trading.setState(0L);
			experts.getConsumerDetails().setSink(money * 100);
			//设置累积充值金额
			experts.getConsumerDetails().setRechargePrice(experts.getConsumerDetails().getRechargePrice() + money);
		}else{
			trading.setState(1L);
		}
		return tradingRepository.save(trading);
	}

	@Override
	public PageableResponse<RechargeVo> findPage(Long id, Pageable pageable) {
		Page<Trading> page = tradingRepository.findPage(id,null,null,pageable,1L,null);
		return PageableResponse.of(RechargeVo.of(page), page.getContent().size(), (int)page.getTotalElements());
	}

	@Override
	public Page<Trading> queryByCondition(String keyword, Long state, Pageable pageable) {
		return tradingRepository.findPage(null,keyword, state,pageable,1L,null);
	}

	@Override
	public Trading withdraw(Long id, Double sink) {
		Trading trading = new Trading();
		Experts experts = expertsService.findOne(id);
		if(sink > experts.getConsumerDetails().getSink()){
			throw new RespondableException(ErrorCode.WITHDRAW_SINK_NOT_ENOUGH);
		}
		//提现汇点
		trading.setSink(sink);
		//类型  2L:提现
		trading.setType(2L);
		//提现状态  0:申请中
		trading.setStatus(0L);
		//设置提现专家
		trading.setExperts(experts);
		//设置专家汇点
		trading.getExperts().getConsumerDetails().setSink(experts.getConsumerDetails().getSink() - sink);
		//设置累积充值金额
		experts.getConsumerDetails().setCashPrice(experts.getConsumerDetails().getCashPrice() + (sink/100));
		return tradingRepository.save(trading);
	}

	@Override
	public PageableResponse<WithdrawVo> findWithdrawPage(Long id, Pageable pageable) {
		Page<Trading> page = tradingRepository.findPage(id,null,null,pageable,2L,null);
		return PageableResponse.of(WithdrawVo.of(page), page.getContent().size(), (int)page.getTotalElements());
	}

	@Override
	public Page<Trading> queryWithdrawByCondition(String keyword, Long status, Pageable pageable) {
		return tradingRepository.findPage(null,keyword, null,pageable,2L,status);
	}

	@Override
	public Trading saveStatus(Long id,Long status) {
		Trading trading = tradingRepository.findOne(id);
		trading.setStatus(status);
		if(status == 2L){
			trading.getExperts().getConsumerDetails().setSink(trading.getExperts().getConsumerDetails().getSink() - trading.getSink());
		}
		if(status == 3L){
			trading.getExperts().getConsumerDetails().setSink(trading.getExperts().getConsumerDetails().getSink() + trading.getSink());
		}
		return tradingRepository.save(trading);
	}

	@Override
	public Trading findOne(Long id) {
		return tradingRepository.findOne(id);
	}
}
