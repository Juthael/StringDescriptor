package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class DirectionOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "direction";
	private MinimalOS directionProperty;
	
	public DirectionOS(String elementID, MinimalOS directionProperty) {
		super(elementID);
		this.directionProperty = directionProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(directionProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!directionProperty.equals(idToIOrderedSet.get(directionProperty.getElementID())))
			directionProperty = (MinimalOS) idToIOrderedSet.get(directionProperty.getElementID());
	}		

}
