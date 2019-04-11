package verbalization.verbalStructureModel.impl;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.verbalStructureModel.ISecondDegSymmetry;

public class SecondDegSymmetryImpl extends TransformationImpl implements ISecondDegSymmetry {

	private static final String transformationName = "secondDegSymmetry";
	
	public SecondDegSymmetryImpl(ITransformationCodeGetter transformationCodeGetter) throws VerbalizationException {
		super(transformationCodeGetter);
	}

	@Override
	public String getTransformationName() {
		return transformationName;
	}

}
