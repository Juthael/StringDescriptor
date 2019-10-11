package launcher.utils.menu.impl;

import java.util.List;

import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IPairAllResults;
import launcher.utils.menu.IPairAllResults1By1;
import launcher.utils.menu.IPairResults;

public class PairAllResults extends MenuPairSubPage implements IPairAllResults {

	String string1;
	String string2;
	List<String> listOfOrderedIDs;
	
	public PairAllResults(String string1, String string2) {
		this.string1 = string1;
		this.string2 = string2;
		listOfOrderedIDs = valuator.getOrderedListOfOrderedSetIDs();
	}
	
	@Override
	public void getPagePresentation() {
		System.out.println(txt.getlPairOfStringsUniqueResult(string1, string2));
	}
	
	@Override
	public void presentChoice() {	
		System.out.println("1 : ".concat(txt.getZ16SeeAllVerbalDescriptions()));
		System.out.println("2 : ".concat(txt.getZ17SeeResults1By1()));
		System.out.println("3 : ".concat(txt.getZ15BackToResults()));	
		System.out.println("4 : ".concat(txt.getZ21EnterNewPair()));	
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
				break ;
			case "2" : seeAllDescriptions1by1();		
				break ;
			case "3" : backToResultPage();
				break ;
			case "4" : enterNewPairOfStrings();
				break ;
			case "5" : goBackToMainMenu();
				break;
			default : System.out.println("Invalid selection. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}		

	@Override
	public void backToResultPage() {
		IPairResults pairResults = new PairResults(string1, string2);
		pairResults.exec();
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
		IPairAllResults1By1 results1By1 = new PairAllResults1By1(string1, string2);
		results1By1.exec();
	}

}
