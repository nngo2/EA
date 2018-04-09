package cs544.exercise5_2;

public class Borders implements IBookSupplier {

	@Override
	public double computePrice(String isbn) {
		double price = Math.random() * 25;
		System.out.println("Borders charges $" + price + " for book with isbn "
				+ isbn);
		return price;
	}

	@Override
	public void order(Book book) {
		System.out.println("Book with isbn = " + book.getIsbn()
		+ " is ordered from Borders");
		
	}

}
