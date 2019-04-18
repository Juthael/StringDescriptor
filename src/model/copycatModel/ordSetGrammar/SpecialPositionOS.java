package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;

public class SpecialPositionOS extends PositionOS implements ILowerSetElement {

	private MinimalLowerSetElement specialPositionProperty;
	
	public SpecialPositionOS(String elementID, MinimalLowerSetElement positionProperty, MinimalLowerSetElement specialPositionProperty) {
		super(elementID, positionProperty);
		this.specialPositionProperty = specialPositionProperty;
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(specialPositionProperty);
		return listOfComponents;
	}

}
