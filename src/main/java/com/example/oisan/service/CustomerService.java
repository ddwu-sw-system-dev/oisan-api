package com.example.oisan.service;

import java.util.List;

import com.example.oisan.controller.CustomerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.oisan.controller.CustomerUpdateCommand;
import com.example.oisan.domain.Customer;
import com.example.oisan.exception.CustomerNotFoundException;
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
	
	public Customer addCustomer(CustomerCommand customerCom) throws Exception{
		if (customerRepository.findCustomerByEmail(customerCom.getEmail()) != null)
			throw new Exception("email already in use");
		Customer customer = new Customer(customerCom.getCustomerId(), customerCom.getCustomerName(), customerCom.getEmail(), customerCom.getPw(),
				customerCom.getConfirmPw(), customerCom.getPhone(), customerCom.getNickname());
		return customerRepository.save(customer);
	}
	
	public void deleteCustomer(int customerId) {
		customerRepository.deleteById(customerId);
	}
	
	public Customer updateCustomer(CustomerUpdateCommand updateCustomer) {
		Customer customer = new Customer();
		customer.setCustomerName(updateCustomer.getCustomerName());
		customer.setEmail(updateCustomer.getEmail());
		customer.setPw(updateCustomer.getPw());
		customer.setAddress(updateCustomer.getAddress());
		customer.setPhone(updateCustomer.getPhone());
		customer.setNickname(updateCustomer.getNickname());
		customer.setOiPay(updateCustomer.getOiPay());
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
	
	public Customer loginCustomer(String email, String pw) throws Exception{
		Customer customer = customerRepository.findCustomerByEmail(email);
		if (customer == null)
			throw new Exception("customer not exist");
		if (customer.getPw().equals(pw)) {
			return customer;
		}
		else {
			throw new Exception("wrong password");
		}
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
