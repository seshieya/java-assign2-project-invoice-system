/**
 * 
 */
package ca.bcit.comp1451.assignment2;

import java.util.Scanner;

/**
 * @author ANGELA WU
 * @version July 8, 2017 - v1
 */
public class Driver {
	public static void main(String[] args) {
		InsuranceCompany insCompany = new InsuranceCompany("Aviva");
		
		Labour proj1 = new Labour("Install Cabinets", 10, 55, 20, "Overtime", "Inexperienced");
		Labour proj2 = new Labour("Move Furniture", 16, 40, 10, "Holiday", "Experienced");
		
		LabourAndMaterial proj3 = new LabourAndMaterial("Supply and Install Cabinets", 40, 55, 15, "Regular", "Inexperienced", 5.4, 150, 20, 100);
		LabourAndMaterial proj4 = new LabourAndMaterial("Supply and Install Drywall", 20, 45, 10, "Holiday", "Experienced", 5.4, 80, 5, 100);
		
		LabourAndMaterialAndEquipment proj5 = new LabourAndMaterialAndEquipment("Supply and Install Cabinets with Equipment Rental",
				40, 55, 15, "Regular", "Inexperienced", 5.4, 150, 20, 400, 100, 50, 100, 10);
		LabourAndMaterialAndEquipment proj6 = new LabourAndMaterialAndEquipment("Supply and Install Drywall with Equipment Rental",
				20, 45, 10, "Holiday", "Experienced", 5.4, 80, 5, 100, 50, 90, 40, 2);
		
				
		
		insCompany.addInvoice(proj1);
		insCompany.addInvoice(proj2);
		insCompany.addInvoice(proj3);
		insCompany.addInvoice(proj4);
		insCompany.addInvoice(proj5);
		insCompany.addInvoice(proj6);
		
		insCompany.sortProjectInvoices();
		
//		for(ProjectInvoice list : insCompany.getAllInvoices()) {
//			System.out.println(list);
//		}
		
		System.out.println("Total insurance fees: " + insCompany.calculateInsuranceFees());
		
		boolean active = true;

		Scanner input = new Scanner(System.in);

		while(active) {
			System.out.println("Enter an invoice number:");
			String invoiceNum = input.next();
			
			try{
				insCompany.getInvoice(invoiceNum);
			}
			catch(InvalidInvoiceNumber exc) {
				System.out.println(exc.getMessage());
			}
			finally {
				System.out.println("Would you like to enter another invoice number?");
				
				
				while(true) {
					String answer = input.next();

					if(answer.equalsIgnoreCase("yes")) {
						break;
					}
					else if(answer.equalsIgnoreCase("no")) {
						active = false;
						break;
					}
					else {
						System.out.println("Please enter yes or no.");
					}
				}
			}
		}
		
		input.close();		
		
	}
	
}
