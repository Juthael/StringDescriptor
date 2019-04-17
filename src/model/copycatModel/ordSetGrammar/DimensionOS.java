package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class DimensionOS extends NonMinimalRelevantSetElement implements HowManyDimensionsOS {

	private static final String NAME = "dimension";
	private MinimalSetElement dimensionProperty;
	
	public DimensionOS(String elementID, MinimalSetElement dimensionProperty) {
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
