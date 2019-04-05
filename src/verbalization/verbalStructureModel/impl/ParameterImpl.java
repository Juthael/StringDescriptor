package verbalization.verbalStructureModel.impl;

import java.util.List;

import exceptions.VerbalizationException;
import settings.Settings;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.verbalStructureModel.IParameter;

public class ParameterImpl implements IParameter {
	
	private String verbalParameter;

	public ParameterImpl(ITransformationCodeGetter transformationCodeGetter) throws VerbalizationException {
		verbalParameter = setVerbalParameter(transformationCodeGetter);
	}

	@Override
	public String getVerbalParameter() {
		return verbalParameter;
	}
	
	private String setVerbalParameter(ITransformationCodeGetter transformationCodeGetter) 
			throws VerbalizationException {
		String verbalParameter;
		String introducerString = setIntroducerString(transformationCodeGetter);
		String valueString = setValueString(transformationCodeGetter);
		verbalParameter = introducerString.concat(valueString);
		return verbalParameter;
	}
	
	private boolean checkIfThereIsALetterDimension(ITransformationCodeGetter transformationCodeGetter) {
		boolean thereIsALetterDimension = false;
		List<String> listOfPredicateCodes = transformationCodeGetter.getListOfPredicateCodes();
		for (String predicateCode : listOfPredicateCodes) {
			if (predicateCode.contains("LetterEnumerate") || predicateCode.contains("WriteLetter"))
				thereIsALetterDimension = true;
		}
		return thereIsALetterDimension;
	}
	
	private String setValueString(ITransformationCodeGetter transformationCodeGetter) throws VerbalizationException {
		String valueString;
		boolean thereIsALetterDimension = checkIfThereIsALetterDimension(transformationCodeGetter);
		String firstPredicateCode = transformationCodeGetter.getListOfPredicateCodes().get(0);
		if (firstPredicateCode.contains("Equals") || firstPredicateCode.equals("oneLetterEnumerate") || 
				firstPredicateCode.equals("oneSizeEnumerate"))
			valueString = "";
		else if (thereIsALetterDimension == true) {
			if (transformationCodeGetter.getListOfPredicateCodes().size() == 1)
				valueString = getLetterFormatValue(transformationCodeGetter.getParameter());
			else valueString = getDoubleFormatValue(transformationCodeGetter.getParameter());
		}
		else { 
			valueString = transformationCodeGetter.getParameter();
			if (valueString.startsWith("-") && !valueString.contains(",")) {
				int value = Integer.parseInt(valueString);
				value = Math.abs(value);
				valueString = Integer.toString(value);
			}
		}
		valueString = removeFirstValueIfIrrelevant(transformationCodeGetter, valueString);
		valueString = putAnAndToItIfRelevant(valueString);
		return valueString;
	}
	
	private String getDoubleFormatValue(String valueString) {
		String doubleFormatValue;
		StringBuilder sB = new StringBuilder();
		sB.append(valueString);
		sB.append("/");
		sB.append(getLetterFormatValue(valueString));
		doubleFormatValue = sB.toString();
		return doubleFormatValue;
	}
	
	private String getLetterFormatValue(String valueString) {
		String letterValue;
		int aMinusOne;
		if (Settings.USE_LOWERCASE_LETTER)
			aMinusOne = 96;
		else aMinusOne = 64;
		StringBuilder sB = new StringBuilder();
		String[] setOfValuesStringArray = valueString.split(Settings.SECOND_DEGREE_ENUMERATION_SEPARATOR);
		for (int j=0 ; j<setOfValuesStringArray.length ; j++) {
			String setOfValues = setOfValuesStringArray[j];
			String[] valueStringArray = setOfValues.split(",");
			for (int i=0 ; i<valueStringArray.length ; i++) {
				String currentValue = valueStringArray[i];
				int asciiValue = Integer.parseInt(currentValue) + aMinusOne;
				sB.append((char) asciiValue);
				if (i < valueStringArray.length - 1)
					sB.append(",");
			}
			if (j < setOfValuesStringArray.length - 1)
				sB.append(Settings.SECOND_DEGREE_ENUMERATION_SEPARATOR);
		}
		letterValue = sB.toString();
		return letterValue;
	}
	
