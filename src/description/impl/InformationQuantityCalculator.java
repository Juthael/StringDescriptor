package description.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import description.IScoreCalculator;
import description.impl.utils.InfoMeasurer;
import fca.core.lattice.ConceptLattice;
import fca.core.lattice.FormalConcept;
import model.orderedSetModel.IOrderedSet;

public class InformationQuantityCalculator implements IScoreCalculator {

	public InformationQuantityCalculator() {
	}

	@Override
	public double calculateScore(IOrderedSet orderedSet, ConceptLattice lattice) {
		double informationQuantity = 0;
		Set<FormalConcept> setOfCoatoms = setSetOfCoatoms(lattice);
		Set<Set<FormalConcept>> setOfInfGeneratorSets = setSetOfInfGeneratorSets(setOfCoatoms, lattice);
		Map<Set<FormalConcept>, InfoMeasurer> infGeneratorSetToInfoMeasure = setInfoMeasureMap(setOfInfGeneratorSets);
		for (Set<FormalConcept> infGenSet : setOfInfGeneratorSets) {
			informationQuantity = informationQuantity + infGeneratorSetToInfoMeasure.get(infGenSet).getInformationMeasure();
		}
		return informationQuantity;
	}
	
	public Set<FormalConcept> setSetOfCoatoms(ConceptLattice lattice) {
		Set<FormalConcept> setOfCoatoms = new HashSet<FormalConcept>();
		FormalConcept topConcept = lattice.getTopConcept();
		setOfCoatoms.addAll(topConcept.getChildren());
		return setOfCoatoms;
	}
	
	public Set<Set<FormalConcept>> setSetOfInfGeneratorSets(Set<FormalConcept> setOfCoatoms, ConceptLattice lattice) {
		Set<Set<FormalConcept>> setOfInfGeneratorSets = new HashSet<Set<FormalConcept>>();
		for (FormalConcept concept : lattice.getConcepts()) {
			if (!setOfCoatoms.contains(concept)) {
				Set<FormalConcept> conceptCoatomUpperBounds = new HashSet<FormalConcept>();
				for (FormalConcept coatom : setOfCoatoms) {
					if (concept.getIntent().isIncluding(coatom.getIntent()))
						conceptCoatomUpperBounds.add(coatom);
				}
				if (!conceptCoatomUpperBounds.isEmpty())
					setOfInfGeneratorSets.add(conceptCoatomUpperBounds);
			}
		}
		return setOfInfGeneratorSets;
	}
	
	public Map<Set<FormalConcept>, InfoMeasurer> setInfoMeasureMap(Set<Set<FormalConcept>> setOfInfGeneratorSets) {
		Map<Set<FormalConcept>, InfoMeasurer> nbOfUpperBoundsMap = new HashMap<Set<FormalConcept>, InfoMeasurer>();
		for (Set<FormalConcept> infGeneratorSet : setOfInfGeneratorSets) {
			nbOfUpperBoundsMap.put(infGeneratorSet, new InfoMeasurer(setOfInfGeneratorSets.size()));
		}
		for (Set<FormalConcept> infGeneratorSet : setOfInfGeneratorSets) {
			for (Set<FormalConcept> mayBeUpperBoundSet : setOfInfGeneratorSets) {
				if (mayBeUpperBoundSet.containsAll(infGeneratorSet)) {
					nbOfUpperBoundsMap.get(infGeneratorSet).incrementNbOfUpperBounds();
				}
			}
		}
		return nbOfUpperBoundsMap;
	}

}
