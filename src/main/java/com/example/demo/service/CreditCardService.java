package com.example.demo.service;

import com.example.demo.model.CreditCardInfo;

public interface CreditCardService {

	public CreditCardInfo getCreditCardInfo(String creditCardNumber);


	//public String sendStatus(String userJson);


	public CreditCardInfo getCreditCardDetails(String message);

}
