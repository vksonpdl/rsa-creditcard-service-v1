package com.example.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CreditCardInfo;
import com.example.demo.service.CreditCardService;
import com.example.demo.util.DecryptionUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	DecryptionUtil decryptionUtil;

	@Override
	public CreditCardInfo getCreditCardInfo(String creditCardNumber) {

		try {
			log.info("creditCardNumber :" + creditCardNumber);
			String decryptedCreditCardNumber = decryptionUtil.decrypt(creditCardNumber);
			log.info("decryptedCreditCardNumber :" + decryptedCreditCardNumber);
			
		} catch (Exception ex) {
			log.error("Exception while decrypting", ex);
		}

		return CreditCardInfo.builder().expiry(new Date()).issued(new Date()).build();
	}

}
