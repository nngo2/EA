package cs544.exercise10_1.client;

import java.util.Collection;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cs544.exercise10_1.customer.Customer;
import cs544.exercise10_1.service.ICustomerService;

public class CustomerServiceProxy implements ICustomerService {
	private static final String ServiceURL = "http://localhost:8080/exercise10_1-service/rest";
	private static final String listURL = ServiceURL + "/customers";
	private static final String customerURL = ServiceURL + "/customer";
	private static final String customerDetailURL = ServiceURL + "/customer/{customerNumber}";

	private RestTemplate restTemplate;

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public void addCustomer(Customer customer) {
		restTemplate.postForObject(customerURL, customer, Customer.class);
	}

	@Override
	public void deleteCustomer(String customerNumber) {
		restTemplate.delete(customerDetailURL, customerNumber);
	}

	@Override
	public void updateCustomer(Customer customer) {
		restTemplate.put(customerDetailURL, customer, customer.getCustomerNumber());
	}

	@Override
	public Collection<Customer> getCustomers() {
		ParameterizedTypeReference<Collection<Customer>> typeRef = new ParameterizedTypeReference<Collection<Customer>>() {
		};
		ResponseEntity<Collection<Customer>> response = restTemplate.exchange(listURL, HttpMethod.GET, null, typeRef);
		return response.getBody();
	}

	@Override
	public Customer getCustomer(String customerNumber) {
		return restTemplate.getForObject(customerDetailURL, Customer.class, customerNumber);
	}

}
