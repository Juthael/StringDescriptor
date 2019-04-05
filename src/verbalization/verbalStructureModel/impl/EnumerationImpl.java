package verbalization.verbalStructureModel.impl;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.verbalStructureModel.IEnumeration;

public class EnumerationImpl extends TransformationImpl implements IEnumeration {

	private static final String transformationName = "enumeration";
	
	public EnumerationImpl(ITransformationCodeGetter transformationCodeGetter) throws VerbalizationException {
		super(transformationCodeGetter);
	}

	@Override
	public String getTransformationName() {
		return transformationName;
	}

}
