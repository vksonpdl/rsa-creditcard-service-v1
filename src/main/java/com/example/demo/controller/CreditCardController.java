package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CreditCardInfo;
import com.example.demo.model.MessageDto;
import com.example.demo.service.CreditCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

	@Autowired
	CreditCardService service;

	@Autowired
	ObjectMapper objectMapper;

	@GetMapping("getdetails/{creditcardnumber}")
	public CreditCardInfo getCreditCardInfo(@PathVariable("creditcardnumber") String creditcardnumber) {

		return service.getCreditCardInfo(creditcardnumber);

	}
	
	@GetMapping("getdetails/jwt/{jwtstring}")
	public CreditCardInfo getCreditCardInfoWithJWT(@PathVariable("jwtstring") String jwtstring) {

		return service.getCreditCardInfoWithJWT(jwtstring);

	}

	@PostMapping("/getcreditcard")
	public CreditCardInfo getCreditCardInfo(@RequestBody MessageDto messageDto) throws JsonProcessingException {

		return service.getCreditCardDetails(messageDto);

	}

}
