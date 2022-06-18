package com.example.oisan.service;

import java.util.ArrayList;
import java.util.List;

import com.example.oisan.controller.CustomerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oisan.controller.CustomerUpdateCommand;
import com.example.oisan.domain.Customer;
import com.example.oisan.domain.Post;
import com.example.oisan.repository.CustomerRepository;
import com.example.oisan.repository.PostRepository;


@Service
public class CustomerService {
	
	@Autowired
	private PostRepository postRepository;
	public void setPostRepository(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	@Autowired
	private CustomerRepository customerRepository;
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public CustomerService() {}
	
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
		return customerRepository.findCustomerByEmail(email);
	}
	
	public List<Post> getCustomerLikePostList(int customerId){
		List<Integer> postIdList = customerRepository.findLikePostByCustomerId(customerId);
		List<Post> likePostList = new ArrayList<Post>();
		for (Integer pId : postIdList) {
			likePostList.add(postRepository.findByPostId(pId));
		}
		return likePostList;
	}
	
}
