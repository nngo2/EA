package cs544.exercise10_1.client;

import java.util.Collection;

import cs544.exercise10_1.customer.Customer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cs544.exercise10_1.service.ICustomerService;

public class CustomerApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
		ICustomerService remoteService = context.getBean("customerServiceProxy", ICustomerService.class);

		// TODO add 3 customers & print customers list
		Customer customer = new Customer("1111", "Customer A");
		remoteService.addCustomer(customer);
		customer = new Customer("2222", "Customer B");
		remoteService.addCustomer(customer);		
		customer = new Customer("3333", "Customer C");
		remoteService.addCustomer(customer);
		
		Collection<Customer> customers = remoteService.getCustomers();
		printCustomers(customers);
		
		// TODO update 1 customer & print customers list
		customer = remoteService.getCustomer("3333");
		customer.setName("Customer CCCCC");
		remoteService.updateCustomer(customer);
		customer = remoteService.getCustomer("3333");
		System.out.println(customer.getName());
		
		// TODO delete 1 customer & print customers list
		remoteService.deleteCustomer("3333");
		customers = remoteService.getCustomers();
		printCustomers(customers);
				
	}

	public static void printCustomers(Collection<Customer> customers) {
		for (Customer customer : customers) {
			System.out.println(customer.getName() + " " + customer.getCustomerNumber());
		}
		System.out.println();
	}
}
