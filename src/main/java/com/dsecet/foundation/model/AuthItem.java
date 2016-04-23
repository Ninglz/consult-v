package com.dsecet.foundation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: Fablenas
 */

@Data
@Embeddable
public class AuthItem implements Serializable{


    @JsonProperty(value = "data")
    private String data;


    @JsonProperty(value = "is_auth")
    private boolean authenticated;

    @JsonIgnore
    private String token;
}
