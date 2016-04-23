package com.dsecet.core.service.impl;



import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.domain.record.Levels;
import com.dsecet.core.domain.record.SendIntegral;
import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Consumer.AppType;
import com.dsecet.core.domain.user.ConsumerDetails;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.core.repository.ConsumerListRepository;
import com.dsecet.core.repository.ConsumerListRepositoryImpl;
import com.dsecet.core.repository.ConsumerRepository;
import com.dsecet.core.repository.admin.SendIntegralRepository;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.core.service.SystemProperty;
import com.dsecet.core.service.admin.LevelsService;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.ErrorCode;
import com.dsecet.foundation.model.query.ConsumerVo;
import com.dsecet.helper.ImageHelper;
import com.dsecet.util.ModelUtils;
import com.dsecet.util.TimeUtils;
import com.dsecet.util.restDemo.client.JsonReqClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lxl
 */
@Slf4j
@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private ConsumerListRepository consumerListRepository;

	@Autowired
    private SendIntegralRepository sendIntegralRepository;
	    
	@Autowired
	private LevelsService levelsService;
	
    @Value("${ucpaas.account.sid}")
    private String accountSid;
	
    @Value("${ucpaas.auth.token}")
    private String authToken;

	@Override
	@Transactional(readOnly = true)
	public Consumer identify(@NotNull String userName) {
		return consumerRepository.findByAuthenticationCellNoData(userName);
	}

	@SuppressWarnings("null")
	@Override
	public Consumer register(@NotNull String userName, @NotNull String password, @NotNull AppType type,  String appToken) {

		Experts experts = new Experts();
		experts.register(userName, password);
		experts.getAuthentication().getCellNo().setData(userName);
		experts.getAuthentication().getCellNo().setAuthenticated(true);
		experts.setNickName("用户" + TimeUtils.currentMillis());
		ConsumerDetails consumerDetails = new ConsumerDetails();
		consumerDetails.setIntegral(SystemProperty.integral);
		experts.setConsumerDetails(consumerDetails);	
		
		Levels levels = levelsService.findOne(Long.valueOf(1));
    	experts.getConsumerDetails().setConsuemrLevels(levels);
    	
    	if(!StringUtils.isBlank(appToken)){
        String APP_ID="afba6099a63b4512935ae205cf7a38a7";
        JsonReqClient client = new JsonReqClient();
        String result = client.createClient(accountSid,authToken,APP_ID , null, "0", null, userName);
        System.out.println(result);
        Gson gson = new Gson();
        Map<String,Map<String,Object>> map =gson.fromJson(result, new TypeToken<Map<String, Object>>(){}.getType());
        String respCode = map.get("resp").get("respCode").toString();
        if(respCode.equals("000000")){
        	Map clientMap  = (Map) map.get("resp").get("client");
        	experts.setClientNum(clientMap.get("clientNumber").toString());
        	experts.setClientPwd(clientMap.get("clientPwd").toString());
        }else{
        	throw new RespondableException(ErrorCode.UNDEFINDED, "注册失败");
        }
        experts.setAppToken(appToken);
    	}
        
		try {
			experts.setAppType(type);
			consumerRepository.save(experts);
	    	//注册送积分活动事件
	    	SendIntegral sendIntegral = new SendIntegral();
			sendIntegral.setName("初版上线注册送积分");
			sendIntegral.setConsumer(experts);
			sendIntegral.setIntegral(SystemProperty.integral);
			sendIntegralRepository.save(sendIntegral);
			
			return experts;
		} catch (Exception e) {
			throw new RespondableException(ErrorCode.UNDEFINDED, e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Consumer get(@NotNull Long id) {
		return consumerRepository.getOne(id);
	}

	@Override
	public Consumer update(@NotNull @Valid Consumer consumer) {
		if (null == consumer.getId() || consumer.getId() == 0L) {
			return null;
		}
		consumer.updateLastModified();
		return consumerRepository.save(consumer);
	}

	@Override
	@Transactional(readOnly = true)
	public Consumer getByIdCard(@NotBlank String idCard) {
		return consumerRepository.findByAuthentication_IdCard_Data(idCard);
	}

	@Override
	public Consumer signin(@NotBlank String userName, @NotBlank String password) {
		Consumer consumer = consumerRepository.findByAuthenticationCellNoData(userName);
		if (!(Objects.nonNull(consumer) && consumer.verifyPassword(password))) {
			throw new RespondableException(ErrorCode.BAD_CREDENTIAL);
		}
		if (consumer.isLocked() || !consumer.isActive()) {
			throw new RespondableException(ErrorCode.BAD_CREDENTIAL);
		}
		consumer.setLastSigned(TimeUtils.currentMillis());
		return consumer;
	}

	@Override
	public boolean resetPassword(@NotBlank Long id, @NotBlank String password, @NotBlank String confirmPassword) {
		Consumer consumer = consumerRepository.findOne(id);
		return consumer.resetPassword(password, confirmPassword);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean userNameExists(@NotBlank String cell) {
		return consumerRepository.countByCellNo(cell) != 0;
	}

	@Override
	public Consumer authIdenity(Long consumerId, String realName, String idCard) throws RespondableException {
		Consumer consumer = consumerRepository.findOne(consumerId);
		ModelUtils.checkNull(consumer);
		consumer.setIdCard(idCard);
		consumer.setRealName(realName);
		consumer.getAuthentication().getRealName().setAuthenticated(true);
		consumer.getAuthentication().getIdCard().setAuthenticated(true);
		return consumer;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Consumer> findByIds(List<Long> ids) {
		return consumerRepository.findByIds(ids);
	}

	@Override
	public Page<Consumer> queryByConditions(ConsumerVo vo, Pageable pageable, Boolean lastSignedSorted) {
		ConsumerListRepositoryImpl.ConsumerQueryBuilder builder = ConsumerListRepositoryImpl.ConsumerQueryBuilder
				.newBuilder();
//		builder.usernameCondiftion(vo.getUsername()).cellNoCondiftion(vo.getCellNo())
//				.realNameCondiftion(vo.getRealName()).lockedCondiftion(vo.getLocked());
		if (lastSignedSorted)
			builder.sortedByLastSignedSorted();
		return consumerListRepository.findConsumerList(builder, pageable);
	}

	@Override
	public boolean findPassword(long id, String password) {
		Consumer consumer = consumerRepository.findOne(id);
		ModelUtils.checkNull(consumer);
		return consumer.resetPassword(password, password);
	}

	@Override
	public List<Consumer> exportConsumers(Long from, Long to) {
		return consumerListRepository.exportConsumers(from, to);
	}


	/**
	 * <p>
	 * Description:通过id获取用户
	 * </p>
	 * 
	 * @author:宁良竹
	 * @update: 2015年11月7日
	 * @param id
	 * @return
	 */
	public Consumer findOne(Long id) {
		return consumerRepository.findOne(id);
	}

	@Override
	public Page<Consumer> queryByCondition(String keyword, Pageable pageable) {
		return consumerListRepository.queryByCondition(keyword, pageable);
	}

	@Override
	public Experts saveInformation(MultipartFile avatar, Experts consumer) {
		if(avatar != null){
		consumer.setImgUrl(
				SystemProperty.informationPrefix+ File.separator + consumer.getId() + File.separator + TimeUtils.currentMillis()
				);
		ImageHelper.deleteAllByFileAndWriteByteToFile(consumer.getImgUrl(), avatar);	
		consumer.setAvatar(consumer.getImgUrl());
		}
		
		return consumerRepository.save(consumer);
	}

	@Override
	public Consumer save(Consumer consumer) {
		return consumerRepository.save(consumer);
	}

}
