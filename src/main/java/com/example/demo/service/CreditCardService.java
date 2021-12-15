package com.example.demo.service;

import com.example.demo.model.CreditCardInfo;
import com.example.demo.model.MessageDto;

public interface CreditCardService {

	public CreditCardInfo getCreditCardInfo(String creditCardNumber);


	public CreditCardInfo getCreditCardDetails(MessageDto messageDto);

}
