package launcher.utils;

import java.util.function.Predicate;

import settings.Settings;

public class DescriptionValidityChecker implements Predicate<String> {

	public DescriptionValidityChecker() {
	}

	public boolean test(String stringToBeChecked) {
		boolean parameterMatchesThePredicate;
		int minLegalCharValue;
		int maxLegalCharValue;
		if (Settings.USE_LOWERCASE_LETTER) {
			minLegalCharValue = 97;
			maxLegalCharValue = 122;
		} else {
			minLegalCharValue = 65;
			maxLegalCharValue = 90;
		}
		if (stringToBeChecked.length() <= Settings.MAX_NB_OF_CHARS_IN_STRING) {
			boolean allValuesAreLegal = true;
			char[] charArray = stringToBeChecked.toCharArray();
			int charIndex = 0;
			while (allValuesAreLegal && charIndex < charArray.length) {
				if (charArray[charIndex] < minLegalCharValue || charArray[charIndex] > maxLegalCharValue)
					allValuesAreLegal = false;
				charIndex++;
			}
			if (allValuesAreLegal == true)
				parameterMatchesThePredicate = true;
			else parameterMatchesThePredicate = false;
		}
		else parameterMatchesThePredicate = false;
		return parameterMatchesThePredicate;
	}

}
