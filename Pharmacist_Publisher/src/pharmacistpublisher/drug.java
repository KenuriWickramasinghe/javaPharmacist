package pharmacistpublisher;

public class drug {
	
	private String Did;
	private String Name;
	private float price;
	
	public drug(String Did,String Name,float price) {
		this.Did = Did;
		this.Name = Name;
		this.price = price;
	}

	public String getDid() {
		return Did;
	}

	public void setDid(String did) {
		Did = did;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

}