	private String setIntroducerString(ITransformationCodeGetter transformationCodeGetter) 
			throws VerbalizationException {
		String introducerString;
		String firstPredicateCode = transformationCodeGetter.getListOfPredicateCodes().get(0);
		switch (firstPredicateCode) {
			case "oneLetterDecrease" :
			case "oneLetterIncrease" :
				introducerString = "of ";
				break;
			case "oneLetterEquals" :
			case "oneLetterEnumerate" :
			case "oneSizeEnumerate" :
			case "twoLetterEquals" :
			case "twoSizeEquals" :
			case "twoIncrementEquals" :
			case "twoSubLetterEquals" :
			case "twoSubSizeEquals" :
			case "twoSubIncrementEquals" :
			case "twoSubSubLetterEquals" :
			case "twoSubSubSizeEquals" :
			case "twoSubSubIncrementEquals" :
			case "twoLetterEnumerate" :
			case "twoSizeEnumerate" :
			case "twoIncrementEnumerate" :
			case "twoSubLetterEnumerate" :
			case "twoSubSizeEnumerate" :
			case "twoSubIncrementEnumerate" :
			case "twoSubSubLetterEnumerate" :
			case "twoSubSubSizeEnumerate" :
			case "twoSubSubIncrementEnumerate" :
			case "manyLetterEquals" :
			case "manySizeEquals" :
			case "manyIncrementEquals" :
			case "manySubLetterEquals" :
			case "manySubSizeEquals" :
			case "manySubIncrementEquals" :
			case "manySubSubLetterEquals" :
			case "manySubSubSizeEquals" :
			case "manySubSubIncrementEquals" :
			case "twoEnumerationEquals" :
			case "manyEnumerationEquals" :	
			case "manyLetterEnumerate" :
			case "WriteLetterEffector" :
			case "twoEnumerationEnumerate" :
			case "manyEnumerationEnumerate" :
				introducerString = "";
				break;
			case "twoLetterDecrease" :
			case "twoSizeDecrease" :
			case "twoIncrementDecrease" :
			case "twoSubLetterDecrease" :
			case "twoSubSizeDecrease" :
			case "twoSubIncrementDecrease" :
			case "twoSubSubLetterDecrease" :
			case "twoSubSubSizeDecrease" :
			case "twoSubSubIncrementDecrease" :
			case "twoLetterIncrease" :
			case "twoSizeIncrease" :
			case "twoIncrementIncrease" :
			case "twoSubLetterIncrease" :
			case "twoSubSizeIncrease" :
			case "twoSubIncrementIncrease" :
			case "twoSubSubLetterIncrease" :
			case "twoSubSubSizeIncrease" :
			case "twoSubSubIncrementIncrease" :
			case "manyLetterDecrease" :
			case "manySizeDecrease" :
			case "manyIncrementDecrease" :
			case "manySubLetterDecrease" :
			case "manySubSizeDecrease" :
			case "manySubIncrementDecrease" :
			case "manySubSubLetterDecrease" :
			case "manySubSubSizeDecrease" :
			case "manySubSubIncrementDecrease" :
			case "manyLetterIncrease" :
			case "manySizeIncrease" :
			case "manyIncrementIncrease" :
			case "manySubLetterIncrease" :
			case "manySubSizeIncrease" :
			case "manySubIncrementIncrease" :
			case "manySubSubLetterIncrease" :
			case "manySubSubSizeIncrease" :
			case "manySubSubIncrementIncrease" :
			case "twoEnumerationDecrease" :
			case "twoEnumerationIncrease" :
			case "manyEnumerationDecrease" :
			case "manyEnumerationIncrease" :				
				introducerString = "by ";
				break;
			case "manySizeEnumerate" :
			case "manyIncrementEnumerate" :
			case "manySubLetterEnumerate" :
			case "manySubSizeEnumerate" :
			case "manySubIncrementEnumerate" :
			case "manySubSubLetterEnumerate" :
			case "manySubSubSizeEnumerate" :
			case "manySubSubIncrementEnumerate" :
				introducerString = "the values ";
				break;
			default :
				throw new VerbalizationException("Parameter.getIntroducerString() : the parameter "
						+ firstPredicateCode + "can't be handled.");
		}
		return introducerString;
	}
	
	private String removeFirstValueIfIrrelevant(ITransformationCodeGetter transformationCodeGetter, 
			String valueStringParameter) throws VerbalizationException {
		String valueString;
		String firstPredicateCode = transformationCodeGetter.getListOfPredicateCodes().get(0);
		switch (firstPredicateCode) {
			case "twoLetterEnumerate" :
			case "twoSizeEnumerate" :
			case "twoIncrementEnumerate" :
			case "twoSubLetterEnumerate" :
			case "twoSubSizeEnumerate" :
			case "twoSubIncrementEnumerate" :
			case "twoSubSubLetterEnumerate" :
			case "twoSubSubSizeEnumerate" :
			case "twoSubSubIncrementEnumerate" :
			case "manyLetterEnumerate" :
			case "manySizeEnumerate" :
			case "manyIncrementEnumerate" :
			case "manySubLetterEnumerate" :
			case "manySubSizeEnumerate" :
			case "manySubIncrementEnumerate" :
			case "manySubSubLetterEnumerate" :
			case "manySubSubSizeEnumerate" :
			case "manySubSubIncrementEnumerate" :
			case "twoEnumerationEnumerate" :
			case "manyEnumerationEnumerate" :
				String separator = "";
				if (valueStringParameter.contains(Settings.SECOND_DEGREE_ENUMERATION_SEPARATOR))
					separator = Settings.SECOND_DEGREE_ENUMERATION_SEPARATOR;
				else if (valueStringParameter.contains(","))
					separator = ",";
				if (!separator.isEmpty()) {
					int firstIndexOfSeparator = 
							valueStringParameter.indexOf(separator);
					valueString = valueStringParameter.substring(firstIndexOfSeparator + 1);
				}
				else valueString = valueStringParameter;
				break;
			default :
				valueString = valueStringParameter;
		}
		return valueString;
	}
	
	private String putAnAndToItIfRelevant(String valueStringParameter) {
		String valueString;
		if (!valueStringParameter.contains(Settings.SECOND_DEGREE_ENUMERATION_SEPARATOR) && 
				valueStringParameter.contains(",")) {
			int lastCommaIndex = valueStringParameter.lastIndexOf(",");
			String valueStringFirstPart = valueStringParameter.substring(0, lastCommaIndex);
			String valueStringSecondPart = " and ";
			String valueStringThirdPart = valueStringParameter.substring(lastCommaIndex+1);
			StringBuilder sB = new StringBuilder();
			sB.append(valueStringFirstPart);
			sB.append(valueStringSecondPart);
			sB.append(valueStringThirdPart);
			valueString = sB.toString();
		}
		else valueString = valueStringParameter;
		return valueString;
	}

}
