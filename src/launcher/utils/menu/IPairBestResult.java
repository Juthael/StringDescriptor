package launcher.utils.menu;

public interface IPairBestResult extends IMenuPairSubPage, IMenuResultSubPage {
	
	void displayVerbalDescription();
	
	void displaySyntacticTree();
	
	void displayOrderedSet();
	
	void displayLattice();
	
}
