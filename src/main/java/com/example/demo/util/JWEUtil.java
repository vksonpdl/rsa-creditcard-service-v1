package com.example.demo.util;

import java.security.interfaces.RSAPrivateKey;

import org.springframework.stereotype.Component;

import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jwt.EncryptedJWT;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWEUtil {

	public String doJWTDecryption(RSAPrivateKey rsaKey, String jwtString) {

		try {
			RSADecrypter decrypter = new RSADecrypter(rsaKey);
			EncryptedJWT jwt = EncryptedJWT.parse(jwtString);			
			jwt.decrypt(decrypter);
			return jwt.getPayload().toJSONObject().get("body").toString();
		} catch (Exception e) {
			log.error("Exception : [{}]", e.getMessage());
		}

		return null;
	}

}
