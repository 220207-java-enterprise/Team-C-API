package com.revature.erm.util;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JwtConfig {

    private final String salt = "dont-hard-code-salt-values"; // use a Properties file instead
    private int expiration = 60 * 60 * 1000; // number of milliseconds in an hour
    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
    private final Key signingKey;

    public JwtConfig() {
        String salt = "don't-hard-code-salt-values"; //Added by Khari
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public int getExpiration() {
        // number of milliseconds in an hour //Added by Khari
        int expiration = 60 * 60 * 1000;  //Added by Khari
        return expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }

}
