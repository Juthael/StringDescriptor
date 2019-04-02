package dao.tools;

import exceptions.DAOException;
import settings.DescGenSettings;

public class DAOStringValidityCheckerV1 {

	public DAOStringValidityCheckerV1() {
	}
	
	public static void checkStringValidity(String stringToBeChecked) throws DAOException {
		int minLegalCharValue;
		int maxLegalCharValue;
		if (DescGenSettings.USE_LOWERCASE_LETTER) {
			minLegalCharValue = 97;
			maxLegalCharValue = 122;
		}
		else {
			minLegalCharValue = 65;
			maxLegalCharValue = 90;
		}
		if (stringToBeChecked.length() <= DescGenSettings.MAX_NB_OF_CHARS_IN_STRING) {
			boolean allValuesAreLegal = true;
			char[] charArray = stringToBeChecked.toCharArray();
			int charIndex = 0;
			while (allValuesAreLegal && charIndex < charArray.length) {
				if (charArray[charIndex] < minLegalCharValue || charArray[charIndex] > maxLegalCharValue)
					allValuesAreLegal = false;
				charIndex++;
			}
			if (allValuesAreLegal == false)
				throw new DAOException("String is invalid.");
		}
	}

}
