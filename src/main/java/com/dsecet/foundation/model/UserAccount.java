package com.dsecet.foundation.model;

import com.dsecet.util.EncryptUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author: Fablenas
 */
@SuppressWarnings("serial")
@Embeddable
@Data
public class UserAccount implements Serializable{

    @ApiModelProperty(value = "username")
    @JsonProperty(value = "u")
    @NotBlank
    @Size(max = 32, min = 6)
    @Pattern(regexp = "[\\d\\w@]*")
    @Column(nullable = false, unique = true, length = 32)
    private String username;

    @JsonIgnore
    @NotNull
    @Embedded
    private Password password = new Password();

    @JsonIgnore
    public void password(String password){
        String salt = EncryptUtils.getSha512Salt();
        this.password.setHash(EncryptUtils.password(password, salt));
        this.password.setSalt(salt);
    }
}
