package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class AbsCommonDiffOS extends NonMinimalExplicitOS implements IOrderedSet {
	
	private static final String NAME = "absCommonDiff";
	private MinimalOS absCommonDiffProperty;

	public AbsCommonDiffOS(String elementID, MinimalOS absCommonDiffProperty) {
		super(elementID);
		this.absCommonDiffProperty = absCommonDiffProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(absCommonDiffProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (absCommonDiffProperty.getElementID().equals(orderedSet.getElementID()) && absCommonDiffProperty != orderedSet)
			absCommonDiffProperty = (MinimalOS) orderedSet;
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		StringBuilder sB = new StringBuilder();
		sB.append(NAME);
		sB.append("(");
		sB.append(absCommonDiffProperty.getElementID());
		sB.append(")");
		explicitID = sB.toString();
		return explicitID;
	}

}
