package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class DirectionOS extends NonMinimalRelevantLowerSetElement implements IDirectionOS {

	private static final String NAME = "direction";
	private MinimalLowerSetElement directionProperty;
	
	public DirectionOS(String elementID, MinimalLowerSetElement directionProperty) {
		super(elementID);
		this.directionProperty = directionProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(directionProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
