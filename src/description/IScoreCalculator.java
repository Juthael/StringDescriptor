package description;

import fca.core.lattice.ConceptLattice;
import model.orderedSetModel.IOrderedSet;

public interface IScoreCalculator {
	
	abstract double calculateScore(IOrderedSet orderedSet, ConceptLattice lattice);

}
