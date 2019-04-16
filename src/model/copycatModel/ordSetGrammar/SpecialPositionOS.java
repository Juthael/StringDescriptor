package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;

public class SpecialPositionOS extends PositionOS implements ISetElement {

	private MinimalSetElement specialPositionProperty;
	
	public SpecialPositionOS(String elementID, MinimalSetElement positionProperty, MinimalSetElement specialPositionProperty) {
		super(elementID, positionProperty);
		this.specialPositionProperty = specialPositionProperty;
	}
	
	@Override
	protected List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(specialPositionProperty);
		return listOfComponents;
	}

}
