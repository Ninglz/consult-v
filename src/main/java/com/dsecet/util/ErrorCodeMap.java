package com.dsecet.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.dsecet.foundation.model.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created on 2015年11月5日
 * <p>Title:       errorMap</p>
 * <p>Description: 存储错误编码对应中文描述</p>
 * <p>Copyright:   Copyright (c) 2015</p>
 * <p>Company:     联创梦享集团</p>
 * <p>Department:  成都研发中心</p>
 * @author         宁良竹 lz.ning@defshare.org
 * @version        1.0
 */
public class ErrorCodeMap implements Serializable{
	
	 public   HashMap<Integer, String> errorCodeMap = new HashMap<Integer, String>() {
        {
        	put(ErrorCode.ENTITY_NO_SUCH, "未找到对应的数据");
            put(ErrorCode.BAD_CREDENTIAL, "用户名或密码错误");
            put(ErrorCode.PASSWORD_DIFFERENCE, "密码输入不一致");
            put(ErrorCode.ILLEGAl_PARAMETER, "非法参数");
            put(ErrorCode.TICKET_REQUIRED, "必要参数");
            put(ErrorCode.TICKET_INVALID, "参数无效");
            put(ErrorCode.CELL_NO_INVALID, "手机号码无效");
            put(ErrorCode.VERIFICATION_CODE_INVALID, "验证码无效");
            put(ErrorCode.CODE_EXPIRE, "验证码过期");
            put(ErrorCode.PASSWORD_INVALID, "密码无效");
            put(ErrorCode.TOKEN_REQUIRED, "token必须");
            put(ErrorCode.TOKEN_INVALID, "token无效");
            put(ErrorCode.TOKEN_EXPIRED, "token过期");
            put(ErrorCode.METHOD_ARGUMENT_NOT_VALID, "方法不存在");
            put(ErrorCode.UNDEFINDED, "未知错误");
            put(ErrorCode.CODE_INVALID, "验证码已过期");
            put(ErrorCode.EXPERTS_NAME_EMPTY, "专家姓名为空");
            put(ErrorCode.IDCARD_EMPTY, "身份证号码为空");
            put(ErrorCode.SENIORITY_EMPTY, "资历为空");
            put(ErrorCode.INDUSTRY_EMPTY, "行业为空");
            put(ErrorCode.DESCRIPTION_EMPTY, "说明为空");
            put(ErrorCode.GOODAT_EMPTY, "擅长为空");
            put(ErrorCode.CUSTOMER_HAS_LOCKED, "用户已被冻结");
            put(ErrorCode.NAME_EMPTY, "用户名为空");
            put(ErrorCode.SEX_EMPTY, "性别为空");
            put(ErrorCode.CITY_EMPTY, "城市为空");
            put(ErrorCode.IMG_EMPTY, "图像为空");
            put(ErrorCode.NOT_FOCUS_YOURSERLF, "不能关注自己");
            put(ErrorCode.NOT_EXPERTS, "不是咨询师");
            put(ErrorCode.EXPERTS_REGISTERED, "专家已注册,待审核");
            put(ErrorCode.DESCRIPTION_OR_CERTIFICATEIMG_EMPTY, "说明,资历证书必填一个");
            put(ErrorCode.REGISTER_CELLNO_DUPLICATE, "注册手机号重复");
            put(ErrorCode.EXPERTS_UPDATED, "专家已提交修改申请,还未审核");
            put(ErrorCode.EXPERTS_NOT_AUDIT, "专家资料未审核通过,请重新申请"); 
            put(ErrorCode.COMMENT_SCORE_EMPTY, "评论分数为空"); 
            put(ErrorCode.NO_COMMENT_YOURSELF, "不能自我评价 "); 
            put(ErrorCode.WITHDRAW_SINK_NOT_ENOUGH, "兑换汇点不能大于账户剩余汇点"); 
            put(ErrorCode.ALIAPYUSERNAME_EMPTY, "请填写支付宝账号"); 
        }
    };
    
    public String toErrorString(int code){
    	return errorCodeMap.get(code);
    }
    
    public final class CodeErrorEntity{


        @JsonProperty("msg")
        @NotNull
        private String msg;

        @JsonCreator
        public CodeErrorEntity(@JsonProperty("code")  int code){
            this.msg = errorCodeMap.get(code);
        }
    }
}
