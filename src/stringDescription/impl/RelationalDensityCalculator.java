package stringDescription.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import fca.core.lattice.ConceptLattice;
import fca.core.lattice.FormalConcept;
import model.orderedSetModel.IOrderedSet;
import stringDescription.IScoreCalculator;

public class RelationalDensityCalculator implements IScoreCalculator {

	public RelationalDensityCalculator() {
	}

	@Override
	public double calculateScore(IOrderedSet orderedSet, ConceptLattice lattice) {
		double score;
		Set<String> setOfCodingAttributesIDs = new HashSet<String>(orderedSet.getListOfCodingComponentsIDs());
		Set<IOrderedSet> lowerSet = orderedSet.getLowerSet();
		for (IOrderedSet subSet : lowerSet) {
			if (subSet.getIsCodingElement() == true)
				setOfCodingAttributesIDs.add(subSet.getElementID());
		}
		Vector<FormalConcept> listOfConcepts = lattice.getConcepts();
		int nbOfInfReducibleConcepts = 0;
		int nbOfInfReducibleConceptsWithCodingElementInExtent = 0;		
		for (FormalConcept concept : listOfConcepts) {
			if (concept.getParents().size() != 1) {
				nbOfInfReducibleConcepts++;
				Set<String> setOfCodingAttributesIDsClone = new HashSet<String>();
				setOfCodingAttributesIDsClone.addAll(setOfCodingAttributesIDs);
				if (setOfCodingAttributesIDsClone.removeAll(concept.getExtent()) == true)
					nbOfInfReducibleConceptsWithCodingElementInExtent++;
			}
		}
		score = log2(nbOfInfReducibleConceptsWithCodingElementInExtent) /log2(nbOfInfReducibleConcepts);
		return score;
	}
	
	private double log2(int i) {
		double x = (double) i;
		return (Math.log(x) / Math.log(2));
	}	

}
