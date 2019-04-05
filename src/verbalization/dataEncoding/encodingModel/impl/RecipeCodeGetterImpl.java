package verbalization.dataEncoding.encodingModel.impl;

import java.util.List;

import verbalization.dataEncoding.encodingModel.IInstructionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;

public class RecipeCodeGetterImpl implements IRecipeCodeGetter {

	private final List<IInstructionCodeGetter> listOfInstructionCodeGetters;
	
	public RecipeCodeGetterImpl(List<IInstructionCodeGetter> listOfInstructionCodeGetters) {
		this.listOfInstructionCodeGetters = listOfInstructionCodeGetters;
	}

	@Override
	public List<IInstructionCodeGetter> getListOfInstructionCodeGetters() {
		return listOfInstructionCodeGetters;
	}

}
