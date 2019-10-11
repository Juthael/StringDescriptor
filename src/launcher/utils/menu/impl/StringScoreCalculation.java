package launcher.utils.menu.impl;

import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IStringScoreCalculation;

public class StringScoreCalculation extends MenuStringSubPage implements IStringScoreCalculation {

	public StringScoreCalculation() {
	}

	@Override
	public void getPagePresentation() {
		System.out.println(txt.gethSingleStringScoreCalculation());
	}

	@Override
	public void presentChoice() {
		System.out.println("1 : ".concat(txt.getZ10EnterNewString()));
		System.out.println("2 : ".concat(txt.getZ7BackToMainMenu()));
	}

	@Override
	public void manageSelection() {
		String entry = KeyboardInputManager.readString();
		switch (entry) {
			case "1" : enterNewString();
				break;
			case "2" : goBackToMainMenu();
				break;
			default : System.out.println("Invalid selection. Please try again. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}

}
