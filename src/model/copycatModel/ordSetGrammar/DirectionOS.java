package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class DirectionOS extends NonMinimalExplicitOS implements IOrderedSet {

	private static final String NAME = "direction";
	private MinimalOS directionProperty;
	
	public DirectionOS(String elementID, MinimalOS directionProperty) {
		super(elementID);
		this.directionProperty = directionProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
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
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (directionProperty.getElementID().equals(orderedSet.getElementID()) && directionProperty != orderedSet)
			directionProperty = (MinimalOS) orderedSet;
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		if (directionProperty.getElementID().equals(Settings.LEFT_TO_RIGHT))
			explicitID = NAME.concat("(leftToRight)");
		else if (directionProperty.getElementID().equals(Settings.RIGHT_TO_LEFT))
			explicitID = NAME.concat("(rightToLeft)");
		else explicitID = getElementID();
		return explicitID;
	}		

}
