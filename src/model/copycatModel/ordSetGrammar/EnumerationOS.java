package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;
import model.orderedSetModel.impl.AbstractNonMinimalExplicitOS;

public class EnumerationOS extends AbstractNonMinimalExplicitOS implements IOrderedSet {

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
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!enumerationProperty.equals(idToIOrderedSet.get(enumerationProperty.getElementID())))
			enumerationProperty = (MinimalOS) idToIOrderedSet.get(enumerationProperty.getElementID());
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
