package com.example.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.constants.AppConstants;

@Component
public class DecryptionUtil {

	@Value("${key.private}")
	String privateKeyString;

	public String decrypt(String encryptedString) throws IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {

		if (StringUtils.hasText(encryptedString)) {

			encryptedString = encryptedString.replaceAll(AppConstants.CHAR_ASTERISK, AppConstants.CHAR_FORWARDSLASH);

			byte[] privateKeyBytes = privateKeyString.getBytes(StandardCharsets.UTF_8);
			privateKeyBytes = Base64.getDecoder().decode(privateKeyBytes);

			KeyFactory keyFactory2 = KeyFactory.getInstance(AppConstants.ENCRYPTION_MODE_RSA);
			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

			RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory2.generatePrivate(privateKeySpec);

			Cipher decryptCipher = Cipher.getInstance(AppConstants.ENCRYPTION_MODE_RSA);
			decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedMessageBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedString));
			String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

			return decryptedMessage;

		} else {
			return encryptedString;
		}
	}

}