package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class PositionOS extends NonMinimalRelevantOS implements IPositionOS {

	private static final String NAME = "position";
	private MinimalOS positionProperty;
	
	public PositionOS(String elementID, MinimalOS positionProperty) {
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


