package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class DirectionOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "direction";
	private MinimalSetElement directionProperty;
	
	public DirectionOS(String elementID, MinimalSetElement directionProperty) {
		super(elementID);
		this.directionProperty = directionProperty;
	}

	@Override
	protected List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(directionProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
