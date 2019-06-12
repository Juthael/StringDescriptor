package launcher.utils.menu;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISyntacticTree;

public interface IPairUniqueResult extends IMenuPairSubPage, IMenuResultSubPage {
	
	void displayVerbalDescriptionAndScore(String verbalization1, String verbalization2, double score);
	
	void displaySyntacticTree(ISyntacticTree synTree);
	
	void displayOrderedSet(IOrderedSet orderedSet);
	
	void displayLattice(ConceptLattice lattice, BinaryContext context);

}
