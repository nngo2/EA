package rest.client;

import java.util.List;

public interface IVehicleClient {
	List<Integer> getVehicleIds();
	Vehicle getVehicle(Integer vehicleId);
}
