package com.dsecet.api.security;

import com.dsecet.foundation.exception.RespondableException;
import com.dsecet.foundation.model.Token;
import com.dsecet.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.dsecet.foundation.model.ErrorCode.TOKEN_INVALID;
import static java.lang.String.format;
import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNumeric;
import static org.apache.commons.lang.StringUtils.split;

/**
 * @author Fablenas
 */
@Getter
@Setter
@EqualsAndHashCode
public class SessionToken implements Token<Long>, Serializable{

    private static final String SEPARATOR_CHARS = "-";

    private static final String PATTERN = format("%s%s%s%s%s", "%s",
            SEPARATOR_CHARS, "%s", SEPARATOR_CHARS, "%s");

    @JsonIgnore
    @Id
    private long userId;

    @JsonIgnore
    private long refreshed;

    @JsonIgnore
    private String hex;
    
    @JsonProperty("num")
    private String clientNum;
    
    @JsonProperty("pwd")
    private String clientPwd;

    @Override
    @JsonIgnore
    public Long getKey(){
        return userId;
    }

    @Override
    public boolean isExpired(@NotNull Long expiredTime){
        return TimeUtils.plusMinutes(this.refreshed, expiredTime) < TimeUtils.currentMillis();
    }

    public SessionToken(long userId){
        super();
        this.userId = userId;
        this.refreshed = TimeUtils.currentMillis();
        hex = sha1Hex(String.valueOf(refreshed));
    }

    @PersistenceConstructor
    public SessionToken(long userId, long refreshed, @NotBlank String hex){
        super();
        this.userId = userId;
        this.refreshed = refreshed;
        this.hex = hex;
    }

    @JsonCreator
    public static SessionToken valueOf(
            @NotBlank @JsonProperty("token") String serializedToken){
        String parts[] = split(serializedToken, SEPARATOR_CHARS);
        if(null == parts || 3 != parts.length || isBlank(parts[0])
                || !isNumeric(parts[0]) || isBlank(parts[1])
                || !isNumeric(parts[1]) || isBlank(parts[2]))
            throw new RespondableException(TOKEN_INVALID);
        return new SessionToken(Long.valueOf(parts[0]), Long.valueOf(parts[1]), parts[2]);
    }

    @Override
    @ApiModelProperty(value = "Token", required = true)
    @JsonProperty("t")
    public String toString(){
        return format(PATTERN, userId, refreshed, hex);
    }

}
