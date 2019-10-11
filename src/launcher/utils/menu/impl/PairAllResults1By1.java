package launcher.utils.menu.impl;

import java.awt.Frame;
import java.util.List;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import fca.gui.lattice.LatticeViewer;
import fca.gui.lattice.element.GraphicalLattice;
import fca.gui.lattice.element.LatticeStructure;
import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IPairAllResults1By1;
import launcher.utils.menu.IPairResults;

public class PairAllResults1By1 extends MenuPairSubPage implements IPairAllResults1By1 {

	private String string1; 
	private String string2; 
	private int resultIndex = -1;
	private List<String> orderedIds;
	
	public PairAllResults1By1(String string1, String string2) {
		this.string1 = string1;
		this.string2 = string2;
		orderedIds = valuator.getOrderedListOfOrderedSetIDs();
	}

	@Override
	public void backToResultPage() {
		IPairResults pairResults = new PairResults(string1, string2);
		pairResults.exec();
	}

	@Override
	public void displayNextResult() {
		getPagePresentation();
	}
	
	@Override
	public void displaySyntacticTree() {
		String iD = valuator.getOrderedListOfOrderedSetIDs().get(resultIndex);
		List<String> syntacticTree = valuator.getOrderedSetIDToListOfPropertiesMapping().get(iD);
		System.out.println("");
		for (String property : syntacticTree) {
			System.out.println(property);
		}
		System.out.println("");
	}

	@Override
	public void displayOrderedSet() {
		String iD = valuator.getOrderedListOfOrderedSetIDs().get(resultIndex);
		List<String> orderedSet = valuator.getOrderedSetIDToListOfMaximalChainsMapping().get(iD);
		System.out.println("");
		for (String maxChain : orderedSet) {
			System.out.println(maxChain);
		}
		System.out.println("");
	}	

	@Override
	public void displayLattice() {
		try {
			String iD = valuator.getOrderedListOfOrderedSetIDs().get(resultIndex);
			BinaryContext context = valuator.getOrderedSetIDToBinaryContextMapping().get(iD);
			ConceptLattice lattice = valuator.getOrderedSetIDToConceptLatticeMapping().get(iD);
			LatticeStructure structure = new LatticeStructure(lattice, context, LatticeStructure.BEST);
			GraphicalLattice graphicalLattice = new GraphicalLattice(lattice, structure);
			LatticeViewer latticeViewer = new LatticeViewer(graphicalLattice);
			latticeViewer.setExtendedState(Frame.MAXIMIZED_BOTH);
			System.out.println("The Hasse diagram has been generated" + System.lineSeparator());
			latticeViewer.setVisible(true);
		}
		catch (Exception unexpected) {
			System.out.println("An error has occured. The diagram can't be generated.");
		}
	}

	@Override
	public void getPagePresentation() {
		resultIndex++;
		if (resultIndex < orderedIds.size()) {
			String iD = orderedIds.get(resultIndex);
			double score = valuator.getOrderedSetIDToScoreMapping().get(iD);
			String verbalDescription = valuator.getOrderedSetIDToVerbalDescriptionMapping().get(iD);
			System.out.println(txt.getnPairOfStringsAllResults1By1(resultIndex+1, score, verbalDescription));
		}
		else {
			System.out.println("No more description available." + System.lineSeparator());
			backToResultPage();
			System.out.println(System.lineSeparator());
		}
	}

	@Override
	public void presentChoice() {
		System.out.println("1 : ".concat(txt.getZ18SeeNextResult()));
		System.out.println("2 : ".concat(txt.getZ12SeeSyntacticTree()));
		System.out.println("3 : ".concat(txt.getZ13SeeOrderedSet()));		
		System.out.println("4 : ".concat(txt.getZ14SeeLattice()));
		System.out.println("5 : ".concat(txt.getZ15BackToResults()));	
		System.out.println("6 : ".concat(txt.getZ21EnterNewPair()));	
		System.out.println("7 : ".concat(txt.getZ7BackToMainMenu()));
	}

	@Override
	public void manageSelection() {
		String entry = KeyboardInputManager.readString();
		switch (entry) {
			case "1" : displayNextResult();
				System.out.println(System.lineSeparator());
				presentChoice();
				manageSelection();
				break;
			case "2" : displaySyntacticTree();	
				System.out.println(System.lineSeparator());
				presentChoice();
				manageSelection();
				break;
			case "3" : displayOrderedSet();	
				System.out.println(System.lineSeparator());
				presentChoice();
				manageSelection();
				break;
			case "4" : displayLattice();
				presentChoice();
				manageSelection();
				break;				
			case "5" : backToResultPage();
				break;
			case "6" : enterNewPairOfStrings();
				break;
			case "7" : goBackToMainMenu();
				break;				
			default : 
				System.out.println("Invalid selection. Please try again. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}

}
