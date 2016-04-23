package com.dsecet.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsecet.admin.vo.SendIntegralVo;
import com.dsecet.admin.vo.WithdrawListVo;
import com.dsecet.core.domain.record.SendIntegral;
import com.dsecet.core.domain.record.Trading;
import com.dsecet.core.service.TradingService;
import com.dsecet.core.service.admin.SendIntegralService;
import com.dsecet.foundation.model.PageableResponse;
import com.dsecet.foundation.model.query.RechargeVo;

/**
 * 财务管理Controller
 * @author heyue
 * */
@Controller
@RequestMapping("/trading")
public class AdminTradingController {
	
	@Autowired
	private TradingService tradingService;

	@Autowired
	private SendIntegralService sendIntegralService;
	
	
	@RequestMapping(value = "/recharge", method = RequestMethod.GET)
	public String index(){
	    return "trading/recharge_list";
	}

	/**
	 *  充值列表
	 *   @author heyue
	 * */
	@RequestMapping(value = "/recharge/list", method = RequestMethod.GET)
	public
	@ResponseBody
	PageableResponse list(String keyword, Long state,Pageable pageable){
		Page<Trading> rechargePage = tradingService.queryByCondition(keyword,state,pageable);
	    return PageableResponse.of(RechargeVo.of(rechargePage), rechargePage.getContent().size(), (int) rechargePage.getTotalElements());
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public String withdrawIndex(){
		return "trading/withdraw_list";
	}
	
	/**
	 *  提现列表
	 *   @author heyue
	 * */
	@RequestMapping(value = "/withdraw/list", method = RequestMethod.GET)
	public
	@ResponseBody
	PageableResponse withdrawist(String keyword, Long status,Pageable pageable){
		Page<Trading> rechargePage = tradingService.queryWithdrawByCondition(keyword,status,pageable);
		return PageableResponse.of(WithdrawListVo.of(rechargePage), rechargePage.getContent().size(), (int) rechargePage.getTotalElements());
	}
	
	@RequestMapping(value = "/deal")
    public
    @ResponseBody
    ModelAndView deal(Long id){
		tradingService.saveStatus(id, 1L);
    	return new ModelAndView("redirect:/admin/trading/withdraw");
    }
	
	@RequestMapping(value = "/payfor")
	public
	@ResponseBody
	ModelAndView payfor(Long id){
		tradingService.saveStatus(id, 2L);
		return new ModelAndView("redirect:/admin/trading/withdraw");
	}
	
	@RequestMapping(value = "/refuse")
	public
	@ResponseBody
	ModelAndView refuse(Long id){
		tradingService.saveStatus(id, 3L);
		return new ModelAndView("redirect:/admin/trading/withdraw");
	}
	
	@RequestMapping(value = "/sendIntegral", method = RequestMethod.GET)
    public String sendIntegralIndex(){
        return "trading/sendIntegral_list";
    }
	
    @RequestMapping(value = "/sendIntegral/list", method = RequestMethod.GET)
    public
    @ResponseBody
    PageableResponse list(String keyword, Pageable pageable){
    	Page<SendIntegral> page = sendIntegralService.findPage(keyword, pageable);
        return PageableResponse.of(SendIntegralVo.of(page), page.getContent().size(), (int) page.getTotalElements());
    }
}
