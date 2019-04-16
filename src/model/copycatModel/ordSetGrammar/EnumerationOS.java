package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class EnumerationOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "enumeration";
	private MinimalSetElement enumerationProperty;
	
	public EnumerationOS(String elementID, MinimalSetElement enumerationProperty) {
		super(elementID);
		this.enumerationProperty = enumerationProperty;
	}

	@Override
	protected List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(enumerationProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
