package verbalization.verbalStructureModel.impl;

import java.util.List;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.IInstructionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;
import verbalization.verbalStructureModel.IInstruction;
import verbalization.verbalStructureModel.IRecipe;

public class RecipeImpl implements IRecipe {

	private final String verbalRecipe;
	
	public RecipeImpl(IRecipeCodeGetter recipeCodeGetter) throws VerbalizationException {
		verbalRecipe = setVerbalRecipe(recipeCodeGetter);
	}
	
	@Override
	public String getVerbalRecipe() {
		return verbalRecipe;
	}
	
	private String setVerbalRecipe(IRecipeCodeGetter recipeCodeGetter) throws VerbalizationException {
		String verbalRecipe;
		List<IInstructionCodeGetter> listOfInstructionCodeGetters = recipeCodeGetter.getListOfInstructionCodeGetters();
		StringBuilder sB = new StringBuilder();
		String indentation = "   ";
		String newLine = System.getProperty("line.separator");
		for (int i=0 ; i<listOfInstructionCodeGetters.size() ; i++) {
			if (i != 0) {
				sB.append(newLine);
				sB.append(indentation);
				sB.append("-then ");
			}
			IInstruction instruction = new InstructionImpl(listOfInstructionCodeGetters.get(i));
			sB.append(instruction.getVerbalInstruction());
		}
		sB.append(newLine);
		verbalRecipe = sB.toString();
		return verbalRecipe;
	}
	
}
