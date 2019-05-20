package verbalization.dataEncoding.encoders.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import settings.Settings;
import verbalization.dataEncoding.encoders.IInstructionCoder;
import verbalization.dataEncoding.encoders.IRecipeCoder;
import verbalization.dataEncoding.encodingModel.IInstructionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;
import verbalization.dataEncoding.encodingModel.impl.RecipeCodeGetterImpl;

public class RecipeCoderImpl implements IRecipeCoder {

	private IRecipeCodeGetter recipeCodeGetter;
	
	public RecipeCoderImpl(List<String> listOfUnfactorizedFrameProperties) throws VerbalizationException {
		recipeCodeGetter = setRecipeCodeGetter(listOfUnfactorizedFrameProperties);
	}

	@Override
	public IRecipeCodeGetter getRecipeCodeGetter() {
		return recipeCodeGetter;
	}
	
	private IRecipeCodeGetter setRecipeCodeGetter(List<String> listOfUnfactorizedFrameProperties) 
			throws VerbalizationException {
		IRecipeCodeGetter recipeCodeGetter;
		List<IInstructionCodeGetter> listOfInstructionCodeGetters = 
				setListOfInstructionsRecursively(listOfUnfactorizedFrameProperties);
		recipeCodeGetter = new RecipeCodeGetterImpl(listOfInstructionCodeGetters);	
		return recipeCodeGetter;
	}
	
	private List<IInstructionCodeGetter> setListOfInstructionsRecursively(List<String> frameListOfProperties) 
			throws VerbalizationException {
		List<IInstructionCodeGetter> listOfInstructionCodeGetters = 
				new ArrayList<IInstructionCodeGetter>();
		List<String> relationXPropertyList = new ArrayList<String>();
		List<String> firstFramePropertyList = new ArrayList<String>();
		int frmaeSubStringIndex = -1;
		String nbOfComponents = "";
		boolean firstFrameHasBegun = false;
		int propertyIndex = 0;
		boolean continueAnalysis = true;
		while (continueAnalysis == true && propertyIndex < frameListOfProperties.size()) {
			String currentProperty = frameListOfProperties.get(propertyIndex);
			if (currentProperty.startsWith("frame/relations/relation")) {
				relationXPropertyList.add(frameListOfProperties.get(propertyIndex));
			}
			else if (currentProperty.contains("frame/size")) {
				if (propertyIndex == 0) {
					int lastSlashIndex = currentProperty.lastIndexOf(Settings.PATH_SEPARATOR);
					nbOfComponents = currentProperty.substring(lastSlashIndex + 1);
				}
				else {
					if (firstFrameHasBegun == false) {
						frmaeSubStringIndex = currentProperty.lastIndexOf("frame");
						firstFrameHasBegun = true;
					}
					else if(currentProperty.startsWith("frame/size", frmaeSubStringIndex))
						continueAnalysis = false;
				}
			}
			if (continueAnalysis == true) {
				if (firstFrameHasBegun == true) {
					firstFramePropertyList.add(currentProperty.substring(frmaeSubStringIndex));
				}
				else if (relationXPropertyList.isEmpty() && currentProperty.contains("letter/platonicLetter")){
					relationXPropertyList.add(currentProperty);
					nbOfComponents = "0";
					continueAnalysis = false;
				}
				propertyIndex++;
			}
		}
		if (!firstFramePropertyList.isEmpty()) {
			listOfInstructionCodeGetters.addAll(setListOfInstructionsRecursively(firstFramePropertyList));
		}
		IInstructionCoder instructionCoder = new InstructionCoderImpl(nbOfComponents, relationXPropertyList); 
		listOfInstructionCodeGetters.add(instructionCoder.getInstructionCodeGetter());
		return listOfInstructionCodeGetters;
	}

}
