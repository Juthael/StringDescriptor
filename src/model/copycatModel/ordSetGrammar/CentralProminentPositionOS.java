package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;

public class CentralProminentPositionOS extends ProminentPositionOS implements WhichPositionTypeOS {

	private MinimalLowerSetElement centralProminentPositionProperty;
	
	public CentralProminentPositionOS(String elementID, IPositionOS position, 
			MinimalLowerSetElement centralProminentPositionProperty) {
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
