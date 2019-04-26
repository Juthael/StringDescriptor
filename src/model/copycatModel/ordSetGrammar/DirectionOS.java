package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class DirectionOS extends NonMinimalRelevantOS implements IDirectionOS {

	private static final String NAME = "direction";
	private MinimalOS directionProperty;
	
	public DirectionOS(String elementID, MinimalOS directionProperty) {
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
