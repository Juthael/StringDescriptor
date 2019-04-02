package verbalization.implementations.verbalStructureModel;

import exceptions.VerbalizationException;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;
import verbalization.interfaces.verbalStructureModel.EnumerationInterface;

public class EnumerationV2 extends TransformationV2 implements EnumerationInterface {

	private static final String transformationName = "enumeration";
	
	public EnumerationV2(TransformationCodeGetterInterface transformationCodeGetter) throws VerbalizationException {
		super(transformationCodeGetter);
	}

	@Override
	public String getTransformationName() {
		return transformationName;
	}

}
