package com.dsecet.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Sha2Crypt;

import java.util.UUID;

/**
 * @author: Fablenas
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EncryptUtils{

    public static final String password(String raw, String salt){
        return Sha2Crypt.sha512Crypt(raw.getBytes(Charsets.UTF_8), salt);
    }

    public static final String getSha512Salt(){
        // TODO Commons.Codec requires sha512 salt started with "$6$", and this
        // should be fixed in future.
        return "$6$" + UUID.randomUUID().toString();
    }

    public static final String md5(String raw){
        return DigestUtils.md5Hex(raw);
    }


}
