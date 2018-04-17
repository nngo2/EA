package cs544.exercise10_1.service;

import java.util.Collection;

import cs544.exercise10_1.shoppingList.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value="/shopping")
public class ShoppingListController {

	private IShoppingListService shoppingListService;
	
	@Autowired
	public void setShoppingListService(IShoppingListService shoppingListService) {
		this.shoppingListService = shoppingListService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)	
	public @ResponseBody Collection<Item> list() {
		return shoppingListService.getList();		
	}

	@RequestMapping(value = "/item", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addItem(@RequestBody Item item) {
		shoppingListService.addToList(item);
//		return new ResponseEntity();
	}

	@RequestMapping(value = "/item/{product}*", method = RequestMethod.GET)
	public @ResponseBody Item item(@PathVariable("product") String product) {
		return shoppingListService.getItem(product);
	}

	@RequestMapping(value = "/item/{product}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Item updateItem(@RequestBody Item item) {

		shoppingListService.updateItem(item);
		return shoppingListService.getItem(item.getProduct());
	}

	@RequestMapping(value = "/item/{product}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteItem(@PathVariable("product") String product) {
		shoppingListService.removeFromList(product);
	}

}
