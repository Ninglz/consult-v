package com.dsecet.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author: Fablenas
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends ImgDomain<Long>{

    public final static String STRING_ENCRYPTION_KEY = "},i7JF7>7m";

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @Column(name = "no_login", nullable = false)
    protected boolean noLogin;

    @JsonIgnore
    @Column(nullable = false)
    private boolean locked;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @Column(nullable = true, name = "last_signed")
    private Long lastSigned;

    @JsonIgnore
    @NotNull
    @Getter(AccessLevel.PROTECTED)
    @Embedded
    private UserAccount account = new UserAccount();


    public Customer signup(@NotBlank String username, @NotBlank String password){
        return username(username).password(password);
    }

    public Customer username(@NotBlank String username){
        getAccount().setUsername(username);
        return this;
    }

    public Customer passwordToken(@NotBlank String token){
        getAccount().getPassword().setToken(token);
        return this;
    }

    public Customer password(@NotBlank String password){
        getAccount().password(password);
        return this;
    }

    public boolean resetPassword(@NotBlank String password, @NotBlank String confirmedPassword){
        if(!password.equals(confirmedPassword) || !isActive() || isLocked()) return false;
        password(password);
        return true;
    }

    @ApiModelProperty(value = "Username")
    @JsonProperty(value = "u")
    public String getUsername(){
        return getAccount().getUsername();
    }
    
    public void setUsername(String userName){
    	getAccount().setUsername(userName);
    }
    
    

    @JsonIgnore
    public String getSalt(){
        return getAccount().getPassword().getSalt();
    }

    @JsonIgnore
    public String getPassword(){
        return getAccount().getPassword().getHash();
    }

    @JsonIgnore
    public String getPasswordToken(){
        return getAccount().getPassword().getToken();
    }

    public void setLastSigned(@NotNull Long signed){
        this.lastSigned = signed;
    }


}
