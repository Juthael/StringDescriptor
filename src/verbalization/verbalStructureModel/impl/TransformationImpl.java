package verbalization.verbalStructureModel.impl;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;
import verbalization.verbalStructureModel.IAction;
import verbalization.verbalStructureModel.IParameter;
import verbalization.verbalStructureModel.ITarget;
import verbalization.verbalStructureModel.ITransformation;

public abstract class TransformationImpl implements ITransformation {

	private String verbalTransformation;
	
	public TransformationImpl(ITransformationCodeGetter transformationCodeGetter) throws VerbalizationException {
		verbalTransformation = setVerbalTransformation(transformationCodeGetter);
	}

	@Override
	public String getVerbalTransformation() {
		return verbalTransformation;
	}

	@Override
	abstract public String getTransformationName();
	
	private String setVerbalTransformation(ITransformationCodeGetter transformationCodeGetter) 
			throws VerbalizationException {
		String verbalTransformation;
		StringBuilder sB = new StringBuilder();
		IAction action = new ActionImpl(transformationCodeGetter);
		sB.append(action.getVerbalAction());
		ITarget target = new TargetImpl(transformationCodeGetter);
		sB.append(target.getVerbalTarget());
		IParameter parameter = new ParameterImpl(transformationCodeGetter);
		sB.append(parameter.getVerbalParameter());
		verbalTransformation = sB.toString();
		return verbalTransformation;
	}

}
