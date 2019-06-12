package launcher.utils.menu;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISyntacticTree;

public interface IStringUniqueResult extends IMenuStringSubPage, IMenuResultSubPage {

	void displayVerbalDescriptionAndScore(String verbalization, double score);
	
	void displaySyntacticTree(ISyntacticTree synTree);
	
	void displayOrderedSet(IOrderedSet orderedSet);
	
	void displayLattice(ConceptLattice lattice, BinaryContext context);
	
}
