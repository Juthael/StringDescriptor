package verbalization.dataEncoding.encodingModel.impl;

import java.util.List;

import verbalization.dataEncoding.encodingModel.IInstructionCodeGetter;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;

public class InstructionCodeGetterImpl implements IInstructionCodeGetter {

	private final String nbOfRepetitionsCodeString;
	private final List<ITransformationCodeGetter> listOfTransformationCodeGetters;
	
	public InstructionCodeGetterImpl(String nbOfRepetitionsCodeString, 
			List<ITransformationCodeGetter> listOfTransformationCodeGetters) {
		this.nbOfRepetitionsCodeString = nbOfRepetitionsCodeString;
		this.listOfTransformationCodeGetters = listOfTransformationCodeGetters;
	}

	@Override
	public String getNbOfRepetitionsCodeString() {
		return nbOfRepetitionsCodeString;
	}

	@Override
	public List<ITransformationCodeGetter> getListOfTransformationCodeGetters() {
		return listOfTransformationCodeGetters;
	}

}
