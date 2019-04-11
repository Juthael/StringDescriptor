package launcher.utils;

import java.util.List;

import descriptionModel.IDescription;
import descriptionModel.IDescriptionBuilder;
import descriptionModel.impl.DescriptionBuilderImpl;
import exceptions.StringFormatException;
import exceptions.VerbalizationException;
import settings.Settings;

public class CmdController {

	private CmdController() {
	}
	
	public static void analyzeStrings() {
		present();
		enterNewString();
	}
	
	private static void present() {
		System.out.println("");
		System.out.println("********** STRING DESCRIPTOR **********");
		System.out.println("");
		System.out.println("This program generates various possible descriptions of the string of characters ");
		System.out.println("provided by the user. ");
		System.out.println("Although most descriptions seem absurd, each one could later prove relevant if inserted ");
		System.out.println("in a particular context.") ;
		System.out.println("A description takes the form of a syntactic tree. This structure is almost exhaustive ");
		System.out.println("but quite abstruse ; therefore, the program offer to translate it into a verbal description, ");
		System.out.println("formulated as a recipe for the production of the provided string.");
	}
	
	private static void enterNewString() {
		String stringToBeDescribed;
		String maxSize = Integer.toString(Settings.MAX_NB_OF_CHARS_IN_STRING);
		String letterCase;
		if (Settings.USE_LOWERCASE_LETTER)
			letterCase = "lowercase";
		else letterCase = "capitalized";
		System.out.println("");
		System.out.println("Letters must be " + letterCase + " and the string cannot contain more than " + maxSize + " characters.");
		System.out.println("Please enter a String : ");
		System.out.println("");
		stringToBeDescribed = DescriptionKeyboardInputManager.readString();
		IDescriptionBuilder builder = new DescriptionBuilderImpl().validatedBy(new DescriptionValidityChecker());
		List<IDescription> setOfDescriptions;
		try {
			setOfDescriptions = builder.buildList(stringToBeDescribed);
			System.out.println("The string analysis has been completed.");
			displayResults(setOfDescriptions);
		}
		catch(Exception unexpected) {
			System.out.println(unexpected.getMessage());
			System.out.println("Please try again");
			System.out.println("");
			enterNewString();
		}
	
	}
	
	private static void displayResults(List<IDescription> listOfDescriptions) {
		System.out.println("");
		System.out.println("How would you like the results to be displayed ?");
		System.out.println("1 - as verbal descriptions.");
		System.out.println("2 - as syntactic trees of properties that can be used for subsequent similarity calculation.");
		System.out.println("3 - both.");
		System.out.println("4 - skip results presentation.");
		System.out.println("Please make your selection : ");
		int displayMode = -1;
		try {
			displayMode = DescriptionKeyboardInputManager.readInt();
		}
		catch(StringFormatException err) {
			System.out.println(err.getMessage());
			System.out.println("");
			displayResults(listOfDescriptions);
		}
		displayNextBatch(1, displayMode, listOfDescriptions);
		chooseWhatToDoNext();
	}
	
	private static void displayNextBatch(int descriptionIndex, int displayMode, List<IDescription> listOfDescriptions) {
		int lastBatchIndexIndex = descriptionIndex + 10;
		if (lastBatchIndexIndex > listOfDescriptions.size())
			lastBatchIndexIndex = listOfDescriptions.size();
		while (descriptionIndex <= lastBatchIndexIndex) {
			IDescription description = listOfDescriptions.get(descriptionIndex - 1);
			try {
				System.out.println("");
				System.out.println("***** Description n°" + descriptionIndex + " *****");
				System.out.println("");
				switch(displayMode) {
					case 1 : String translationInNL1 = description.getDescriptionInNaturalLanguage();
						System.out.println(translationInNL1);
						break;
					case 2 : List<String> listOfProperties2 = description.getCompleteDescription();
						for (String property : listOfProperties2) {
							System.out.println(property);
						}
						System.out.println("");
						break;
					case 3 : String translationInNL3 = description.getDescriptionInNaturalLanguage();
						System.out.println(translationInNL3);
						System.out.println("");
						List<String> listOfProperties3 = description.getCompleteDescription();
						for (String property : listOfProperties3) {
							System.out.println(property);
						}
						System.out.println("");
						break;
					case 4 : 
						chooseWhatToDoNext();
						break;
					default : System.out.println("Selection is invalid. Please try again.");
						displayResults(listOfDescriptions);
				}
			}
			catch (VerbalizationException unexpected) {
				System.out.println("An error has occured.");
				System.out.println(unexpected.getMessage());
				System.out.println(unexpected.getStackTrace());
				displayResults(listOfDescriptions);
			}
			descriptionIndex++;
		}
		if (descriptionIndex < listOfDescriptions.size()) {
			System.out.println("");
			System.out.println("Press enter to continue.");
			DescriptionKeyboardInputManager.readString();
			System.out.println("");
			displayNextBatch(descriptionIndex, displayMode, listOfDescriptions);
		}

		
	}
	
	private static void chooseWhatToDoNext() {
		System.out.println("");
		System.out.println("Would you like to enter a new String ?");
		System.out.println("1-yes");
		System.out.println("2-no");
		System.out.println("Please make your selection :");
		int whatToDoNext = -1;
		try {
			whatToDoNext = DescriptionKeyboardInputManager.readInt();
		}
		catch(StringFormatException err) {
			System.out.println(err.getMessage());
			System.out.println("");
			chooseWhatToDoNext();
		}
		switch(whatToDoNext) {
		case 1 : 
			enterNewString();
			break;
		case 2 :
			System.out.println("Goodbye.");
			System.exit(0);
			break;
		default : System.out.println("Selection is invalid. Please try again.");
			chooseWhatToDoNext();
			break;
		}
	}

}