package com.example.oisan.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oisan.domain.Customer;
import com.example.oisan.service.CustomerService;
import com.example.oisan.command.CustomerUpdateCommand;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping("/create")
	public Customer create(Customer newCustomer) {
		return customerService.addCustomer(newCustomer);
	}
	
	@PutMapping
	public void update(CustomerUpdateCommand updateCustomer) {
		customerService.updateCustomer(updateCustomer);
	}
	
	@DeleteMapping
	public void delete(@PathVariable String customerId) {
		int id = Integer.parseInt(customerId);
		customerService.deleteCustomer(id);
	}
	
	@GetMapping("/{customerId}")
	public Customer getCustomerById(@PathVariable String customerId) {
		int id = Integer.parseInt(customerId);
		return customerService.getCustomerInfoByCustomerId(id); //test obj
	}
	
	@GetMapping
	public List<Customer> getCustomerList(){
		return customerService.getCustomers();
	}

}