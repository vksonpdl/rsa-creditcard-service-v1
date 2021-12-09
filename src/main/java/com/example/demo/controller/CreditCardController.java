package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CreditCardInfo;
import com.example.demo.service.CreditCardService;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

	@Autowired
	CreditCardService service;

	@GetMapping("getdetails/{creditcardnumber}")
	public CreditCardInfo getCreditCardInfo(@PathVariable("creditcardnumber") String creditcardnumber) {

		return service.getCreditCardInfo(creditcardnumber);

	}

}
