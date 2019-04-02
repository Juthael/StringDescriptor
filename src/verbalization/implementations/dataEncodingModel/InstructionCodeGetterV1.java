package verbalization.implementations.dataEncodingModel;

import java.util.List;

import verbalization.interfaces.dataEncodingModel.InstructionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;

public class InstructionCodeGetterV1 implements InstructionCodeGetterInterface {

	private final String nbOfRepetitionsCodeString;
	private final List<TransformationCodeGetterInterface> listOfTransformationCodeGetters;
	
	public InstructionCodeGetterV1(String nbOfRepetitionsCodeString, 
			List<TransformationCodeGetterInterface> listOfTransformationCodeGetters) {
		this.nbOfRepetitionsCodeString = nbOfRepetitionsCodeString;
		this.listOfTransformationCodeGetters = listOfTransformationCodeGetters;
	}

	@Override
	public String getNbOfRepetitionsCodeString() {
		return nbOfRepetitionsCodeString;
	}

	@Override
	public List<TransformationCodeGetterInterface> getListOfTransformationCodeGetters() {
		return listOfTransformationCodeGetters;
	}

}
