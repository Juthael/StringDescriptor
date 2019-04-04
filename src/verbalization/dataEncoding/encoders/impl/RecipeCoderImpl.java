package verbalization.implementations;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import verbalization.implementations.dataEncodingModel.RecipeCodeGetterV1;
import verbalization.interfaces.InstructionCoderInterface;
import verbalization.interfaces.RecipeCoderInterface;
import verbalization.interfaces.dataEncodingModel.InstructionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.RecipeCodeGetterInterface;

public class RecipeCoderV1 implements RecipeCoderInterface {

	private RecipeCodeGetterInterface recipeCodeGetter;
	
	public RecipeCoderV1(List<String> listOfUnfactorizedGroupProperties) throws VerbalizationException {
		recipeCodeGetter = setRecipeCodeGetter(listOfUnfactorizedGroupProperties);
	}

	@Override
	public RecipeCodeGetterInterface getRecipeCodeGetter() {
		return recipeCodeGetter;
	}
	
	private RecipeCodeGetterInterface setRecipeCodeGetter(List<String> listOfUnfactorizedGroupProperties) 
			throws VerbalizationException {
		RecipeCodeGetterInterface recipeCodeGetter;
		List<InstructionCodeGetterInterface> listOfInstructionCodeGetters = 
				setListOfInstructionsRecursively(listOfUnfactorizedGroupProperties);
		recipeCodeGetter = new RecipeCodeGetterV1(listOfInstructionCodeGetters);	
		return recipeCodeGetter;
	}
	
	private List<InstructionCodeGetterInterface> setListOfInstructionsRecursively(List<String> groupListOfProperties) 
			throws VerbalizationException {
		List<InstructionCodeGetterInterface> listOfInstructionCodeGetters = 
				new ArrayList<InstructionCodeGetterInterface>();
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
		InstructionCoderInterface instructionCoder = new InstructionCoderV2(nbOfComponents, relationXPropertyList); 
		listOfInstructionCodeGetters.add(instructionCoder.getInstructionCodeGetter());
		return listOfInstructionCodeGetters;
	}

}
