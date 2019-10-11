package launcher.utils.menu.impl;

import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IPairAllResults;
import launcher.utils.menu.IPairBestResult;
import launcher.utils.menu.IPairResults;

public class PairResults extends MenuPairSubPage implements IPairResults {

	String string1;
	String string2;
	
	public PairResults(String string1, String string2) {
		this.string1 = string1;
		this.string2 = string2;
	}

	@Override
	public void getPagePresentation() {
		System.out.println(txt.getkPairOfStringsResults(string1, string2));

	}

	@Override
	public void presentChoice() {
		System.out.println("1 : ".concat(txt.getZ8SeeBestDescription()));
		System.out.println("2 : ".concat(txt.getZ9SeeAllDescriptions()));
		System.out.println("3 : ".concat(txt.getZ21EnterNewPair()));	
		System.out.println("4 : ".concat(txt.getZ7BackToMainMenu()));
	}

	@Override
	public void manageSelection() {
		String entry = KeyboardInputManager.readString();
		switch (entry) {
			case "1" : seeBestDescription();
				break;
			case "2" : seeAllDescriptions();
				break;
			case "3" : enterNewPairOfStrings();
				break;
			case "4" : goBackToMainMenu();
				break;
			default : System.out.println("Invalid selection. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}

	@Override
	public void seeBestDescription() {
		IPairBestResult pairBestResult = new PairBestResult(string1, string2);
		pairBestResult.exec();
	}

	@Override
	public void seeAllDescriptions() {
		IPairAllResults pairAllResults = new PairAllResults(string1, string2);
		pairAllResults.exec();
	}

}
