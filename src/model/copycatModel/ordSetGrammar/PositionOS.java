package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.PropertyOSImpl;
import model.orderedSetModel.impl.SetElementImpl;

public class PositionOS extends SetElementImpl implements ISetElement {

	private PropertyOSImpl positionProperty;
	
	public PositionOS(String elementID, PropertyOSImpl positionProperty) {
		super(elementID);
		this.positionProperty = positionProperty;
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
