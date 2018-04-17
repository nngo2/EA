package cs544.exercise10_1.service;

import cs544.exercise10_1.customer.Customer;

import java.util.Collection;

public interface ICustomerService {
	public void addCustomer(Customer customer);
	public void deleteCustomer(String customerNumber);
	public void updateCustomer(Customer customer);
	public Collection<Customer> getCustomers();
	public Customer getCustomer(String customerNumber);
}
