package description.impl;

import description.IScoreCalculator;
import fca.core.lattice.ConceptLattice;
import model.orderedSetModel.IOrderedSet;

public class CodageEfficiencyCalculator implements IScoreCalculator {

	public CodageEfficiencyCalculator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateScore(IOrderedSet orderedSet, ConceptLattice lattice) {
		InformationQuantityCalculator infoCalc = new InformationQuantityCalculator();
		NoiseQuantityCalculator noisCalc = new NoiseQuantityCalculator();
		double infoQuantity = infoCalc.calculateScore(orderedSet, lattice);
		double relevantInfoQuantity = infoQuantity - noisCalc.measureNoise(orderedSet, lattice);
		return relevantInfoQuantity / infoQuantity;
	}

}
