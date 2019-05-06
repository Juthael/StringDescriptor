package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;
import model.orderedSetModel.impl.AbstractNonMinimalExplicitOS;

public class CommonDiffOS extends AbstractNonMinimalExplicitOS implements IOrderedSet {

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
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!commonDiffProperty.equals(idToIOrderedSet.get(commonDiffProperty.getElementID())))
			commonDiffProperty = (MinimalOS) idToIOrderedSet.get(commonDiffProperty.getElementID());
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
