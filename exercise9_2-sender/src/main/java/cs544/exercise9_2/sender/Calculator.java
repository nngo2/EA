package cs544.exercise9_2.sender;

import java.io.Serializable;

public class Calculator implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double num1;
	private double num2;
	private Operator operator;
	
	public Calculator(double num1, double num2, Operator operator) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.operator = operator;
	}
	
	public double getNum1() {
		return num1;
	}
	public void setNum1(double num1) {
		this.num1 = num1;
	}
	public double getNum2() {
		return num2;
	}
	public void setNum2(double num2) {
		this.num2 = num2;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "Calculator [num1=" + num1 + ", num2=" + num2 + ", operator=" + operator + "]";
	}
}
