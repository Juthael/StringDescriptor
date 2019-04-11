package verbalization.verbalStructureModel.impl;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.verbalStructureModel.IAction;

public class ActionImpl implements IAction {

	private String verbalAction;
	
	public ActionImpl(ITransformationCodeGetter transformationCodeGetter) throws VerbalizationException {
		verbalAction = setVerbalAction(transformationCodeGetter.getListOfPredicateCodes().get(0));
	}

	@Override
	public String getVerbalAction() {
		return verbalAction;
	}
	
	private String setVerbalAction(String predicateCode) throws VerbalizationException {
		String action;
		switch (predicateCode) {
			case "oneLetterDecrease" :
			case "oneLetterIncrease" :	
			case "twoLetterEnumerate" :
			case "twoSizeEnumerate" :
			case "twoIncrementEnumerate" :
			case "twoSubLetterEnumerate" :
			case "twoSubSizeEnumerate" :
			case "twoSubIncrementEnumerate" :
			case "twoSubSubLetterEnumerate" :
			case "twoSubSubSizeEnumerate" :
			case "twoSubSubIncrementEnumerate" :
				action = " with ";
				break;
			case "manyLetterEnumerate" :
			case "manySymmetryReverse" :
				action = "with ";
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
				action = "";
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
				action = "reducing ";
				break;
			case "twoLetterIncrease" :
			case "twoSizeIncrease" :
			case "twoIncrementIncrease" :
			case "twoSubLetterIncrease" :
			case "twoSubSizeIncrease" :
			case "twoSubIncrementIncrease" :
			case "twoSubSubLetterIncrease" :
			case "twoSubSubSizeIncrease" :
			case "twoSubSubIncrementIncrease" :
				action = "increasing ";
				break;
			case "manyLetterDecrease" :
			case "manySizeDecrease" :
			case "manyIncrementDecrease" :
			case "manySubLetterDecrease" :
			case "manySubSizeDecrease" :
			case "manySubIncrementDecrease" :
			case "manySubSubLetterDecrease" :
			case "manySubSubSizeDecrease" :
			case "manySubSubIncrementDecrease" :
				action = "reducing each time ";
				break;
			case "manyLetterIncrease" :
			case "manySizeIncrease" :
			case "manyIncrementIncrease" :
			case "manySubLetterIncrease" :
			case "manySubSizeIncrease" :
			case "manySubIncrementIncrease" :
			case "manySubSubLetterIncrease" :
			case "manySubSubSizeIncrease" :
			case "manySubSubIncrementIncrease" :
				action = "increasing each time ";
				break;
			case "manySizeEnumerate" :
			case "manyIncrementEnumerate" :
			case "manySubLetterEnumerate" :
			case "manySubSizeEnumerate" :
			case "manySubIncrementEnumerate" :
			case "manySubSubLetterEnumerate" :
			case "manySubSubSizeEnumerate" :
			case "manySubSubIncrementEnumerate" :
				action = "giving successively to ";
				break;
			case "WriteLetterEffector" :
				action = "write letter ";
				break;
			case "twoSymmetryReverse" : 
				action = " symmetrically";
				break;
			default :
				throw new VerbalizationException("Action.getActionString() : the parameter "
						+ predicateCode + " can't be handled.");
		}
		return action;
	}	

}
