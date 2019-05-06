package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;
import model.orderedSetModel.impl.AbstractNonMinimalExplicitOS;

public class SymmetryOS extends AbstractNonMinimalExplicitOS implements IOrderedSet {

	private static final String NAME = "symmetry";
	private MinimalOS symmetryProperty;
	
	public SymmetryOS(String elementID, MinimalOS symmetryProperty) {
		super(elementID);
		this.symmetryProperty = symmetryProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(symmetryProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!symmetryProperty.equals(idToIOrderedSet.get(symmetryProperty.getElementID())))
			symmetryProperty = (MinimalOS) idToIOrderedSet.get(symmetryProperty.getElementID());
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		if (symmetryProperty.getElementID().equals(Settings.SYMMETRY_WITHOUT_CENTRAL_ELEMENT))
			explicitID = "symm";
		else if (symmetryProperty.getElementID().equals(Settings.SYMMETRY_WITH_CENTRAL_ELEMENT))
			explicitID = "symm".concat("WithCenter");
		else explicitID = getElementID();
		return explicitID;
	}

}
