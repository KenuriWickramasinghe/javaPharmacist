package pharmacistpublisher;

public interface PharmacistPublish {
	public void drugs();
	public void AllDrugs();
	public boolean isAvailable(String Name);
	public void addNewDrug(String Did,String Name,float price);
	public int generateBill(String id,int qty);
	public double displayFinalBillAmount();
	public String[][] dispalybillDetails();
}
