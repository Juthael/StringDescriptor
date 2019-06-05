package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class EnumerationOS extends NonMinimalExplicitOS implements IOrderedSet {

	private static final String NAME = "enumeration";
	private MinimalOS enumerationProperty;
	
	public EnumerationOS(String elementID, MinimalOS enumerationProperty) {
		super(elementID);
		this.enumerationProperty = enumerationProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(enumerationProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (enumerationProperty.getElementID().equals(orderedSet.getElementID()) && enumerationProperty != orderedSet)
			enumerationProperty = (MinimalOS) orderedSet;
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		StringBuilder sB = new StringBuilder();
		sB.append(NAME);
		sB.append("(");
		sB.append(enumerationProperty.getElementID());
		sB.append(")");
		explicitID = sB.toString();
		return explicitID;
	}		

}
