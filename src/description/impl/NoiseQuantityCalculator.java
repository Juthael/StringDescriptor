package description.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import description.IScoreCalculator;
import fca.core.lattice.ConceptLattice;
import fca.core.lattice.FormalConcept;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import settings.Settings;

public class NoiseQuantityCalculator implements IScoreCalculator {

	public NoiseQuantityCalculator() {
	}

	@Override
	public double calculateScore(IOrderedSet orderedSet, ConceptLattice lattice) {
		List<String> contextComponentNames = new ArrayList<String>();
		List<IElement> listOfComponents = orderedSet.getListOfComponents();
		for (IElement element : listOfComponents) {
			IOrderedSet oS = (IOrderedSet) element;
			if (!oS.getElementID().equals(Settings.TREE_TOP_ELEMENT_GENERIC_NAME))
				contextComponentNames.add(oS.getElementID());
		}
		InformationQuantityCalculator infoCalc = new InformationQuantityCalculator();
		Set<Set<FormalConcept>> setOfInfGeneratorSets = infoCalc.setSetOfInfGeneratorSets(lattice);
		Set<Set<FormalConcept>> noiseInfGenSets = new HashSet<Set<FormalConcept>>();
		for (Set<FormalConcept> infGenSet : setOfInfGeneratorSets) {
			boolean allConceptsHaveAllCompInExtent = true;
			if (allConceptsHaveAllCompInExtent == true) {
				for (FormalConcept concept : infGenSet) {
					boolean thisConceptHasAllCompInExtent = concept.getExtent().containsAll(contextComponentNames);
					if (thisConceptHasAllCompInExtent == false)
						allConceptsHaveAllCompInExtent = false;
				}
			}
			if (!allConceptsHaveAllCompInExtent)
				noiseInfGenSets.add(infGenSet);
		}
		return infoCalc.measureInfo(noiseInfGenSets);
	}
	
	public double measureNoise(IOrderedSet orderedSet, ConceptLattice lattice) {
		return calculateScore(orderedSet, lattice);
	}
}
