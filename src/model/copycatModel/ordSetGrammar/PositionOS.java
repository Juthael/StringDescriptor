package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class PositionOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "position";
	private MinimalSetElement positionProperty;
	
	public PositionOS(String elementID, MinimalSetElement positionProperty) {
		super(elementID);
		this.positionProperty = positionProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(positionProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}


