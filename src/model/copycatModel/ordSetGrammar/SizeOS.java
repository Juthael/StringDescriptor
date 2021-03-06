package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class SizeOS extends NonMinimalExplicitOS implements IOrderedSet {

	private static final String NAME = "size";
	private MinimalOS sizeProperty;
	
	public SizeOS(String elementID, MinimalOS sizeProperty) {
		super(elementID);
		this.sizeProperty = sizeProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(sizeProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (sizeProperty.getElementID().equals(orderedSet.getElementID()) && sizeProperty != orderedSet)
			sizeProperty = (MinimalOS) orderedSet;
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		StringBuilder sB = new StringBuilder();
		sB.append(NAME);
		sB.append("(");
		sB.append(sizeProperty.getElementID());
		sB.append(")");
		explicitID = sB.toString();
		return explicitID;
	}	

}
