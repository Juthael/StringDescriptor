package verbalization.implementations.verbalStructureModel;

import exceptions.VerbalizationException;
import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;
import verbalization.interfaces.verbalStructureModel.ActionInterface;
import verbalization.interfaces.verbalStructureModel.ParameterInterface;
import verbalization.interfaces.verbalStructureModel.TargetInterface;
import verbalization.interfaces.verbalStructureModel.TransformationInterface;

public abstract class TransformationV2 implements TransformationInterface {

	private String verbalTransformation;
	
	public TransformationV2(TransformationCodeGetterInterface transformationCodeGetter) throws VerbalizationException {
		verbalTransformation = setVerbalTransformation(transformationCodeGetter);
	}

	@Override
	public String getVerbalTransformation() {
		return verbalTransformation;
	}

	@Override
	abstract public String getTransformationName();
	
	private String setVerbalTransformation(TransformationCodeGetterInterface transformationCodeGetter) 
			throws VerbalizationException {
		String verbalTransformation;
		StringBuilder sB = new StringBuilder();
		ActionInterface action = new ActionV2(transformationCodeGetter);
		sB.append(action.getVerbalAction());
		TargetInterface target = new TargetV2(transformationCodeGetter);
		sB.append(target.getVerbalTarget());
		ParameterInterface parameter = new ParameterV2(transformationCodeGetter);
		sB.append(parameter.getVerbalParameter());
		verbalTransformation = sB.toString();
		return verbalTransformation;
	}

}
