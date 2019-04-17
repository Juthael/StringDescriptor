package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class EndPositionOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "endPosition";
	private MinimalSetElement endPositionProperty;
	
	public EndPositionOS(String elementID, MinimalSetElement endPositionProperty) {
		super(elementID);
		this.endPositionProperty = endPositionProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(endPositionProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
