package launcher.utils.menu.impl;

import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IStringAllResults;
import launcher.utils.menu.IStringBestResult;
import launcher.utils.menu.IStringResults;

public class StringResults extends MenuStringSubPage implements IStringResults {

	String string;
	
	public StringResults(String string) {
		this.string = string;
	}

	@Override
	public void seeBestDescription() {
		IStringBestResult stringBestResult = new StringBestResult(string);
		stringBestResult.exec();
	}

	@Override
	public void seeAllDescriptions() {
		IStringAllResults stringAllResults = new StringAllResults(string);
		stringAllResults.exec();
	}

	@Override
	public void getPagePresentation() {
		System.out.println(txt.getdSingleStringResults(string));

	}

	@Override
	public void presentChoice() {
		System.out.println("1 : ".concat(txt.getZ8SeeBestDescription()));
		System.out.println("2 : ".concat(txt.getZ9SeeAllDescriptions()));
		System.out.println("3 : ".concat(txt.getZ10EnterNewString()));	
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
			case "3" : enterNewString();
				break;
			case "4" : goBackToMainMenu();
				break;				
			default : System.out.println("Invalid selection. Please try again. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}

}
