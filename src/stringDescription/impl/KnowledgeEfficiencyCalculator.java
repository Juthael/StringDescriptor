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
		double nbOfInfReducibleElements = 0;
		double nbOfSupReducibleElements = 0;
		Vector<FormalConcept> listOfConcepts = lattice.getConcepts();
		for (FormalConcept concept : listOfConcepts) {
			boolean thisElementIsInfReducible;
			boolean thisElementIsSupReducible;
			int nbOfChildren = concept.getChildren().size();
			int nbOfParents = concept.getParents().size();
			if (nbOfParents == 1)
				thisElementIsInfReducible = false;
			else thisElementIsInfReducible = true;
			if (nbOfChildren == 1)
				thisElementIsSupReducible = false;
			else thisElementIsSupReducible = true;
			if (thisElementIsSupReducible == true)
				nbOfSupReducibleElements++;
			if (thisElementIsInfReducible == true)
				nbOfInfReducibleElements++; 
		}
		score = nbOfSupReducibleElements / nbOfInfReducibleElements;
		/*
		System.out.print("Sup-reducibles : ");
		System.out.println(nbOfSupReducibleElements);
		System.out.print("Inf-Reducible : " );
		System.out.println(nbOfInfReducibleElements);
		System.out.print("Score : ");
		System.out.println(score); */
		return score;
	}
	/*
	private double log2(int i) {
		double x = (double) i;
		return (Math.log(x) / Math.log(2));
	} */

}
