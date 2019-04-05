package verbalization.verbalStructureModel.impl;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.verbalStructureModel.ISequence;

public class SequenceImpl extends TransformationImpl implements ISequence {

	private static final String transformationName = "sequence";
	
	public SequenceImpl(ITransformationCodeGetter transformationCodeGetter) throws VerbalizationException {
		super(transformationCodeGetter);
	}
	
	@Override
	public String getTransformationName() {
		return transformationName;
	}

}
