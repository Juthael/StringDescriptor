package model.copycatModel.ordSetGrammar;

import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.PropertyOSImpl;

public class SpecialPositionOS extends PositionOS implements ISetElement {

	private PropertyOSImpl specialPositionProperty;
	
	public SpecialPositionOS(String elementID, PropertyOSImpl positionProperty, PropertyOSImpl specialPositionProperty) {
		super(elementID, positionProperty);
		this.specialPositionProperty = specialPositionProperty;
	}

}
