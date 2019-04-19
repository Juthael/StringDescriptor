package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class EndPositionOS extends NonMinimalRelevantLowerSetElement implements IEndPositionOS {

	private static final String NAME = "endPosition";
	private MinimalLowerSetElement endPositionProperty;
	
	public EndPositionOS(String elementID, MinimalLowerSetElement endPositionProperty) {
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
