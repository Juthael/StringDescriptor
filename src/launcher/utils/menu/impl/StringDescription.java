package launcher.utils.menu.impl;

import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IStringDescription;
import launcher.utils.menu.IStringScoreCalculation;

public class StringDescription extends MenuStringSubPage implements IStringDescription {

	public StringDescription() {
	}
	
	@Override
	public void getPagePresentation() {
		System.out.println(txt.getcSingleStringDescription());	
	}

	@Override
	public void presentChoice() {
		System.out.println("1 : ".concat(txt.getZ24LearnMoreAboutScores()));
		System.out.println("2 : ".concat(txt.getZ6SeeResultsA()));
		System.out.println("3 : ".concat(txt.getZ7BackToMainMenu()));
	}

	@Override
	public void manageSelection() {
		String entry = KeyboardInputManager.readString();
		switch (entry) {
			case "1" : learnMoreAboutScoreCalculation();
				break;
			case "2" : enterNewString();
				break;
			case "3" : goBackToMainMenu();
				break;
			default : 
				System.out.println("Invalid selection. Please try again. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}

	@Override
	public void learnMoreAboutScoreCalculation() {
		IStringScoreCalculation scoreCalculation = new StringScoreCalculation();
		scoreCalculation.exec();
	}	

}
