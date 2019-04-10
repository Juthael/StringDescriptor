package verbalization.dataEncoding.encoders.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import settings.Settings;
import syntacticTreesGeneration.impl.DimensionEncodingManager;
import verbalization.dataEncoding.encoders.ITransformationCoder;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.dataEncoding.encodingModel.impl.TransformationCodeGetterImpl;

public class TransformationCoderImpl implements ITransformationCoder{

	private ITransformationCodeGetter transformationCodeGetter;
	private final int nbOfComponents;
	private final List<String> relationProperties;
	
	public TransformationCoderImpl(String nbOfComponents, List<String> relationProperties) throws VerbalizationException {
		this.nbOfComponents = Integer.parseInt(nbOfComponents);
		this.relationProperties = relationProperties;
		transformationCodeGetter = setTransformationCodeGetter();
	}

	@Override
	public ITransformationCodeGetter getTransformationCodeGetter() {
		return transformationCodeGetter;
	}
	
	private ITransformationCodeGetter setTransformationCodeGetter() throws VerbalizationException {
		ITransformationCodeGetter transformationCodeGetter;
		String parameterCode = "";
		List<String> listOfDimensions = new ArrayList<String>();
		String transfoTypeCode = "";		
		boolean continueAnalysis = true;
		int propertyIndex = 0;
		while (continueAnalysis == true && propertyIndex < relationProperties.size()) {
			if (relationProperties.get(propertyIndex).contains("/platonicLetter")) {
				listOfDimensions.add("do");
				transfoTypeCode = "Effector";
				int lastSlashIndex = relationProperties.get(propertyIndex).lastIndexOf(Settings.PATH_SEPARATOR);
				parameterCode = relationProperties.get(propertyIndex).substring(lastSlashIndex + 1);
				continueAnalysis = false;
			}
			else if (relationProperties.get(propertyIndex).contains("relation/dimension")) {
				int lastSlashIndex = relationProperties.get(propertyIndex).lastIndexOf(Settings.PATH_SEPARATOR);
				listOfDimensions.add(relationProperties.get(propertyIndex).substring(lastSlashIndex + 1));
			}
			else if (relationProperties.get(propertyIndex).contains("relation/enumeration")) {
				int lastSlashIndex = relationProperties.get(propertyIndex).lastIndexOf(Settings.PATH_SEPARATOR);
				transfoTypeCode = "Enumerate";
				parameterCode = relationProperties.get(propertyIndex).substring(lastSlashIndex + 1);
			}
			else if (relationProperties.get(propertyIndex).contains("sequence/commonDiff")) {
				int lastSlashIndex = relationProperties.get(propertyIndex).lastIndexOf(Settings.PATH_SEPARATOR);
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
		transformationCodeGetter =	new TransformationCodeGetterImpl(listOfPredicateCodes, parameterCode);
		return transformationCodeGetter;	
	}
	
	private List<String> setListOfPredicateCodes(List<String> listOfDimensions, String relationTypeCode) throws VerbalizationException {
		List<String> listOfPredicateCodes = new ArrayList<String>();
		String zeroOneTwoMany = getNbOfComponentsString();
		List<String> listOfDimensionCodes = new ArrayList<String>();
		for (String dimension : listOfDimensions) {
			String dimensionType = DimensionEncodingManager.getDimensionTypeFromCode(dimension);
			String subCode = getSubString(dimension);
			String dimensionTypeCode;
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
		String subString = "";
		StringBuilder sB = new StringBuilder();
		int dimensionDegree = DimensionEncodingManager.getDimensionDegree(dimension);
		for (int i=0 ; i<dimensionDegree ; i++)
			sB.append("Sub");
		if (sB.length() > 0)
			subString = sB.toString();
		return subString;
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
