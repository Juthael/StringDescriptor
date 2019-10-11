package launcher.utils.menu.impl;

import description.IDescriptionValuatorBuilder;
import description.impl.DescriptionValuatorBuilder;
import exceptions.StringFormatException;
import launcher.utils.KeyboardInputManager;
import launcher.utils.StringValidityChecker;
import launcher.utils.menu.IMenuStringSubPage;
import launcher.utils.menu.IStringResults;

public abstract class MenuStringSubPage extends MenuSubPage implements IMenuStringSubPage {

	public MenuStringSubPage() {
	}

	@Override
	public void enterNewString() {
		System.out.println(System.lineSeparator() + txt.getZ6SeeResultsB());
		String string = KeyboardInputManager.readString();
		IDescriptionValuatorBuilder descriptionValuatorBuilder = 
				new DescriptionValuatorBuilder().validatedBy(new StringValidityChecker());
		try {
			valuator = descriptionValuatorBuilder.buildDescriptionValuator(string);
			IStringResults stringResults = new StringResults(string);
			stringResults.exec();
		}
		catch (StringFormatException unexpected) {
			System.out.println("The string entered is invalid. Please try again." + System.lineSeparator());
			enterNewString();
		}
		catch (Exception unexpected) {
			System.out.println("An error has occured. Please try again." + System.lineSeparator());
			enterNewString();
		}
	}

}
