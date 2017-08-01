/**
 * 
 */
package ca.bcit.comp1451.assignment2;

/**
 * @author ANGELA WU
 * @version July 7, 2017 - v1
 */
public class Labour extends ProjectInvoice {
	
	private static final int MIN_DISTANCE = 0;
	private static final double TRANSPORTATION_RATE = 1.2;
	private static final double TAX = 1.05;
	private static final double OVERTIME_SURCHARGE = 1.5;
	private static final double HOLIDAY_SURCHARGE = 2.0;
	
	private int distanceOfTransportationInKm;
	private String hrRateCriteria;
	private String typeOfLabour;
	
	/**
	 * Constructor for objects of class Labour
	 * @param projectName the project name
	 * @param numOfWorkingHrs the number of working hours needed to complete this project
	 * @param hourlyRate the hourly rate of each worker
	 * @param distanceOfTransportationInKm  the distance needed for transportation in KM
	 * @param hrRateCriteria  the criteria to base the hourly rate on: regular, overtime, or holiday
	 * @param typeOfLabour type of labour is either experienced of inexperienced
	 */
	public Labour(String projectName, int numOfWorkingHrs, double hourlyRate, int distanceOfTransportationInKm, String hrRateCriteria, String typeOfLabour) {
		super(projectName, numOfWorkingHrs, hourlyRate);
		setDistanceOfTransportationInKm(distanceOfTransportationInKm);
		setHrRateCriteria(hrRateCriteria);
		setTypeOfLabour(typeOfLabour);
	}

	/**
	 * @return the distanceOfTransportationInKm
	 */
	public int getDistanceOfTransportationInKm() {
		return distanceOfTransportationInKm;
	}

	/**
	 * @param distanceOfTransportationInKm the distanceOfTransportationInKm to set
	 */
	public void setDistanceOfTransportationInKm(int distanceOfTransportationInKm) {
		if(distanceOfTransportationInKm > MIN_DISTANCE) {
			this.distanceOfTransportationInKm = distanceOfTransportationInKm;
		}
	}

	/**
	 * @return the hrRateCriteria
	 */
	public String getHrRateCriteria() {
		return hrRateCriteria;
	}

	/**
	 * @param hrRateCriteria the hrRateCriteria to set
	 */
	public void setHrRateCriteria(String hrRateCriteria) {
		if(hrRateCriteria != null && 
		  (hrRateCriteria.equalsIgnoreCase("regular") 	|| 
		   hrRateCriteria.equalsIgnoreCase("overtime") 	||
		   hrRateCriteria.equalsIgnoreCase("holiday") 	)) {
			
			this.hrRateCriteria = hrRateCriteria;
		}
		else {
			this.hrRateCriteria = "regular";
		}
	}

	/**
	 * @return the typeOfLabour
	 */
	public String getTypeOfLabour() {
		return typeOfLabour;
	}

	/**
	 * @param typeOfLabour the typeOfLabour to set
	 */
	public void setTypeOfLabour(String typeOfLabour) {
		if(typeOfLabour != null && (typeOfLabour.equalsIgnoreCase("experienced") || typeOfLabour.equalsIgnoreCase("inexperienced"))) {
			this.typeOfLabour = typeOfLabour;
		} 
		else {
			this.typeOfLabour = "inexperienced";
		}
	}
	
	/**
	 * 
	 * @return the transportation costs
	 */
	public double calculateCostOfTransportation() {
		return (TRANSPORTATION_RATE * this.getDistanceOfTransportationInKm());
	}
	
	/**
	 * calculate costs of total invoice
	 */
	@Override
	public double calculateCost() {
		
		double LabourCosts = 0;
		
		switch(this.getHrRateCriteria().toLowerCase()) {
		case "regular":
			LabourCosts = ( (this.getHourlyRate() * this.getNumOfWorkingHrs()) * TAX ) + this.calculateCostOfTransportation();
			break;
		case "overtime":
			LabourCosts = ( (OVERTIME_SURCHARGE * this.getHourlyRate() * this.getNumOfWorkingHrs()) * TAX ) + this.calculateCostOfTransportation();
			break;
		case "holiday":
			LabourCosts = ( (HOLIDAY_SURCHARGE * this.getHourlyRate() * this.getNumOfWorkingHrs()) * TAX ) + this.calculateCostOfTransportation();
			break;
		}
		
		return LabourCosts;
	}
	
	/**
	 * override toString() method
	 * @return the costs broken down
	 */
	@Override
	public String toString() {
		return super.toString() +
			   "\nCriteria of labour: " + this.getHrRateCriteria() +
			   "\nType of labour: " + this.getTypeOfLabour() +
			   "\nCost of Transportation: " + this.calculateCostOfTransportation() +
			   "\nTOTAL: " + this.calculateCost() +
			   "\n";
	}
	
	
	
}
