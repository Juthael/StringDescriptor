package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class CommonDiffOS extends NonMinimalExplicitOS implements IOrderedSet {

	private static final String NAME = "commonDiff";
	private MinimalOS commonDiffProperty;
	
	public CommonDiffOS(String elementID, MinimalOS commonDiffProperty) {
		super(elementID);
		this.commonDiffProperty = commonDiffProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(commonDiffProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (commonDiffProperty.getElementID().equals(orderedSet.getElementID()) && commonDiffProperty != orderedSet)
			commonDiffProperty = (MinimalOS) orderedSet;
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		StringBuilder sB = new StringBuilder();
		sB.append(NAME);
		sB.append("(");
		sB.append(commonDiffProperty.getElementID());
		sB.append(")");
		explicitID = sB.toString();
		return explicitID;
	}	
	
	public String getCommonDiffValue() {
		return commonDiffProperty.getElementID();
	}

}
