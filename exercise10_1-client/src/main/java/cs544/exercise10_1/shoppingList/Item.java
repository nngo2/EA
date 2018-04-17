package cs544.exercise10_1.shoppingList;

public class Item {

	String product;
	int qty;
	String notes;
	
	Item() {
		super();
	}

	public Item(String product, int qty, String notes) {
		this.product = product;
		this.qty = qty;
		this.notes = notes;
	}

	public final String getProduct() {
		return product;
	}

	public final void setProduct(String product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public String toString() {
		return qty+"\t"+product+"\t"+notes;
	}
	
}
