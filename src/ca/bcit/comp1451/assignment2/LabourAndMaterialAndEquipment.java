package ca.bcit.comp1451.assignment2;

/**
 * @author ANGELA WU
 * @version July 8, 2017 - v1
 */

public class LabourAndMaterialAndEquipment extends LabourAndMaterial implements Transferable {
	
	private static final int MIN_AMOUNT = 0;
	private static final int EQUIPMENT_RENTAL_TIER = 15;
	private static final double EQUIPMENT_RENTAL_SURCHARGE = 0.05;
	private static final double TAX = 1.05;
	private static final double TRAINING_FEE_SURCHARGE = 1.02;
	
	
	private double equipmentValueInCAD;
	private double numberOfRentalHrs;
	private double equipmentWeightInKg;
	private double equipmentVolumeInCubicFoot;
	
	/**
	 * Constructor for objects of class LabourAndMaterialAndEquipment
	 * @param projectName the project name
	 * @param numOfWorkingHrs the number of working hours needed to complete this project
	 * @param hourlyRate the hourly rate of each worker
	 * @param distanceOfTransportationInKm  the distance needed for transportation in KM
	 * @param hrRateCriteria  the criteria to base the hourly rate on: regular, overtime, or holiday
	 * @param typeOfLabour type of labour is either experienced of inexperienced
	 * @param materialCost cost of material
	 * @param materialWeight  weight of material
	 * @param materialVolume  volume of material
	 * @param materialTransportionDistanceInKm  the distance needed to transport material in KM
	 * @param equipmentValueInCAD  equipment value in Canadian dollars
	 * @param numberOfRentalHrs  number of hours the equipment was rented
	 * @param equipmentWeightInKg 	weight of equipment in KG
	 * @param equipmentVolumeInCubicFoot	volume of equipment in cubic foot
	 */
	public LabourAndMaterialAndEquipment(String projectName, 
							  			 int numOfWorkingHrs, 
							  			 double hourlyRate, 
							  			 int distanceOfTransportationInKm, 
							  			 String hrRateCriteria, 
							  			 String typeOfLabour, 
							  			 double materialCost,
							  			 double materialWeight,
							  			 double materialVolume,
							  			 int materialTransportionDistanceInKm,
							  			 double equipmentValueInCAD,
							  			 double numberOfRentalHrs,
							  			 double equipmentWeightInKg,
							  			 double equipmentVolumeInCubicFoot) {
		
		super(projectName, 
			  numOfWorkingHrs, 
			  hourlyRate, 
			  distanceOfTransportationInKm, 
			  hrRateCriteria, 
			  typeOfLabour, 
			  materialCost,
			  materialWeight,
			  materialVolume,
			  materialTransportionDistanceInKm);
		
		setEquipmentValueInCAD(equipmentValueInCAD);
		setNumberOfRentalHrs(numberOfRentalHrs);
		setEquipmentWeightInKg(equipmentWeightInKg);
		setEquipmentVolumeInCubicFoot(equipmentVolumeInCubicFoot);
	}

	/**
	 * @return the equipmentValueInCAD
	 */
	public double getEquipmentValueInCAD() {
		return equipmentValueInCAD;
	}

	/**
	 * @param equipmentValueInCAD the equipmentValueInCAD to set
	 */
	public void setEquipmentValueInCAD(double equipmentValueInCAD) {
		if(equipmentValueInCAD > MIN_AMOUNT) {
		}
		
		this.equipmentValueInCAD = equipmentValueInCAD;

	}

	/**
	 * @return the numberOfRentalHrs
	 */
	public double getNumberOfRentalHrs() {
		return numberOfRentalHrs;
	}

	/**
	 * @param numberOfRentalHrs the numberOfRentalHrs to set
	 */
	public void setNumberOfRentalHrs(double numberOfRentalHrs) {
		if(numberOfRentalHrs > MIN_AMOUNT) {
			this.numberOfRentalHrs = numberOfRentalHrs;
		}
	}

	/**
	 * @return the equipmentWeightInKg
	 */
	public double getEquipmentWeightInKg() {
		return equipmentWeightInKg;
	}

