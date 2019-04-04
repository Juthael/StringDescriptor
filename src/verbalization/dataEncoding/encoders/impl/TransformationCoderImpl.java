package verbalization.implementations;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import verbalization.implementations.dataEncodingModel.TransformationCodeGetterV1;
import verbalization.interfaces.TransformationCoderInterface;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;

public class TransformationCoderV2 implements TransformationCoderInterface{

	private TransformationCodeGetterInterface transformationCodeGetter;
	private final int nbOfComponents;
	private final List<String> relationProperties;
	
	public TransformationCoderV2(String nbOfComponents, List<String> relationProperties) throws VerbalizationException {
		this.nbOfComponents = Integer.parseInt(nbOfComponents);
		this.relationProperties = relationProperties;
		transformationCodeGetter = setTransformationCodeGetter();
	}

	@Override
	public TransformationCodeGetterInterface getTransformationCodeGetter() {
		return transformationCodeGetter;
	}
	
	private TransformationCodeGetterInterface setTransformationCodeGetter() throws VerbalizationException {
		TransformationCodeGetterInterface transformationCodeGetter;
		String parameterCode = "";
		List<String> listOfDimensions = new ArrayList<String>();
		String transfoTypeCode = "";		
		boolean continueAnalysis = true;
		int propertyIndex = 0;
		while (continueAnalysis == true && propertyIndex < relationProperties.size()) {
			if (relationProperties.get(propertyIndex).contains("/platonicLetter")) {
				listOfDimensions.add("do");
				transfoTypeCode = "Effector";
				int lastSlashIndex = relationProperties.get(propertyIndex).lastIndexOf("/");
				parameterCode = relationProperties.get(propertyIndex).substring(lastSlashIndex + 1);
				continueAnalysis = false;
			}
			else if (relationProperties.get(propertyIndex).contains("relation/dimension")) {
				int lastSlashIndex = relationProperties.get(propertyIndex).lastIndexOf("/");
				listOfDimensions.add(relationProperties.get(propertyIndex).substring(lastSlashIndex + 1));
			}
			else if (relationProperties.get(propertyIndex).contains("relation/enumeration")) {
				int lastSlashIndex = relationProperties.get(propertyIndex).lastIndexOf("/");
				transfoTypeCode = "Enumerate";
				parameterCode = relationProperties.get(propertyIndex).substring(lastSlashIndex + 1);
			}
			else if (relationProperties.get(propertyIndex).contains("sequence/commonDiff")) {
				int lastSlashIndex = relationProperties.get(propertyIndex).lastIndexOf("/");
				parameterCode = relationProperties.get(propertyIndex).substring(lastSlashIndex + 1);
				int commonDiffValue = Integer.parseInt(parameterCode);
				if (commonDiffValue < 0)
					transfoTypeCode = "Decrease";
				else if (commonDiffValue == 0)
					transfoTypeCode = "Equals";
				else transfoTypeCode = "Increase";
				continueAnalysis = false;
			}
			propertyIndex++;
		}
		List<String> listOfPredicateCodes = setListOfPredicateCodes(listOfDimensions, transfoTypeCode);
		transformationCodeGetter =	new TransformationCodeGetterV1(listOfPredicateCodes, parameterCode);
		return transformationCodeGetter;	
	}
	
	private List<String> setListOfPredicateCodes(List<String> listOfDimensions, String relationTypeCode) throws VerbalizationException {
		List<String> listOfPredicateCodes = new ArrayList<String>();
		String zeroOneTwoMany = getNbOfComponentsString();
		List<String> listOfDimensionCodes = new ArrayList<String>();
		for (String dimension : listOfDimensions) {
			String subCode = getSubString(dimension);
			String dimensionTypeCode;
			int lastDotIndex = dimension.lastIndexOf(".");
			int lastColonIndex = dimension.lastIndexOf(":");
			int firstDimensionTypeIndex;
			if (lastDotIndex < lastColonIndex)
				firstDimensionTypeIndex = lastColonIndex + 1;
			else firstDimensionTypeIndex = lastDotIndex + 1;
			String dimensionType = dimension.substring(firstDimensionTypeIndex);
			switch (dimensionType) {
				case ("size") :
					dimensionTypeCode = "Size";
					break;
				case ("platonicLetter") :
					dimensionTypeCode = "Letter";
					break;
				case("commonDiff") :
					dimensionTypeCode = "Increment";
					break;
				case("enumeration") :
					dimensionTypeCode = "Enumeration";
					break;
				case("do") :
					dimensionTypeCode = "WriteLetter";
					break;
				default : 
					throw new VerbalizationException("TransformationCoder.setDimensionCode() : dimensionType " + dimensionType 
							+ "wasn't recognized.");
			}
			StringBuilder sB1 = new StringBuilder();
			sB1.append(zeroOneTwoMany);
			sB1.append(subCode);
			sB1.append(dimensionTypeCode);
			listOfDimensionCodes.add(sB1.toString());
		}
		for (String dimensionCode : listOfDimensionCodes) {
			String predicateCode = dimensionCode.concat(relationTypeCode);
			listOfPredicateCodes.add(predicateCode);
		}
		return listOfPredicateCodes;
	}
	
	private String getSubString(String dimension) {
		String sub = "";
		int numberOfSubs;
		if (dimension.indexOf(":") == -1)
			numberOfSubs = 0;
		else numberOfSubs = dimension.indexOf(":") - dimension.lastIndexOf(":") + 1;
		for (int i=0 ; i<numberOfSubs ; i++) {
			sub = sub.concat("Sub");
		}
		return sub;
	}
	
	private String getNbOfComponentsString() throws VerbalizationException {
		String nbOfComponentsString;
		if (nbOfComponents < 0)
			throw new VerbalizationException("TransfoCoder.getNbOfComponentsString() : "
					+ nbOfComponents + " is an illegal value for nbOfComponentString.");
		else if (nbOfComponents == 0)
			nbOfComponentsString = "";
		else if (nbOfComponents == 1)
			nbOfComponentsString = "one";
		else if (nbOfComponents == 2)
			nbOfComponentsString = "two";
		else nbOfComponentsString = "many";
		return nbOfComponentsString;
	}	

}
