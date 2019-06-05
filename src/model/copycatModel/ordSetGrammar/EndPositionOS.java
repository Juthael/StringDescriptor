package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class EndPositionOS extends NonMinimalExplicitOS implements IOrderedSet {

	private static final String NAME = "endPosition";
	private MinimalOS endPositionProperty;
	
	public EndPositionOS(String elementID, MinimalOS endPositionProperty) {
		super(elementID);
		this.endPositionProperty = endPositionProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
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
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (endPositionProperty.getElementID().equals(orderedSet.getElementID()) && endPositionProperty != orderedSet)
			endPositionProperty = (MinimalOS) orderedSet;
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		if (endPositionProperty.getElementID().equals(Settings.FIRST_POSITION))
			explicitID = NAME.concat("(first)");
		else if (endPositionProperty.getElementID().equals(Settings.LAST_POSITION))
			explicitID = NAME.concat("(last)");
		else explicitID = getElementID();
		return explicitID;
	}	

}
