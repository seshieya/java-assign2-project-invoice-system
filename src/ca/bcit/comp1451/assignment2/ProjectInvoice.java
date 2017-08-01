/**
 * 
 */
package ca.bcit.comp1451.assignment2;


/**
 * @author ANGELA WU
 * @version July 7, 2017 - v1
 */
public abstract class ProjectInvoice implements Comparable<ProjectInvoice> {

	private static final int MIN_WORKING_HRS = 0;
	private static final int MIN_HOURLY_RATE = 0;
	
	private static int uniqueNumber = 1000;
	
	private String invoiceNumber; 
	private String projectName;
	private double numOfWorkingHrs;
	private double hourlyRate;
	
	/**
	 * Constructor for objects of class ProjectInvoice
	 * @param projectName
	 * @param numOfWorkingHrs
	 * @param hourlyRate
	 */
	public ProjectInvoice(String projectName, int numOfWorkingHrs, double hourlyRate) {
		setProjectName(projectName);
		setNumOfWorkingHrs(numOfWorkingHrs);
		setHourlyRate(hourlyRate);
		
		createInvoice();
	}
	
	
	
	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}



	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}



	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		if(projectName != null && !projectName.isEmpty()) {
			this.projectName = projectName;
		}
	}



	/**
	 * @return the numOfWorkingHrs
	 */
	public double getNumOfWorkingHrs() {
		return numOfWorkingHrs;
	}



	/**
	 * @param numOfWorkingHrs the numOfWorkingHrs to set
	 */
	public void setNumOfWorkingHrs(int numOfWorkingHrs) {
		if(numOfWorkingHrs >MIN_WORKING_HRS) {
			this.numOfWorkingHrs = numOfWorkingHrs;
		}
	}



	/**
	 * @return the hourlyRate
	 */
	public double getHourlyRate() {
		return hourlyRate;
	}



	/**
	 * @param hourlyRate the hourlyRate to set
	 */
	public void setHourlyRate(double hourlyRate) {
		if(hourlyRate > MIN_HOURLY_RATE) {
			this.hourlyRate = hourlyRate;
		}
	}

	/**
	 * create unique invoice number for invoice
	 */
	public void createInvoice() { 
		uniqueNumber++;
		this.invoiceNumber = "INV" + uniqueNumber;
	}
	
	/**
	 * abstract method
	 * @return calculated costs
	 */
	public abstract double calculateCost();
	
	/**
	 * sorts project invoices by their total costs.
	 */
	@Override
	public int compareTo(ProjectInvoice invoice) {
//		return (this.getNumOfWorkingHrs() - invoice.getNumOfWorkingHrs());
		return (int)(this.calculateCost() - invoice.calculateCost());
	}
	
	/**
	 * override toString() method
	 * @return the costs broken down
	 */
	@Override
	public String toString() {
		return "INVOICE NUMBER: " + this.getInvoiceNumber() + "\nProject name: " + this.getProjectName() +
			   "\nNumber of working hours: " + this.getNumOfWorkingHrs() + "\nHourly Rate: " + this.getHourlyRate();
	}
	
	
}
