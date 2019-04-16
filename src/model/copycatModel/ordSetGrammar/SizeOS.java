package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class SizeOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "size";
	private MinimalSetElement sizeProperty;
	
	public SizeOS(String elementID, MinimalSetElement sizeProperty) {
		super(elementID);
		this.sizeProperty = sizeProperty;
	}

	@Override
	protected List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(sizeProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
