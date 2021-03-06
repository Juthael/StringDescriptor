package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class DimensionOS extends NonMinimalExplicitOS implements IOrderedSet {

	private static final String NAME = "dimension";
	private MinimalOS dimensionProperty;
	
	public DimensionOS(String elementID, MinimalOS dimensionProperty) {
		super(elementID);
		this.dimensionProperty = dimensionProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(dimensionProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (dimensionProperty.getElementID().equals(orderedSet.getElementID()) && dimensionProperty != orderedSet)
			dimensionProperty = (MinimalOS) orderedSet;
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		StringBuilder sB = new StringBuilder();
		sB.append(NAME);
		sB.append("(");
		String[] dimensionArray = dimensionProperty.getElementID().split(">");
		for (int j=0 ; j<dimensionArray.length ; j++) {
			String dimensionPart = dimensionArray[j];
			String[] dimensionSubArray = dimensionPart.split("\\.");
			int subStringEnd;
			for (int i=0 ; i<dimensionSubArray.length ; i++) {
				if (dimensionSubArray[i].length() < 3)
					subStringEnd = dimensionSubArray[i].length();
				else subStringEnd = 3;
				sB.append(dimensionSubArray[i].substring(0, subStringEnd));
				if (i < dimensionSubArray.length - 1)
					sB.append(".");
			}
			if (j < dimensionArray.length - 1)
				sB.append(">");
		}
		sB.append(")");
		explicitID = sB.toString();
		return explicitID;
	}	

}
