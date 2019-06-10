package stringDescription.impl.utils;

public class InfoMeasurer {
	
	private int nbOfGeneratorSets;
	private int value = 0;

	public InfoMeasurer(int nbOfGeneratorSets) {
		this.nbOfGeneratorSets = nbOfGeneratorSets;
	}
	
	public void incrementNbOfUpperBounds() {
		value++;
	}
	
	public int getNbOfUpperBounds() {
		return value;
	}
	
	public void reset() {
		value=0;
	}
	
	public double getInformationMeasure() {
		double probability = (double) value / nbOfGeneratorSets;
		double infoMeasure = -log2(probability);
		return infoMeasure;
	}
	
	private double log2(double i) {
		return (Math.log(i) / Math.log(2));
	}

}
