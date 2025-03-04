package com.SCMXPert.sbmongodb.customImpl;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.InvalidKeyException;

@Service
public class JWTMethods {
	public String hmacSha256(String data, String secret) throws java.security.InvalidKeyException {
	    try {
	        byte[] hash = secret.getBytes(StandardCharsets.UTF_8);
	        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
	        sha256Hmac.init(secretKey);
	        byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
	        return   Base64.getUrlEncoder().withoutPadding().encodeToString(signedBytes);
	    } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
	       // Logger.getLogger(JWebToken.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
	        return null;
	    }
	}
}

