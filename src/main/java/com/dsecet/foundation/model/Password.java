package com.dsecet.foundation.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@SuppressWarnings("serial")
@Embeddable
@Data
public class Password implements Serializable{

    @Column(length = 64)
    private String salt;

    @NotBlank
    @Column(nullable = false, length = 128)
    private String hash;

    @Column
    private String token;
}
