package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.AbstractNonMinimalOS;
import model.orderedSetModel.IOrderedSet;

public class PositionOS extends AbstractNonMinimalOS implements WhichPositionTypeOS {

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
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!positionProperty.equals(idToIOrderedSet.get(positionProperty.getElementID())))
			positionProperty = (MinimalOS) idToIOrderedSet.get(positionProperty.getElementID());
	}		

}


