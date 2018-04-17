package cs544.exercise10_1.service;

import java.util.Collection;

import cs544.exercise10_1.shoppingList.Item;

public interface IShoppingListService {

	public Collection<Item> getList();

	public Item getItem(String product);

	public void addToList(Item item);

	public void removeFromList(String product);

	public void updateItem(Item item);

}