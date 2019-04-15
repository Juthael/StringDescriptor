package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.PropertyOSImpl;
import model.orderedSetModel.impl.SetElementImpl;

public class EnumerationOS extends SetElementImpl implements ISetElement {

	PropertyOSImpl enumerationProperty;
	
	public EnumerationOS(String elementID, PropertyOSImpl enumerationProperty) {
		super(elementID);
		this.enumerationProperty = enumerationProperty;
	}

	@Override
	protected List<IElement> buildListOfComponents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescriptorName() {
		// TODO Auto-generated method stub
		return null;
	}

}
