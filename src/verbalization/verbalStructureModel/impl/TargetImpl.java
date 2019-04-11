package verbalization.verbalStructureModel.impl;

import java.util.List;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.verbalStructureModel.ITarget;

public class TargetImpl implements ITarget {

	private String verbalTarget;
	
	public TargetImpl(ITransformationCodeGetter transformationCodeGetter) throws VerbalizationException {
		verbalTarget = setVerbalTarget(transformationCodeGetter);
	}
	
	@Override
	public String getVerbalTarget() {
		return verbalTarget;
	}
	
	private String setVerbalTarget(ITransformationCodeGetter transformationCodeGetter) 
			throws VerbalizationException {
		String verbalTarget;
		List<String> listOfPredicateCodes = transformationCodeGetter.getListOfPredicateCodes();
		StringBuilder sB = new StringBuilder();
		for (int i=0 ; i<listOfPredicateCodes.size() ; i++) {
			String predicateElementString = getTargetElement(listOfPredicateCodes.get(i));
			sB.append(predicateElementString);
			if (i < listOfPredicateCodes.size() - 2)
				sB.append(", ");
			else if (i == listOfPredicateCodes.size() - 2)
				sB.append("and ");
		}
		verbalTarget = sB.toString();
		return verbalTarget;
	}
	
	private String getTargetElement(String targetCode) throws VerbalizationException {
		String targetElement;
		switch (targetCode) {
			case "oneLetterDecrease" :
				targetElement = "an assumed letter decrement ";
				break;
			case "oneLetterIncrease" :
				targetElement = "an assumed letter increment ";
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
			case "WriteLetterEffector" :
			case "twoSymmetryReverse" :
				targetElement = "";
				break;
			case "twoLetterDecrease" :
			case "twoLetterIncrease" :
			case "manyLetterDecrease" :
			case "manyLetterIncrease" :
				targetElement = "the letter value ";
				break;
			case "twoSizeDecrease" :
			case "twoSizeIncrease" :
			case "manySizeDecrease" :
			case "manySizeIncrease" :
				targetElement = "the size ";
				break;
			case "twoIncrementDecrease" :
			case "twoIncrementIncrease" : 
			case "manyIncrementDecrease" :
			case "manyIncrementIncrease" : 
				targetElement = "the increment ";
				break;
			case "twoSubLetterDecrease" :
			case "twoSubLetterIncrease" :
			case "manySubLetterDecrease" :
			case "manySubLetterIncrease" :
				targetElement = "the value of the initial letter ";
				break;
			case "twoSubSizeDecrease" :
			case "twoSubSizeIncrease" :
				targetElement = "the initial group size ";
				break;
			case "twoSubIncrementDecrease" :
			case "twoSubIncrementIncrease" :
				targetElement = "the initial group increment ";
				break;
			case "twoSubSubLetterDecrease" :
			case "twoSubSubLetterIncrease" :
			case "manySubSubLetterDecrease" :
			case "manySubSubLetterIncrease" :
				targetElement = "the value of the first subgroup initial letter ";
				break;
			case "twoSubSubSizeDecrease" :
			case "twoSubSubSizeIncrease" :
			case "manySubSubSizeDecrease" :
			case "manySubSubSizeIncrease" :
			case "manySubSubSizeEnumerate" :
				targetElement = "the size of the initial subgroup ";
				break;
			case "twoSubSubIncrementDecrease" :
			case "twoSubSubIncrementIncrease" :
			case "manySubSubIncrementDecrease" :
			case "manySubSubIncrementIncrease" :
			case "manySubSubIncrementEnumerate" :
				targetElement = "the initial subgroup increment ";
				break;
			case "twoLetterEnumerate" : 
				targetElement = "letter ";
				break;
			case "manySymmetryReverse":
				targetElement = "the group of values ";
				break;
			case "twoSizeEnumerate" :
				targetElement = "a group of size ";
				break;
			case "twoIncrementEnumerate" :
				targetElement = "a group of increment ";
				break;
			case "twoSubLetterEnumerate" :
				targetElement = "a group of initial letter ";
				break;
			case "twoSubSizeEnumerate" :
				targetElement = "an initial subgroup of size ";
				break;
			case "twoSubIncrementEnumerate" :
				targetElement = "an initial subgroup of increment ";
				break;
			case "twoSubSubLetterEnumerate" :
				targetElement = "a first subgroup of initial letter ";
				break;
			case "twoSubSubSizeEnumerate" :
				targetElement = "an initial sub-subgroup of size ";
				break;
			case "twoSubSubIncrementEnumerate" :
				targetElement = "an initial sub-subgroup of increment ";
				break;
			case "manySubSizeDecrease" :
			case "manySubSizeIncrease" :
			case "manySubSizeEnumerate" :				
				targetElement = "the size of the initial group ";
				break;
			case "manySubIncrementDecrease" :
			case "manySubIncrementIncrease" :
			case "manySubIncrementEnumerate" :
				targetElement = "the increment of the initial group ";
				break;
			case "manyLetterEnumerate" :
				targetElement = "letters ";
				break;
			case "manySizeEnumerate" :
				targetElement = "the group size ";
				break;
			case "manyIncrementEnumerate" :
				targetElement = "the group increment ";
				break;
			case "manySubLetterEnumerate" :
				targetElement = "the group initial letter ";
				break;
			case "manySubSubLetterEnumerate" :
				targetElement = "the first subgroup initial letter ";
				break;
			case "twoEnumerationDecrease" :
			case "twoEnumerationIncrease" :
			case "manyEnumerationDecrease" :
			case "manyEnumerationIncrease" :
				targetElement = "the initial enumeration letters ";
			default :
				throw new VerbalizationException("Target.getTargetElement() : the parameter " + targetCode + 
						" can't be handled.");
		}
		return targetElement;
	}	

}
