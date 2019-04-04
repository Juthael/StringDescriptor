package verbalization.implementations.verbalStructureModel;

import exceptions.VerbalizationException;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;
import verbalization.interfaces.verbalStructureModel.EffectorInterface;

public class EffectorV2 extends TransformationV2 implements EffectorInterface {
	
	private static final String transformationName = "effector";

	public EffectorV2(TransformationCodeGetterInterface transformationCodeGetter) throws VerbalizationException {
		super(transformationCodeGetter);
	}

	@Override
	public String getTransformationName() {
		return transformationName;
	}

}
