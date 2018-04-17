package cs544.exercise10_2.service;

public class Calculator implements ICalculator {
	private int state;

	@Override
	public synchronized int calc(char operator, int value) {
		switch (operator) {
		case '+':
			state += value;
			break;
		case '-':
			state -= value;
			break;
		case '*':
			state *= value;
			break;
		case '/':
			state /= value;
			break;
		}
		
		return state;
	}
}
