package pharmacistsubscriber;
import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import pharmacistpublisher.PharmacistPublish;
import pharmacistpublisher.drug;

public class Activator implements BundleActivator {

	ServiceReference pharmacistServiceReference;
	Scanner s = new Scanner(System.in);
	
	public void start(BundleContext context) throws Exception {
		System.out.println("============Pharmacist consumer started.============");
		pharmacistServiceReference = context.getServiceReference( PharmacistPublish.class.getName());
		PharmacistPublish  pharmacistPublish = (PharmacistPublish)context.getService(pharmacistServiceReference);
		
		String calculateFinalBill = null;
		int back = -1;
		int count = -1;
		int select = 0;
		System.out.print("Do you want to start your duty (y/ n) : ");
		String d = s.nextLine();
		do {
		if(d.equalsIgnoreCase("y")) {
			System.out.println("----------------------------Welcome-------------------------------");
			
			System.out.println("Please Select an option to continue.....");
			System.out.println("Options");
			System.out.println("1.View Drug List");
			System.out.println("2.Add new Drug");
			System.out.println("3.Generate customer bills");
			System.out.println("4.Exit");
			
			System.out.println("Enter your selection...");
			 select = s.nextInt();
			 
			 if(select==1) {
				 
				 do {
				 System.out.println("--------------------Drug list--------------------------");
				 pharmacistPublish.drugs();
				 pharmacistPublish.AllDrugs();
				 System.out.println("-------------------------------------------------------");
				 
				 System.out.println("Press 0 to continue...");
				 back = s.nextInt();
				 }while(!(back==0));
			 }
			 else if(select==2) {
				 System.out.print("Please enter drug name : ");
				 String dName = s.next();
				 
				 if(!(pharmacistPublish.isAvailable(dName))) {
					 System.out.print("Enter drug ID : ");
						String Did = s.next();
							
						System.out.print("Enter drug Name : ");
						String Name = s.next();
						
						System.out.print("Enter drug Price : ");
						float Price = s.nextFloat();
						
						pharmacistPublish.addNewDrug(Did, Name, Price);
						System.out.println("This drug is added.");
					
				 }
				 else {
					 System.out.println("This drug is already availabale.");
					
				 }
				 
			 }
			 else if(select==3) {
				 do {
				 do {
				 System.out.println("---------------------next billing --------------------" + "\n");
					System.out.println("Enter the drug id");
					String id = s.next();
					
					System.out.println("Enter the quantity");
					int qty = s.nextInt();
					
					int r = pharmacistPublish.generateBill(id, qty);
					if(r == -1) {
						 System.out.println("Please enter a valid item number!!");
					}
					else {
						count++;
					}
					s.nextLine();
					System.out.println("Press y to calclate the totoal bill or any other key to continue the billing....");	
					calculateFinalBill=s.nextLine();
			 }while(!(calculateFinalBill.equals("y")));
				 
				 System.out.println("------------------------------------------Receipt----------------------------------------");
					String[][] billDetails= pharmacistPublish.dispalybillDetails();//Consumes the cashierService displaybillDetails()
					
					String format = "%-20s";
					System.out.printf(format, "Drug ID:");
					System.out.printf(format, "Drug Name:");
					System.out.printf(format, "Drug Quantity:");
					System.out.printf(format,"Total:");
					System.out.println("");
					for (int i=0; i<=count; i++) {
						  for (int j=0; j<billDetails[i].length; j++) {
					
						System.out.printf(format,billDetails[i][j]);
						 
						  }
						  System.out.println("");
						  }
					System.out.println("                                                          ----------");
					System.out.println("Subtotal:                                                   " + pharmacistPublish.displayFinalBillAmount());
					System.out.println("                                                          ----------");
					System.out.println("-------------------------------------------------------------------------------------------");
					
			  
					count=-1;
					
			System.out.println("Press 0 to continue....");
			
			back=s.nextInt();
			
			}
			
			while(!(back==0));
			 }
			 else if(select == 4) {
				System.out.println("=======GOOD BYE!+THANK YOU FOR YOUR SERVICE=======");
				return;
			}
			 
		}else {
			System.out.println("=======GOOD BYE!=======");
		}
		
		System.out.print("Do you want to start your duty (y/ n) : ");
		d = s.next();
		
		}while(d.equalsIgnoreCase("y"));
		System.out.println("=======GOOD BYE!=======");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("============Pharmacist consumer stopped.============");
		context.ungetService(pharmacistServiceReference);
	}

}
