package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class EnumerationOS extends NonMinimalRelevantLowerSetElement implements ILowerSetElement {

	private static final String NAME = "enumeration";
	private MinimalLowerSetElement enumerationProperty;
	
	public EnumerationOS(String elementID, MinimalLowerSetElement enumerationProperty) {
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
