package verbalization.implementations.verbalStructureModel;

import exceptions.VerbalizationException;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;
import verbalization.interfaces.verbalStructureModel.SequenceInterface;

public class SequenceV2 extends TransformationV2 implements SequenceInterface {

	private static final String transformationName = "sequence";
	
	public SequenceV2(TransformationCodeGetterInterface transformationCodeGetter) throws VerbalizationException {
		super(transformationCodeGetter);
	}
	
	@Override
	public String getTransformationName() {
		return transformationName;
	}

}
