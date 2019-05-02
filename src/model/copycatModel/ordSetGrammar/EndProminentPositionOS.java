package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

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
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!endPosition.equals(idToIOrderedSet.get(endPosition.getElementID()))) {
			endPosition = (EndPositionOS) idToIOrderedSet.get(endPosition.getElementID());
			endPosition.eliminateRedundancies(idToIOrderedSet);
		}
	}	

}
