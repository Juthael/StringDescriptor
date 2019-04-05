package verbalization.dataEncoding.encoders.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encoders.IInstructionCoder;
import verbalization.dataEncoding.encoders.IRecipeCoder;
import verbalization.dataEncoding.encodingModel.IInstructionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;
import verbalization.dataEncoding.encodingModel.impl.RecipeCodeGetterImpl;

public class RecipeCoderImpl implements IRecipeCoder {

	private IRecipeCodeGetter recipeCodeGetter;
	
	public RecipeCoderImpl(List<String> listOfUnfactorizedGroupProperties) throws VerbalizationException {
		recipeCodeGetter = setRecipeCodeGetter(listOfUnfactorizedGroupProperties);
	}

	@Override
	public IRecipeCodeGetter getRecipeCodeGetter() {
		return recipeCodeGetter;
	}
	
	private IRecipeCodeGetter setRecipeCodeGetter(List<String> listOfUnfactorizedGroupProperties) 
			throws VerbalizationException {
		IRecipeCodeGetter recipeCodeGetter;
		List<IInstructionCodeGetter> listOfInstructionCodeGetters = 
				setListOfInstructionsRecursively(listOfUnfactorizedGroupProperties);
		recipeCodeGetter = new RecipeCodeGetterImpl(listOfInstructionCodeGetters);	
		return recipeCodeGetter;
	}
	
	private List<IInstructionCodeGetter> setListOfInstructionsRecursively(List<String> groupListOfProperties) 
			throws VerbalizationException {
		List<IInstructionCodeGetter> listOfInstructionCodeGetters = 
				new ArrayList<IInstructionCodeGetter>();
		List<String> relationXPropertyList = new ArrayList<String>();
		List<String> firstGroupPropertyList = new ArrayList<String>();
		int groupSubStringIndex = -1;
		String nbOfComponents = "";
		boolean firstGroupHasBegun = false;
		int propertyIndex = 0;
		boolean continueAnalysis = true;
		while (continueAnalysis == true && propertyIndex < groupListOfProperties.size()) {
			String currentProperty = groupListOfProperties.get(propertyIndex);
			if (currentProperty.startsWith("group/relations/relation")) {
				relationXPropertyList.add(groupListOfProperties.get(propertyIndex));
			}
			else if (currentProperty.contains("group/size")) {
				if (propertyIndex == 0) {
					int lastSlashIndex = currentProperty.lastIndexOf("/");
					nbOfComponents = currentProperty.substring(lastSlashIndex + 1);
				}
				else {
					if (firstGroupHasBegun == false) {
						groupSubStringIndex = currentProperty.lastIndexOf("group");
						firstGroupHasBegun = true;
					}
					else if(currentProperty.startsWith("group/size", groupSubStringIndex))
						continueAnalysis = false;
				}
			}
			if (continueAnalysis == true) {
				if (firstGroupHasBegun == true) {
					firstGroupPropertyList.add(currentProperty.substring(groupSubStringIndex));
				}
				else if (relationXPropertyList.isEmpty() && currentProperty.contains("letter/platonicLetter")){
					relationXPropertyList.add(currentProperty);
					nbOfComponents = "0";
					continueAnalysis = false;
				}
				propertyIndex++;
			}
		}
		if (!firstGroupPropertyList.isEmpty()) {
			listOfInstructionCodeGetters.addAll(setListOfInstructionsRecursively(firstGroupPropertyList));
		}
		IInstructionCoder instructionCoder = new InstructionCoderImpl(nbOfComponents, relationXPropertyList); 
		listOfInstructionCodeGetters.add(instructionCoder.getInstructionCodeGetter());
		return listOfInstructionCodeGetters;
	}

}
