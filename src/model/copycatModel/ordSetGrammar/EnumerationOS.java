package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class EnumerationOS extends NonMinimalRelevantOS implements IOrderedSet {

	private static final String NAME = "enumeration";
	private MinimalOS enumerationProperty;
	
	public EnumerationOS(String elementID, MinimalOS enumerationProperty) {
		super(elementID);
		this.enumerationProperty = enumerationProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(enumerationProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
