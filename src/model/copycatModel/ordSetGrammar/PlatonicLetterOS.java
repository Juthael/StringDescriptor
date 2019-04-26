package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalOS;
import model.orderedSetModel.impl.NonMinimalRelevantOS;

public class PlatonicLetterOS extends NonMinimalRelevantOS implements IPlatonicLetterOS {

	private static final String NAME = "platonicLetter";
	private MinimalOS platonicLetterProperty;
	
	public PlatonicLetterOS(String elementID, MinimalOS platonicLetterProperty) {
		super(elementID);
		this.platonicLetterProperty = platonicLetterProperty;
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(platonicLetterProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
