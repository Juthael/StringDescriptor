package verbalization.implementations.verbalStructureModel;

import java.util.List;

import exceptions.VerbalizationException;
import verbalization.interfaces.dataEncodingModel.InstructionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.RecipeCodeGetterInterface;
import verbalization.interfaces.verbalStructureModel.InstructionInterface;
import verbalization.interfaces.verbalStructureModel.RecipeInterface;

public class RecipeV2 implements RecipeInterface {

	private final String verbalRecipe;
	
	public RecipeV2(RecipeCodeGetterInterface recipeCodeGetter) throws VerbalizationException {
		verbalRecipe = setVerbalRecipe(recipeCodeGetter);
	}
	
	@Override
	public String getVerbalRecipe() {
		return verbalRecipe;
	}
	
	private String setVerbalRecipe(RecipeCodeGetterInterface recipeCodeGetter) throws VerbalizationException {
		String verbalRecipe;
		List<InstructionCodeGetterInterface> listOfInstructionCodeGetters = recipeCodeGetter.getListOfInstructionCodeGetters();
		StringBuilder sB = new StringBuilder();
		String indentation = "   ";
		String newLine = System.getProperty("line.separator");
		for (int i=0 ; i<listOfInstructionCodeGetters.size() ; i++) {
			if (i != 0) {
				sB.append(newLine);
				sB.append(indentation);
				sB.append("-then ");
			}
			InstructionInterface instruction = new InstructionV2(listOfInstructionCodeGetters.get(i));
			sB.append(instruction.getVerbalInstruction());
		}
		sB.append(newLine);
		verbalRecipe = sB.toString();
		return verbalRecipe;
	}
	
}
