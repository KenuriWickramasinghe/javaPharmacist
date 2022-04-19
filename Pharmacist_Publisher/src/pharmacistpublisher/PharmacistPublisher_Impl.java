package pharmacistpublisher;

import java.util.ArrayList;

public class PharmacistPublisher_Impl implements PharmacistPublish{
	ArrayList<drug> druglist = new ArrayList<drug>();
	private double bill;
	private String[][] billdetails = new String[1000][4];
	private int count = -1;
	
	public void drugs() {
		druglist.add(new drug("D001","Penadol",3));
		druglist.add(new drug("D002","Codral",10));
		druglist.add(new drug("D003","Dimetapp",14));
		druglist.add(new drug("D004","Maxiclear",8));
		druglist.add(new drug("D005","Piriton",2));
		druglist.add(new drug("D006","C vitemin",10));
	}
	
	public void AllDrugs() {
		System.out.println("----------All ----------\n");
		
		for(int i = 0; i < druglist.size(); i++) {
        	System.out.print("\t" + druglist.get(i).getDid() + " : " + druglist.get(i).getName() + " : " + druglist.get(i).getPrice() + "\n");
        }
        System.out.println();
	}
	
	public boolean isAvailable(String Name) {
		if(druglist.contains(Name)) {
			//System.out.print("\t" + druglist.getDid() + " : " + druglist.getName() + " : " + druglist.getPrice() + "\n");
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addNewDrug(String Did,String Name,float price){
		druglist.add(new drug(Did, Name, price));
		AllDrugs();
	}
	
	public int generateBill(String id,int qty) {
		
		boolean valid = false;
		drug currentItem = null;
		drugs();
		for (drug tempItem : druglist) {
//			System.out.println(tempItem.getDid());
			if(id.equals(tempItem.getDid())) {
//				System.out.println("inside if");
				currentItem = tempItem;
				valid = true;
				count++;
				break;
			}
		}
		
		if(valid) {
//			System.out.println(currentItem.getDid());	
		this.bill = this.bill + (currentItem.getPrice() * qty); 
			
		billdetails[count][0]= currentItem.getDid();
		billdetails[count][1]= currentItem.getName();
		
		billdetails[count][2]= Integer.toString(qty);
		billdetails[count][3]= Double.toString((currentItem.getPrice() * qty));
			
		return 1;
		}
		else {
			return -1;
		}
		
	}
	
	public double displayFinalBillAmount() {
		double finalBill = this.bill;
		newBill();
	
		return finalBill;
	
	}
	
	public String[][] dispalybillDetails(){
	
		return billdetails;
	}
	
	public void newBill() {//To reset all relevant fields to default values 
		this.bill = 0;
		this.count=-1;		
	}
}
