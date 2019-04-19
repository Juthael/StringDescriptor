package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class DimensionOS extends NonMinimalRelevantLowerSetElement implements IDimensionOS {

	private static final String NAME = "dimension";
	private MinimalLowerSetElement dimensionProperty;
	
	public DimensionOS(String elementID, MinimalLowerSetElement dimensionProperty) {
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
