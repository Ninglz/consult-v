package com.dsecet.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.domain.user.Consumer.AppType;
import com.dsecet.core.domain.user.Experts;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.query.ConsumerVo;

/**
 * @author: lxl
 */
public interface ConsumerService{

    Consumer identify(String cellNo);

    Consumer register(String cellNo,String password,AppType type,String appToken);

    Consumer get(Long id);

    Consumer update(Consumer consumer);

    Consumer getByIdCard(String idCard);

    Consumer signin(String cellNo, String password);

    boolean resetPassword(Long id, String password, String confirmPassword);

    boolean userNameExists(String userName);

    Consumer authIdenity(Long consumerId, String realName, String idCard) throws RespondableException;

    List<Consumer> findByIds(List<Long> ids);

    Page<Consumer> queryByConditions(ConsumerVo vo, Pageable pageable, Boolean lastSignedSorted);

    boolean findPassword(long id, String password);

    List<Consumer> exportConsumers(Long from, Long to);

    Consumer findOne(Long id);

	Page<Consumer> queryByCondition(String keyword, Pageable pageable);

	/**
	 * 完善客户资料
	 * */
	Consumer saveInformation(MultipartFile img,Experts consumer);

	 Consumer save(Consumer consumer);
}
