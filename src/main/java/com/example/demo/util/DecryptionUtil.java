package com.example.demo.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.constants.AppConstants;
import com.google.cloud.kms.v1.AsymmetricDecryptResponse;
import com.google.cloud.kms.v1.CryptoKeyVersionName;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;

@Component
public class DecryptionUtil {

	/*
	 * @Value("${key.private}") String privateKeyString;
	 */

	@Value("${gcp.project-id}")
	String projectId;

	@Value("${gcp.key-zone}")
	String projectZone;

	@Value("${gcp.key-ring}")
	String keyRingName;

	@Value("${gcp.key-name-rsa}")
	String keyName;

	@Value("${gcp.key-version-rsa}")
	String keyVersion;

	/*
	 * public String decrypt(String encryptedString) throws
	 * IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
	 * InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
	 * 
	 * if (StringUtils.hasText(encryptedString)) {
	 * 
	 * encryptedString = encryptedString.replaceAll(AppConstants.CHAR_ASTERISK,
	 * AppConstants.CHAR_FORWARDSLASH);
	 * 
	 * byte[] privateKeyBytes = privateKeyString.getBytes(StandardCharsets.UTF_8);
	 * privateKeyBytes = Base64.getDecoder().decode(privateKeyBytes);
	 * 
	 * KeyFactory keyFactory2 =
	 * KeyFactory.getInstance(AppConstants.ENCRYPTION_MODE_RSA); PKCS8EncodedKeySpec
	 * privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
	 * 
	 * RSAPrivateKey privateKey = (RSAPrivateKey)
	 * keyFactory2.generatePrivate(privateKeySpec);
	 * 
	 * Cipher decryptCipher = Cipher.getInstance(AppConstants.ENCRYPTION_MODE_RSA);
	 * decryptCipher.init(Cipher.DECRYPT_MODE, privateKey); byte[]
	 * decryptedMessageBytes =
	 * decryptCipher.doFinal(Base64.getDecoder().decode(encryptedString)); String
	 * decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
	 * 
	 * return decryptedMessage;
	 * 
	 * } else { return encryptedString; } }
	 */

	public String decryptFromCloud(String encryptedString) throws IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
			IOException {

		if (StringUtils.hasText(encryptedString)) {

			encryptedString = encryptedString.replaceAll(AppConstants.CHAR_ASTERISK, AppConstants.CHAR_FORWARDSLASH);

			KeyManagementServiceClient client = KeyManagementServiceClient.create();

			CryptoKeyVersionName keyVersionName = CryptoKeyVersionName.of(projectId, projectZone, keyRingName, keyName,
					keyVersion);

			AsymmetricDecryptResponse response = client.asymmetricDecrypt(keyVersionName,
					ByteString.copyFrom(Base64.getDecoder().decode(encryptedString)));

			response.getPlaintext().toStringUtf8();

			return response.getPlaintext().toStringUtf8();

		} else {
			return encryptedString;
		}
	}

}
