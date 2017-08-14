package com.example.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.entity.Customer;
import com.example.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class AppController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value="/getCustomers", method = RequestMethod.GET)
	public List<Customer> getCustomers()
	{
		return customerService.getCustomers();
	}
	
}
