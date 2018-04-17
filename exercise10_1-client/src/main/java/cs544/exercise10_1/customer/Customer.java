package cs544.exercise10_1.customer;

public class Customer {

	String customerNumber;
	String name;
	
	public Customer() {
		super();
	}
	public Customer(String customerNumber, String name) {
		super();
		this.customerNumber = customerNumber;
		this.name = name;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
