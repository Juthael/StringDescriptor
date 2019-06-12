package launcher.utils.menu;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISyntacticTree;

public interface IPairResults1By1 extends IMenuPairSubPage, IMenuResultSubPage {
	
	void displayNextResult();
	
	void displaySyntacticTree(ISyntacticTree synTree);
	
	void displayOrderedSet(IOrderedSet orderedSet);
	
	void displayLattice(ConceptLattice lattice, BinaryContext context);

}
