package launcher.utils.menu.impl;

import description.IDescriptionValuatorBuilder;
import description.impl.DescriptionValuatorBuilder;
import exceptions.StringFormatException;
import launcher.utils.KeyboardInputManager;
import launcher.utils.StringValidityChecker;
import launcher.utils.menu.IMenuPairSubPage;
import launcher.utils.menu.IPairResults;

public abstract class MenuPairSubPage extends MenuSubPage implements IMenuPairSubPage {

	public MenuPairSubPage() {
	}	

	@Override
	public void enterNewPairOfStrings() {
		System.out.println(System.lineSeparator() + txt.getZ20EnterFirstString());
		String string1 = KeyboardInputManager.readString();
		System.out.println(System.lineSeparator() + txt.getZ26EnterSecondString());
		String string2 = KeyboardInputManager.readString();
		IDescriptionValuatorBuilder descriptionValuatorBuilder = 
				new DescriptionValuatorBuilder().validatedBy(new StringValidityChecker());
		try {
			valuator = descriptionValuatorBuilder.buildDescriptionValuator(string1, string2);
			IPairResults pairResults = new PairResults(string1, string2);
			pairResults.exec();
		}
		catch (StringFormatException unexpected) {
			System.out.println("The pair of strings entered is invalid. Please try again." + System.lineSeparator());
			enterNewPairOfStrings();
		}
		catch (Exception unexpected) {
			System.out.println("An error has occured. Please try again." + System.lineSeparator());
			//HERE
			unexpected.printStackTrace();
			enterNewPairOfStrings();
		}
	}

}
