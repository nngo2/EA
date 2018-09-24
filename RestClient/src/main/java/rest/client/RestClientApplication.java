package rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {
	@Autowired
	private IVehicleClient vehicleClient; 
	
	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("List vehicles:");
		
		for (Integer id : vehicleClient.getVehicleIds()) {
			System.out.println(vehicleClient.getVehicle(id));
		}	
	}
	
}
