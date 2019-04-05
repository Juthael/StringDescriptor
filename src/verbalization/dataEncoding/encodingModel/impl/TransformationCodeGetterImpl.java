package verbalization.dataEncoding.encodingModel.impl;

import java.util.List;

import verbalization.dataEncoding.encodingModel.ITransformationCodeGetter;

public class TransformationCodeGetterImpl implements ITransformationCodeGetter {

	private final List<String> listOfPredicateCodes;
	private final String parameter;	
	
	public TransformationCodeGetterImpl(List<String> listOfPredicateCodes, String parameter) {
		this.listOfPredicateCodes = listOfPredicateCodes;
		this.parameter = parameter;
	}

	@Override
	public List<String> getListOfPredicateCodes() {
		return listOfPredicateCodes;
	}

	@Override
	public String getParameter() {
		return parameter;
	}

}
