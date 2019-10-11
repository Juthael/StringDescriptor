package launcher.utils.menu.impl;

import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IPairDescription;
import launcher.utils.menu.IPairScoreCalculation;

public class PairDescription extends MenuPairSubPage implements IPairDescription {

	public PairDescription() {
	}
	
	@Override
	public void getPagePresentation() {
		System.out.println(txt.getjPairOfStringsDescription());
	}
	
	@Override
	public void presentChoice() {
		System.out.println("1 : ".concat(txt.getZ24LearnMoreAboutScores()));
		System.out.println("2 : ".concat(txt.getZ3InterpPairOfStrings()));
		System.out.println("3 : ".concat(txt.getZ7BackToMainMenu()));		
	}

	@Override
	public void manageSelection() {
		String entry = KeyboardInputManager.readString();
		switch (entry) {
			case "1" : learnMoreAboutScoreCalculation();
				break;
			case "2" : enterNewPairOfStrings();
				break;
			case "3" : goBackToMainMenu();
				break;
			default : 
				System.out.println("Invalid selection. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}

	@Override
	public void learnMoreAboutScoreCalculation() {
		IPairScoreCalculation scoreCalculation = new PairScoreCalculation();
		scoreCalculation.exec();
	}	

}
