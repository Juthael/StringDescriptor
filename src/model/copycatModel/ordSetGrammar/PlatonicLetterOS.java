package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.MinimalLowerSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class PlatonicLetterOS extends NonMinimalRelevantLowerSetElement implements IPlatonicLetterOS {

	private static final String NAME = "platonicLetter";
	private MinimalLowerSetElement platonicLetterProperty;
	
	public PlatonicLetterOS(String elementID, MinimalLowerSetElement platonicLetterProperty) {
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
