package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class SizeOS extends NonMinimalRelevantOS implements ISizeOS {

	private static final String NAME = "size";
	private MinimalOS sizeProperty;
	
	public SizeOS(String elementID, MinimalOS sizeProperty) {
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
