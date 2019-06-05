package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class PositionOS extends NonMinimalExplicitOS implements WhichPositionTypeOS {

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
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (positionProperty.getElementID().equals(orderedSet.getElementID()) && positionProperty != orderedSet)
			positionProperty = (MinimalOS) orderedSet;
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


