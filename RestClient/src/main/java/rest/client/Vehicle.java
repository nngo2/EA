package rest.client;

public class Vehicle {
	private Integer vehicleId = null;
	private Integer year = null;
	private String make = null;
	private String model = null;
	private Integer dealerId = null;

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getDealerId() {
		return dealerId;
	}

	public void setDealerId(Integer dealerId) {
		this.dealerId = dealerId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class VehicleResponse {\n");
		sb.append("    vehicleId: ").append(toIndentedString(vehicleId)).append("\n");
		sb.append("    year: ").append(toIndentedString(year)).append("\n");
		sb.append("    make: ").append(toIndentedString(make)).append("\n");
		sb.append("    model: ").append(toIndentedString(model)).append("\n");
		sb.append("    dealerId: ").append(toIndentedString(dealerId)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
