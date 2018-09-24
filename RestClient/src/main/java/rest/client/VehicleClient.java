package rest.client;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleClient implements IVehicleClient {
	private static final String ROOT_URL = "http://vautointerview.azurewebsites.net/api/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String dataSetId;
	
	@PostConstruct
	public void initData() {
		dataSetId = restTemplate.getForObject(ROOT_URL + "datasetId", DatasetId.class).getDatasetId();
	}
	
	@Override
	public Vehicle getVehicle(Integer vehicleId) {
		return restTemplate.getForObject(ROOT_URL + dataSetId + "/vehicles/" + vehicleId, Vehicle.class);
	}

	@Override
	public List<Integer> getVehicleIds() {
		String apiUrl = ROOT_URL + dataSetId + "/vehicles";
		ResponseEntity<VehicleIds> entity = restTemplate.getForEntity(apiUrl, VehicleIds.class);
		return entity.getBody().getVehicleIds();
	}

}
