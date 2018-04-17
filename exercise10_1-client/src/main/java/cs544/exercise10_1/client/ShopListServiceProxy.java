package cs544.exercise10_1.client;

import java.util.Collection;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cs544.exercise10_1.service.IShoppingListService;
import cs544.exercise10_1.shoppingList.Item;

public class ShopListServiceProxy implements IShoppingListService {
	private static final String ServiceURL = "http://localhost:8080/exercise10_1-service/rest";
	private static final String listURL = ServiceURL+"/shopping/list";
	private static final String newItemURL = ServiceURL+"/shopping/item";
	private static final String itemURL = ServiceURL+"/shopping/item/{product}";

	private RestTemplate restTemplate;
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void addToList(Item item) {
		restTemplate.postForObject(newItemURL, item, Item.class);
	}

	public Item getItem(String product) {
		return restTemplate.getForObject(itemURL, Item.class, product);
	}

	public Collection<Item> getList() {
		ParameterizedTypeReference<Collection<Item>> typeRef = new ParameterizedTypeReference<Collection<Item>>() {};
		ResponseEntity<Collection<Item>> response = restTemplate.exchange(listURL, HttpMethod.GET, null, typeRef);
		return response.getBody();
	}

	public void removeFromList(String product) {
		restTemplate.delete(itemURL, product);
	}

	public void updateItem(Item item) {
		restTemplate.put(itemURL, item, item.getProduct());
	}

}
