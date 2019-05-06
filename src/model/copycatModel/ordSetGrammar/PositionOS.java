package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;
import model.orderedSetModel.impl.AbstractNonMinimalExplicitOS;
import model.orderedSetModel.IOrderedSet;

public class PositionOS extends AbstractNonMinimalExplicitOS implements WhichPositionTypeOS {

	private static final String NAME = "position";
	private MinimalOS positionProperty;
	
	public PositionOS(String elementID, MinimalOS positionProperty) {
		super(elementID);
		this.positionProperty = positionProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(positionProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!positionProperty.equals(idToIOrderedSet.get(positionProperty.getElementID())))
			positionProperty = (MinimalOS) idToIOrderedSet.get(positionProperty.getElementID());
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		StringBuilder sB = new StringBuilder();
		sB.append(NAME);
		sB.append("(");
		sB.append(positionProperty.getElementID());
		sB.append(")");
		explicitID = sB.toString();
		return explicitID;
	}		

}


