package launcher.utils.menu.impl;

import launcher.utils.KeyboardInputManager;
import launcher.utils.menu.IMainMenu;
import launcher.utils.menu.IPairDescription;
import launcher.utils.menu.IStringDescription;

public class MainMenu extends MenuPage implements IMainMenu {

	public MainMenu() {
	}
	
	@Override
	public void getPagePresentation() {
		System.out.println(txt.getbMainMenu());
	}

	@Override
	public void goToSingleStringDescription() {
		IStringDescription stringDescription = new StringDescription();
		stringDescription.exec();
	}

	@Override
	public void goToPairOfStringsDescription() {
		IPairDescription pairDescription = new PairDescription();
		pairDescription.exec();
	}

	@Override
	public void exit() {
		System.out.println(System.lineSeparator() + "Goodbye.");
		System.exit(0);
	}
	
	@Override
	public void presentChoice() {
		System.out.println("1 : ".concat(txt.getZ2InterpString()));
		System.out.println("2 : ".concat(txt.getZ3InterpPairOfStrings()));
		System.out.println("3 : ".concat(txt.getZ4Exit()));
	}
	
	@Override
	public void manageSelection() {
		String entry = KeyboardInputManager.readString();
		switch (entry) {
			case "1" :  goToSingleStringDescription();
				break;
			case "2" : goToPairOfStringsDescription();
				break;
			case "3" : exit();
				break;
			default : 
				System.out.println("Invalid selection. Please try again. Would you like to : ");
				presentChoice();
				manageSelection();
				break;
		}
	}

}
