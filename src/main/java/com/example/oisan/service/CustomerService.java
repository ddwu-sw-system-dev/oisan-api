package com.example.oisan.service;

import java.util.List;

import com.example.oisan.controller.CustomerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oisan.controller.CustomerUpdateCommand;
import com.example.oisan.domain.Customer;
import com.example.oisan.repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
//	private Map<Integer, Customer> customerMap = new HashMap<Integer, Customer>();

	public CustomerService() {
//		customerMap.put(0, new Customer(0, "test1","testaa@gmail.com","123123","seoul","010-123-1234","testnick"));
//		customerMap.put(1, new Customer(1, "test2","test2@gmail.com","123123","seoul","010-123-1224","tes12nick"));
	}
	
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer addCustomer(CustomerCommand customerCom) {
		Customer customer = new Customer(customerCom.getCustomerName(), customerCom.getEmail(), customerCom.getPw(),
				customerCom.getAddress(), customerCom.getPhone(), customerCom.getNickname());
		return customerRepository.save(customer);
	}
	
	public void deleteCustomer(int customerId) {
		customerRepository.deleteById(customerId);
	}
	
	public Customer updateCustomer(CustomerCommand CustomerCom) {
		Customer customer = new Customer();
		customer.setCustomerId(CustomerCom.getCustomerId());
		customer.setCustomerName(CustomerCom.getCustomerName());
		customer.setEmail(CustomerCom.getEmail());
		customer.setPw(CustomerCom.getPw());
		customer.setAddress(CustomerCom.getAddress());
		customer.setPhone(CustomerCom.getPhone());
		customer.setNickname(CustomerCom.getNickname());
//		customer.setOiPay(CustomerCom.getOiPay());
		return customerRepository.save(customer);
	}
	
	public Customer getCustomerInfoByEmail(String email) {
		return customerRepository.findCustomerByEmail(email);
	}
	
	public Customer getCustomerInfoByNickname(String nickname) {
		return customerRepository.findCustomerByNickname(nickname);
	}
	
	public Customer getCustomerInfoByCustomerId(int customerId) {
		return customerRepository.findCustomerByCustomerId(customerId);
	}
	
	public Customer loginCustomer(String email, String pw) {
//		if (loginCustomer.getEmail() == null || loginCustomer.getPw() == null)
//			return null; //empty form
//		Customer customer = getCustomerInfoByEmail(loginCustomer.getEmail());
//		if (customer == null)
//			return null; //customer not exist
//		if (!customer.getPw().equals(loginCustomer.getPw()))
//			return null; //password not match
		return customerRepository.findCustomerByEmail(email);
	}
	
//	public CustomerCreateCommand modifyCustomerInfo(CustomerModRequest modReq) { //CustomerModRequest 혜준이가 만들어줌
//		CustomerCreateCommand customer = customerMap.get(modReq.getCustomerCustomerId()); // request에서 getCustomerCustomerId로 만들어줘야함
//		if (customer == null)
//			throw new CustomerNotFoundException(); //생성해줘야함
//	
//		customer.setEmail(modReq.getEmail());
//		customer.setCustomerName(modReq.getCustomerName());
//		customer.setPw(modReq.getPw());
//		customer.setAddress(modReq.getAddress());
//		customer.setPhone(modReq.getPhone());
//		customer.setNickname(modReq.getNickname());
//		return customer;
//	}

}
