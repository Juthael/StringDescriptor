package verbalization.implementations.dataEncodingModel;

import java.util.List;

import verbalization.interfaces.dataEncodingModel.TransformationCodeGetterInterface;

public class TransformationCodeGetterV1 implements TransformationCodeGetterInterface {

	private final List<String> listOfPredicateCodes;
	private final String parameter;	
	
	public TransformationCodeGetterV1(List<String> listOfPredicateCodes, String parameter) {
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
