package com.dsecet.api.security;

import com.dsecet.foundation.model.VerificationCache;
import com.dsecet.foundation.model.VerificationFactory;
import com.google.common.collect.Maps;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: Fablenas
 */
@Component("memVerificationCache")
public class MemVerificationCache implements VerificationCache<String, String, String>{

    private Map<String, String> codes = Maps.newConcurrentMap();

    private Map<String, String> tickets = Maps.newConcurrentMap();

    @Autowired
    private VerificationFactory<String, String, String> verificationFactory;

    @Override
    public String produceCode(@NotBlank String key){
        String code = verificationFactory.code(key);
        codes.put(key, code);
        return code;
    }

    @Override
    public boolean consumeCode(@NotBlank String key, @NotBlank String code){
        return codes.remove(key, code);
    }

    @Override
    public String produceTicket(@NotBlank String key, @NotBlank String code){
        String ticket = verificationFactory.ticket(key, code);
        tickets.put(key, ticket);
        return ticket;
    }

    @Override
    public boolean consumeTicket(@NotBlank String key, @NotBlank String ticket){
        return tickets.remove(key, ticket);
    }

    @Override
    public String guessKey(@NotBlank String ticket){
        return verificationFactory.guessKey(ticket);
    }
}
