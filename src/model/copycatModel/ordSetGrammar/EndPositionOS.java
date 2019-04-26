package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class EndPositionOS extends NonMinimalRelevantOS implements IEndPositionOS {

	private static final String NAME = "endPosition";
	private MinimalOS endPositionProperty;
	
	public EndPositionOS(String elementID, MinimalOS endPositionProperty) {
		super(elementID);
		this.endPositionProperty = endPositionProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(endPositionProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
