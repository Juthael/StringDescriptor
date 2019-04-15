package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.PropertyOSImpl;
import model.orderedSetModel.impl.SetElementImpl;

public class DirectionOS extends SetElementImpl implements ISetElement {

	private PropertyOSImpl directionProperty;
	
	public DirectionOS(String elementID, PropertyOSImpl directionProperty) {
		super(elementID);
		this.directionProperty = directionProperty;
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
