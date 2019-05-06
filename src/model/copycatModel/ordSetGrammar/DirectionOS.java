package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;
import model.orderedSetModel.impl.AbstractNonMinimalExplicitOS;

public class DirectionOS extends AbstractNonMinimalExplicitOS implements IOrderedSet {

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
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!directionProperty.equals(idToIOrderedSet.get(directionProperty.getElementID())))
			directionProperty = (MinimalOS) idToIOrderedSet.get(directionProperty.getElementID());
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
