package launcher.utils.menu.impl;

import java.awt.Frame;
import java.util.List;

import fca.core.context.binary.BinaryContext;
import fca.core.lattice.ConceptLattice;
import fca.gui.lattice.LatticeViewer;
import fca.gui.lattice.element.GraphicalLattice;
import fca.gui.lattice.element.LatticeStructure;
import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IStringBestResult;
import launcher.utils.menu.IStringResults;

public class StringBestResult extends MenuStringSubPage implements IStringBestResult {

	String string;
	
	public StringBestResult(String string) {
		this.string = string;
	}

	@Override
	public void backToResultPage() {
		IStringResults stringResults = new StringResults(string);
		stringResults.exec();
	}

	@Override
	public void displayVerbalDescription() {
		System.out.println(valuator.getBestDescriptionVerbalDescription());

	}

	@Override
	public void displaySyntacticTree() {
		List<String> listOfProperties = valuator.getBestDescriptionListOfProperties();
		for (String property : listOfProperties)
			System.out.println(property);
	}

	@Override
	public void displayOrderedSet() {
		List<String> listOfMaxChains = valuator.getBestDescriptionListOfMaximalChains();
		for (String maxChain : listOfMaxChains)
			System.out.println(maxChain);
	}

	@Override
	public void displayLattice() {
		try {
			String iD = valuator.getBestDescriptionOrderedSetID();
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
		System.out.println(txt.geteSingleStringUniqueResult(string));
	}

	@Override
	public void presentChoice() {
		System.out.println("1 : ".concat(txt.getZ11SeeVerbalDescription()));
		System.out.println("2 : ".concat(txt.getZ12SeeSyntacticTree()));
		System.out.println("3 : ".concat(txt.getZ13SeeOrderedSet()));	
		System.out.println("4 : ".concat(txt.getZ14SeeLattice()));	
		System.out.println("5 : ".concat(txt.getZ15BackToResults()));	
		System.out.println("6 : ".concat(txt.getZ10EnterNewString()));	
		System.out.println("7 : ".concat(txt.getZ7BackToMainMenu()));
	}

	@Override
	public void manageSelection() {
		String entry = KeyboardInputManager.readString();
		switch (entry) {
			case "1" : displayVerbalDescription();
				System.out.println(System.lineSeparator());
				presentChoice();
				manageSelection();
				break;
			case "2" : displaySyntacticTree();
				System.out.println(System.lineSeparator());
				presentChoice();
				manageSelection();
				break;
			case "3" :  displayOrderedSet();
				System.out.println(System.lineSeparator());
				presentChoice();
				manageSelection();
				break;
			case "4" : displayLattice();
				System.out.println(System.lineSeparator());
				presentChoice();
				manageSelection();		
				break;
			case "5" : backToResultPage();
				break;
			case "6" : enterNewString();
				break;
			case "7" : goBackToMainMenu();
				break;				
			default :  System.out.println("Invalid selection. Please try again. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}

}
