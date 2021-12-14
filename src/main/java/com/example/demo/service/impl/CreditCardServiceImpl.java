package com.example.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CreditCardInfo;
import com.example.demo.model.UserDto;
import com.example.demo.service.CreditCardService;
import com.example.demo.util.DecryptionUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	DecryptionUtil decryptionUtil;
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public CreditCardInfo getCreditCardInfo(String creditCardNumber) {

		try {
			log.info("creditCardNumber :" + creditCardNumber);
			String decryptedCreditCardNumber = decryptionUtil.decryptFromCloud(creditCardNumber);
			log.info("decryptedCreditCardNumber :" + decryptedCreditCardNumber);
			
		} catch (Exception ex) {
			log.error("Exception while decrypting", ex);
		}

		return CreditCardInfo.builder().expiry(new Date()).issued(new Date()).build();
	}
	
	public CreditCardInfo getCreditCardDetails(String message) {

		try {
			log.info("message :" + message);
			String decryptedmessage = decryptionUtil.decryptFromCloud(message);
			log.info("Decryptedmessage :" +decryptedmessage );
			
			UserDto userdto = objectMapper.readValue(decryptedmessage,UserDto.class);
			log.info(objectMapper.writeValueAsString(UserDto.class));
			
		} catch (Exception ex) {
			log.error("Exception while decrypting", ex);
		}

		return CreditCardInfo.builder().expiry(new Date()).issued(new Date()).build();
	}

}
