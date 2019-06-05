package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

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
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (endPosition.getElementID().equals(orderedSet.getElementID()) && endPosition != orderedSet) {
			endPosition = (EndPositionOS) orderedSet;
		}
		else endPosition.eliminateRedundancies(orderedSet);
	}

	@Override
	public String getExplicitID() {
		return getElementID();
	}	

}
