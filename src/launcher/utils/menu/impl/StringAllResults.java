package launcher.utils.menu.impl;

import java.util.List;

import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IStringAllResults;
import launcher.utils.menu.IStringAllResults1By1;
import launcher.utils.menu.IStringResults;

public class StringAllResults extends MenuStringSubPage implements IStringAllResults {

	String string;
	List<String> listOfOrderedIDs;
	
	public StringAllResults(String string) {
		this.string = string;
		listOfOrderedIDs = valuator.getOrderedListOfOrderedSetIDs();
	}

	@Override
	public void backToResultPage() {
		IStringResults stringResults = new StringResults(string);
		stringResults.exec();
	}

	@Override
	public void seeAllVerbalDescriptions() {
		int index = 1;
		for (String iD : listOfOrderedIDs) {
			double score = valuator.getOrderedSetIDToScoreMapping().get(iD);
			System.out.println("DESCRIPTION N°" + index + ". SCORE : " + Double.toString(score) + System.lineSeparator());
			System.out.println(valuator.getOrderedSetIDToVerbalDescriptionMapping().get(iD));
			index ++;
		}
	}

	@Override
	public void seeAllDescriptions1by1() {
		IStringAllResults1By1 results1By1 = new StringAllResults1By1(string);
		results1By1.exec();
	}

	@Override
	public void getPagePresentation() {
		System.out.println(txt.getfSingleStringAllResults(string));

	}

	@Override
	public void presentChoice() {
		System.out.println("1 : ".concat(txt.getZ16SeeAllVerbalDescriptions()));
		System.out.println("2 : ".concat(txt.getZ17SeeResults1By1()));
		System.out.println("3 : ".concat(txt.getZ15BackToResults()));	
		System.out.println("4 : ".concat(txt.getZ10EnterNewString()));	
		System.out.println("5 : ".concat(txt.getZ7BackToMainMenu()));
	}

	@Override
	public void manageSelection() {
		String entry = KeyboardInputManager.readString();
		switch (entry) {
			case "1" : seeAllVerbalDescriptions();
				System.out.println(System.lineSeparator());	
				presentChoice();
				manageSelection();
				break;
			case "2" : seeAllDescriptions1by1();
				break;
			case "3" : backToResultPage();
				break;
			case "4" : enterNewString();
				break;
			case "5" : goBackToMainMenu();
				break;					
			default : System.out.println("Invalid selection. Please try again. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}

}
