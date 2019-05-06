package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;
import model.orderedSetModel.impl.AbstractNonMinimalExplicitOS;

public class SizeOS extends AbstractNonMinimalExplicitOS implements IOrderedSet {

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
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!sizeProperty.equals(idToIOrderedSet.get(sizeProperty.getElementID())))
			sizeProperty = (MinimalOS) idToIOrderedSet.get(sizeProperty.getElementID());
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
