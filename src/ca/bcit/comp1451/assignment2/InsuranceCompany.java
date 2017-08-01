/**
 * 
 */
package ca.bcit.comp1451.assignment2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author ANGELA WU
 * @version July 8, 2017 - v1
 */
public class InsuranceCompany {

	private static final int INVOICE_NUMBER_MAX_LENGTH = 7;
	private static final double LABOUR_INSURANCE_RATE = 1.05;
	private static final double LABOUR_AND_MATERIAL_INSURANCE_RATE = 1.07;
	private static final double LABOUR_AND_MATERIAL_AND_EQUIPMENT_INSURANCE_RATE = 1.10;
	
	private String companyName;
	private ArrayList<ProjectInvoice> allInvoices;
	
	/**
	 * Constructor for objects of class InsuranceCompany
	 * @param companyName the company name
	 */
	public InsuranceCompany(String companyName) {
		setCompanyName(companyName);
		
		allInvoices = new ArrayList<ProjectInvoice>();
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		if(companyName != null && !companyName.isEmpty()) {
			this.companyName = companyName;
		}
		else {
			this.companyName = "Unknown";
		}
	}

	/**
	 * @return the invoices
	 */
	public ArrayList<ProjectInvoice> getAllInvoices() {
		return allInvoices;
	}
	
	/**
	 * 
	 * @param invoiceObj invoice to add to the allInvoices ArrayList
	 */
	public void addInvoice(ProjectInvoice invoiceObj) {
		if(invoiceObj != null) {
			allInvoices.add(invoiceObj);
		}
	}
	
	/**
	 * 
	 * @param invoiceNum The invoice number
	 * @throws InvalidInvoiceNumber  throws this error if the invoice length is less than 7 characters long
	 */
	public void getInvoice(String invoiceNum) throws InvalidInvoiceNumber {
		
		if(invoiceNum == null || invoiceNum.length() < INVOICE_NUMBER_MAX_LENGTH) {
			throw new InvalidInvoiceNumber("Invalid invoice number.");
		}
		
		boolean invoiceFound = false;
				
		for(ProjectInvoice invoiceList : allInvoices) {
			if(invoiceList.getInvoiceNumber().equals(invoiceNum)) {
				System.out.println(invoiceList);
				invoiceFound = true;
			}
		}
		
		if(invoiceFound == false) {
			System.out.println("Invoice was not found.");
		}
	}
	
	/**
	 * 
	 * @return the amount of insurance fees to charge
	 */
	public double calculateInsuranceFees() {
		
		double insuranceFees = 0;
		double insuranceFeeOfAllInvoices = 0;
		
		for(ProjectInvoice invoiceList : allInvoices) {
			if(invoiceList instanceof Labour) {
				insuranceFees = invoiceList.calculateCost() * LABOUR_INSURANCE_RATE;
			}
			else if(invoiceList instanceof LabourAndMaterial) {
				insuranceFees = invoiceList.calculateCost() * LABOUR_AND_MATERIAL_INSURANCE_RATE;
			}
			else if(invoiceList.getClass().getSimpleName().equals("LabourAndMaterialAndEquipment")) {
				insuranceFees = invoiceList.calculateCost() * LABOUR_AND_MATERIAL_AND_EQUIPMENT_INSURANCE_RATE;
			}
			
			insuranceFeeOfAllInvoices += insuranceFees;
		}
				
		return insuranceFeeOfAllInvoices;
	}
	
	/**
	 * sort ArrayList by the cost of the project
	 */
	public void sortProjectInvoices() {
		Collections.sort(allInvoices);
	}
	

	
	
}
