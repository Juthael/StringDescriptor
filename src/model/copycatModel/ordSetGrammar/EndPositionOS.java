package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;
import model.orderedSetModel.impl.AbstractNonMinimalExplicitOS;

public class EndPositionOS extends AbstractNonMinimalExplicitOS implements IOrderedSet {

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
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!endPositionProperty.equals(idToIOrderedSet.get(endPositionProperty.getElementID())))
			endPositionProperty = (MinimalOS) idToIOrderedSet.get(endPositionProperty.getElementID());
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
