package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class SizeOS extends NonMinimalRelevantLowerSetElement implements ILowerSetElement {

	private static final String NAME = "size";
	private MinimalLowerSetElement sizeProperty;
	
	public SizeOS(String elementID, MinimalLowerSetElement sizeProperty) {
		super(elementID);
		this.sizeProperty = sizeProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(sizeProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
