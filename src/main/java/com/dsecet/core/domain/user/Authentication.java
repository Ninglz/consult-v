package com.dsecet.core.domain.user;

import com.dsecet.foundation.model.AuthItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

/**
 * @author: lxl
 */
@SuppressWarnings("serial")
@Data
@Embeddable
public class Authentication implements Serializable{


    @JsonProperty(value = "email")
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "data", column = @Column(name = "email", unique = true)),
            @AttributeOverride(name = "authenticated", column = @Column(name = "email_auth")),
            @AttributeOverride(name = "token", column = @Column(name = "email_token"))})
    private AuthItem email = new AuthItem();

    @JsonProperty(value = "cell")
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "data", column = @Column(name = "cell_no", nullable = false, unique = true, length = 11)),
            @AttributeOverride(name = "authenticated", column = @Column(name = "cell_no_auth")),
            @AttributeOverride(name = "token", column = @Column(name = "cell_no_token"))})
    private AuthItem cellNo  = new AuthItem();
    
    @JsonProperty(value = "nick_name")
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "data", column = @Column(name = "nick_name", nullable = true, length = 32)),
            @AttributeOverride(name = "authenticated", column = @Column(name = "nick_name_auth")),
            @AttributeOverride(name = "token", column = @Column(name = "nick_name_token"))})
    private AuthItem nickName  = new AuthItem();

    @JsonProperty(value = "real_name")
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "data", column = @Column(name = "real_name", nullable = true, length = 32)),
            @AttributeOverride(name = "authenticated", column = @Column(name = "real_name_auth")),
            @AttributeOverride(name = "token", column = @Column(name = "real_name_token"))})
    private AuthItem realName  = new AuthItem();


    @JsonProperty(value = "id_card")
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "data", column = @Column(name = "id_card", length = 18)),
            @AttributeOverride(name = "authenticated", column = @Column(name = "id_card_auth")),
            @AttributeOverride(name = "token", column = @Column(name = "id_card_token"))})
    private AuthItem idCard  = new AuthItem();

}
