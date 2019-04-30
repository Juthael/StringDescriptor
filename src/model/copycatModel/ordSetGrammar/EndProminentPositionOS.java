package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;

public class EndProminentPositionOS extends ProminentPositionOS implements WhichPositionTypeOS {

	private EndPositionOS endPosition;
	
	public EndProminentPositionOS(String elementID, PositionOS position, EndPositionOS endPosition) {
		super(elementID, position);
		this.endPosition = endPosition;
	}
	
	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(endPosition);
		return listOfComponents;
	}	

}
