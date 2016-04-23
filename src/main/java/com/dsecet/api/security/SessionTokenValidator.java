package com.dsecet.api.security;

import com.dsecet.foundation.model.Token;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author Fablenas
 */
@Component("sessionTokenValidator")
public class SessionTokenValidator{

    public boolean validate(@NotNull Token<Long> token,
                            @NotNull Token<Long> cachedToken){
        return token.equals(cachedToken);
    }
}