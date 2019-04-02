package verbalization.implementations.dataEncodingModel;

import java.util.List;

import verbalization.interfaces.dataEncodingModel.InstructionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.RecipeCodeGetterInterface;

public class RecipeCodeGetterV1 implements RecipeCodeGetterInterface {

	private final List<InstructionCodeGetterInterface> listOfInstructionCodeGetters;
	
	public RecipeCodeGetterV1(List<InstructionCodeGetterInterface> listOfInstructionCodeGetters) {
		this.listOfInstructionCodeGetters = listOfInstructionCodeGetters;
	}

	@Override
	public List<InstructionCodeGetterInterface> getListOfInstructionCodeGetters() {
		return listOfInstructionCodeGetters;
	}

}
