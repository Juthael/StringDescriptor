package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class PositionOS extends NonMinimalRelevantLowerSetElement implements IPositionOS {

	private static final String NAME = "position";
	private MinimalLowerSetElement positionProperty;
	
	public PositionOS(String elementID, MinimalLowerSetElement positionProperty) {
		super(elementID);
		this.positionProperty = positionProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(positionProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}


