package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
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

}
