/**
 * 
 */
package ca.bcit.comp1451.assignment2;

/**
 * @author ANGELA WU
 * @version July 7, 2017 - v1
 */
public class LabourAndMaterial extends Labour implements Transferable {
	
	private static final int MIN_MATERIAL = 0;
	private static final double MATERIAL_PROFIT = 1.15;
	private static final double MATERIAL_TAX = 1.07;
	
	protected static final int VOLUME_TIER = 10;
	protected static final int WEIGHT_TIER = 100;
	
	protected static final double PRICE_VOLUME_OVER_TIER = 2;
	protected static final double PRICE_VOLUME_UNDER_TIER = 1.5;
	protected static final double PRICE_WEIGHT_OVER_TIER = 1.2;
	protected static final double PRICE_WEIGHT_UNDER_TIER = 2;
	
	protected static final double TRANSPORT_TAX = 1.05;
	
	
	private double materialCost;
	private double materialWeight;
	private double materialVolume;
	private int materialTransportionDistanceInKm;
	
	/**
	 * Constructor for objects of class LabourAndMaterial
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
	 */
	public LabourAndMaterial (String projectName, 
							  int numOfWorkingHrs, 
							  double hourlyRate, 
							  int distanceOfTransportationInKm, 
							  String hrRateCriteria, 
							  String typeOfLabour, 
							  double materialCost,
							  double materialWeight,
							  double materialVolume,
							  int materialTransportionDistanceInKm) {
		super(projectName, numOfWorkingHrs, hourlyRate, distanceOfTransportationInKm, hrRateCriteria, typeOfLabour);
		setMaterialCost(materialCost);
		setMaterialWeight(materialWeight);
		setMaterialVolume(materialVolume);
		setMaterialTransportionDistanceInKm(materialTransportionDistanceInKm);
	}

	/**
	 * @return the materialCost
	 */
	public double getMaterialCost() {
		return materialCost;
	}

	/**
	 * @param materialCost the materialCost to set
	 */
	public void setMaterialCost(double materialCost) {
		if(materialCost > MIN_MATERIAL) {
			this.materialCost = materialCost;
		}
	}

	/**
	 * @return the materialWeight
	 */
	public double getMaterialWeight() {
		return materialWeight;
	}

	/**
	 * @param materialWeight the materialWeight to set
	 */
	public void setMaterialWeight(double materialWeight) {
		if(materialWeight > MIN_MATERIAL) {
			this.materialWeight = materialWeight;
		}
	}

	/**
	 * @return the materialVolume
	 */
	public double getMaterialVolume() {
		return materialVolume;
	}

	/**
	 * @param materialVolume the materialVolume to set
	 */
	public void setMaterialVolume(double materialVolume) {
		if(materialVolume > MIN_MATERIAL) {
			this.materialVolume = materialVolume;
		}
	}

	/**
	 * @return the materialTransportionDistanceInKm
	 */
	public int getMaterialTransportionDistanceInKm() {
		return materialTransportionDistanceInKm;
	}

	/**
	 * @param materialTransportionDistanceInKm the materialTransportionDistanceInKm to set
	 */
	public void setMaterialTransportionDistanceInKm(int materialTransportionDistanceInKm) {
		if(materialCost > MIN_MATERIAL) {
			this.materialTransportionDistanceInKm = materialTransportionDistanceInKm;
		}
	}
	
	/**
	 * 
	 * @return total material costs
	 */
	public double calculateMaterialCost() {
		return this.getMaterialCost() * MATERIAL_PROFIT * MATERIAL_TAX;
	}
	
	/**
	 * Calculates material transportation costs
	 */
	public double calculateTransportationFees() {
		double materialTransportCost;
		double materialVolumeCost;
		double materialWeightCost;

		
		if(this.getMaterialVolume() > VOLUME_TIER) {
			materialVolumeCost = this.getMaterialTransportionDistanceInKm() * PRICE_VOLUME_OVER_TIER;
		}
		else {
			materialVolumeCost = this.getMaterialTransportionDistanceInKm() * PRICE_VOLUME_UNDER_TIER;
		}
		
		if (this.getMaterialWeight() > WEIGHT_TIER) {
			materialWeightCost = this.getMaterialTransportionDistanceInKm() * PRICE_WEIGHT_OVER_TIER;	
		}
		else {
			materialWeightCost = this.getMaterialTransportionDistanceInKm() * PRICE_WEIGHT_UNDER_TIER;
		}
		
		materialTransportCost =  (materialVolumeCost + materialWeightCost) * TRANSPORT_TAX;
		
		return materialTransportCost;
	}
	
	/**
	 * Calculates labour + material + transportation fees
	 * @return total costs
	 */
	@Override
	public double calculateCost() {

		return super.calculateCost() + this.calculateMaterialCost() + this.calculateTransportationFees();
	
	}
	
	/**
	 * override toString() method
	 * @return the costs broken down
	 */
	@Override
	public String toString() {
		return super.toString() +
			   "\nMaterial cost: " + this.calculateMaterialCost() +
			   "\nMaterial transportation fee: " + this.calculateTransportationFees() +
			   "\nTOTAL: " + this.calculateCost() +
			   "\n";
	}
	
	
	
}
