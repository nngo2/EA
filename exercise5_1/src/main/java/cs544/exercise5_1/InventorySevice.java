package cs544.exercise5_1;

public class InventorySevice implements IInventoryService {
	@Override
	public int getNumberInStock(int productNumber) {
		return productNumber - 200;
	}
}
