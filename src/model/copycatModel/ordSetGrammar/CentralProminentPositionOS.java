package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalSetElement;

public class CentralProminentPositionOS extends ProminentPositionOS implements WhichPositionTypeOS {

	private MinimalSetElement centralProminentPositionProperty;
	
	public CentralProminentPositionOS(String elementID, PositionOS position, 
			MinimalSetElement centralProminentPositionProperty) {
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
