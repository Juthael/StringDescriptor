package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class SymmetryOS extends NonMinimalExplicitOS implements IOrderedSet {

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
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (symmetryProperty.getElementID().equals(orderedSet.getElementID()) && symmetryProperty != orderedSet)
			symmetryProperty = (MinimalOS) orderedSet;
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
