package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;

public class CentralProminentPositionOS extends ProminentPositionOS implements WhichPositionTypeOS {

	private MinimalOS centralProminentPositionProperty;
	
	public CentralProminentPositionOS(String elementID, PositionOS position, 
			MinimalOS centralProminentPositionProperty) {
		super(elementID, position);
		this.centralProminentPositionProperty = centralProminentPositionProperty;
	}
	
	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(centralProminentPositionProperty);
		return listOfComponents;
	}	
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!centralProminentPositionProperty.equals(idToIOrderedSet.get(centralProminentPositionProperty.getElementID())))
			centralProminentPositionProperty = (
					MinimalOS) idToIOrderedSet.get(centralProminentPositionProperty.getElementID());
	}

}
