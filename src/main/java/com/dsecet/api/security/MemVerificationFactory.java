package com.dsecet.api.security;

import com.dsecet.foundation.model.VerificationFactory;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author: Fablenas
 */
@Component("memVerificationFactory")
public class MemVerificationFactory implements VerificationFactory<String, String, String>{

    private static final String TICKET_FORMAT = "%s:%s:%s";

    @Override
    public String code(@NotBlank String key){
        return RandomStringUtils.random(6, 48, 58, false, true);
    }

    @Override
    public String ticket(@NotBlank String key, @NotBlank String code){
        return String.format(TICKET_FORMAT, key, code, UUID.randomUUID().toString());
    }

    @Override
    public String guessKey(@NotBlank String ticket){
        return StringUtils.split(ticket, ":")[0];
    }
}
