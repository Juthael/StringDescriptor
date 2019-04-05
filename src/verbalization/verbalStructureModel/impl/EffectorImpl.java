package verbalization.verbalStructureModel.impl;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.verbalStructureModel.IEffector;

public class EffectorImpl extends TransformationImpl implements IEffector {
	
	private static final String transformationName = "effector";

	public EffectorImpl(ITransformationCodeGetter transformationCodeGetter) throws VerbalizationException {
		super(transformationCodeGetter);
	}

	@Override
	public String getTransformationName() {
		return transformationName;
	}

}
