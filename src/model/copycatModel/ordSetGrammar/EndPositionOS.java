package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class EndPositionOS extends AbstractNonMinimalOS implements IOrderedSet {

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
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!endPositionProperty.equals(idToIOrderedSet.get(endPositionProperty.getElementID())))
			endPositionProperty = (MinimalOS) idToIOrderedSet.get(endPositionProperty.getElementID());
	}	

}
