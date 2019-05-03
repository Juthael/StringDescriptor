package stringDescription.impl;

import java.util.Vector;

import fca.core.lattice.ConceptLattice;
import fca.core.lattice.FormalConcept;
import model.orderedSetModel.IOrderedSet;
import stringDescription.IScoreCalculator;

public class KnowledgeEfficiencyCalculator implements IScoreCalculator {

	public KnowledgeEfficiencyCalculator() {
	}

	@Override
	public double calculateScore(IOrderedSet orderedSet, ConceptLattice lattice) {
		double score;
		int nbOfInfReducibleSupIrreducibleElements = 0;
		int nbOfSupReducibleElements = 0;
		Vector<FormalConcept> listOfConcepts = lattice.getConcepts();
		for (FormalConcept concept : listOfConcepts) {
			boolean thisElementIsInfReducible;
			boolean thisElementIsSupReducible;
			int nbOfChildren = concept.getChildren().size();
			int nbOfParents = concept.getParents().size();
			if (nbOfChildren == 1)
				thisElementIsInfReducible = false;
			else thisElementIsInfReducible = true;
			if (nbOfParents == 1)
				thisElementIsSupReducible = false;
			else thisElementIsSupReducible = true;
			if (thisElementIsSupReducible == true)
				nbOfSupReducibleElements++;
			else if (thisElementIsInfReducible == true)
				nbOfInfReducibleSupIrreducibleElements++; 
		}
		score = log2(nbOfSupReducibleElements) / log2(nbOfInfReducibleSupIrreducibleElements);
		return score;
	}
	
	private double log2(int i) {
		double x = (double) i;
		return (Math.log(x) / Math.log(2));
	}

}