	/**
	 * @param equipmentWeightInKg the equipmentWeightInKg to set
	 */
	public void setEquipmentWeightInKg(double equipmentWeightInKg) {
		if(equipmentWeightInKg > MIN_AMOUNT) {
		this.equipmentWeightInKg = equipmentWeightInKg;
		}
	}

	/**
	 * @return the equipmentVolumeInCubicFoot
	 */
	public double getEquipmentVolumeInCubicFoot() {
		return equipmentVolumeInCubicFoot;
	}

	/**
	 * @param equipmentVolumeInCubicFoot the equipmentVolumeInCubicFoot to set
	 */
	public void setEquipmentVolumeInCubicFoot(double equipmentVolumeInCubicFoot) {
		if(equipmentVolumeInCubicFoot > MIN_AMOUNT) {
		this.equipmentVolumeInCubicFoot = equipmentVolumeInCubicFoot;
		}
	}
	
	/**
	 * 
	 * @return total rental fees
	 */
	public double calculateRentalFees() {
		double ratePerHr;
			
		if( (this.getEquipmentValueInCAD() * EQUIPMENT_RENTAL_SURCHARGE) >= EQUIPMENT_RENTAL_TIER) {
			ratePerHr = this.getEquipmentValueInCAD() * EQUIPMENT_RENTAL_SURCHARGE;
		}
		else {
			ratePerHr = EQUIPMENT_RENTAL_TIER;
		}
		
		return (ratePerHr * this.getNumberOfRentalHrs()) * TAX; 
	}
	
	/**
	 * 
	 * @return total training fees
	 */
	public double calculateTrainingFees() {
		if(this.getTypeOfLabour().equalsIgnoreCase("experienced")) {
			return 0;
		}	
		
		return this.getEquipmentValueInCAD() * TRAINING_FEE_SURCHARGE * TAX;
	}
	
	/**
	 * Calculates equipment transportation costs
	 * @return total transportation fees
	 */
	@Override
	public double calculateTransportationFees() {
		double equipmentTransportCost;
		double equipmentVolumeCost;
		double equipmentWeightCost;

		
		if(this.getMaterialVolume() > LabourAndMaterial.VOLUME_TIER) {
			equipmentVolumeCost = this.getMaterialTransportionDistanceInKm() * LabourAndMaterial.PRICE_VOLUME_OVER_TIER;
		}
		else {
			equipmentVolumeCost = this.getMaterialTransportionDistanceInKm() * LabourAndMaterial.PRICE_VOLUME_UNDER_TIER;
		}
		
		if (this.getMaterialWeight() > LabourAndMaterial.WEIGHT_TIER) {
			equipmentWeightCost = this.getMaterialTransportionDistanceInKm() * LabourAndMaterial.PRICE_WEIGHT_OVER_TIER;	
		}
		else {
			equipmentWeightCost = this.getMaterialTransportionDistanceInKm() * LabourAndMaterial.PRICE_WEIGHT_UNDER_TIER;
		}
		
		equipmentTransportCost =  (equipmentVolumeCost + equipmentWeightCost) * LabourAndMaterial.TRANSPORT_TAX;
		
		return equipmentTransportCost;
	}
	
	/**
	 * Calculates labour, material, material transportation costs, plus equipment transportation costs
	 * @return total costs
	 */
	@Override
	public double calculateCost() {
		//double labourAndMaterialAndTransportCost = super.calculateCost();
		
		return super.calculateCost() + this.calculateTrainingFees() + this.calculateTransportationFees() + this.calculateRentalFees();
		
	}
	
	/**
	 * override toString() method
	 * @return the costs broken down
	 */
	@Override
	public String toString() {
		return super.toString() +
			   "\nEquipment rental hours :" + this.getNumberOfRentalHrs() +
			   "\nEquipment rental fees: " + this.calculateRentalFees() + 
			   "\nTraining fees: " + this.calculateTrainingFees() +
			   "\nTransportation fees : " + this.calculateTransportationFees() +
			   "\nTOTAL: " + this.calculateCost() +
			   "\n";
	}
	
	
}
