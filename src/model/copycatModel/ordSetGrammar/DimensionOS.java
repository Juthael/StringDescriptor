package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class DimensionOS extends NonMinimalRelevantOS implements IOrderedSet {

	private static final String NAME = "dimension";
	private MinimalOS dimensionProperty;
	
	public DimensionOS(String elementID, MinimalOS dimensionProperty) {
		super(elementID);
		this.dimensionProperty = dimensionProperty;
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

}
