package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.PropertyOSImpl;
import model.orderedSetModel.impl.SetElementImpl;

public class PlatonicLetterOS extends SetElementImpl implements ISetElement {

	private PropertyOSImpl platonicLetterProperty;
	
	public PlatonicLetterOS(String elementID, PropertyOSImpl platonicLetterProperty) {
		super(elementID);
		this.platonicLetterProperty = platonicLetterProperty;
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
