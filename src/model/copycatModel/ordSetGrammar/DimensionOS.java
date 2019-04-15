package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.PropertyOSImpl;
import model.orderedSetModel.impl.SetElementImpl;

public class DimensionOS extends SetElementImpl implements HowManyDimensionsOS {

	PropertyOSImpl dimensionProperty;
	
	public DimensionOS(String elementID, PropertyOSImpl dimensionProperty) {
		super(elementID);
		this.dimensionProperty = dimensionProperty;
	}

	@Override
	protected List<IElement> buildListOfComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescriptorName() {
		// TODO Auto-generated method stub
		return null;
	}

}
