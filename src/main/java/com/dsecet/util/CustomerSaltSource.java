package com.dsecet.util;

import com.dsecet.core.domain.user.Consumer;
import com.dsecet.core.service.ConsumerService;
import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.Customer;
import com.dsecet.foundation.model.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("websiteSaltSource")
public class CustomerSaltSource implements SaltSource{

    @Autowired
    private ConsumerService consumerService;

    @Override
    public Object getSalt(UserDetails user){
        Consumer consumer = consumerService.identify(user.getUsername());
        if(Objects.isNull(consumer)){
            throw new RespondableException(ErrorCode.USER_NO_SUCH);
        }
        return consumer.getSalt();
    }

}
