package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.orderedSetModel.impl.MinimalSetElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class PlatonicLetterOS extends NonMinimalRelevantSetElement implements ISetElement {

	private static final String NAME = "platonicLetter";
	private MinimalSetElement platonicLetterProperty;
	
	public PlatonicLetterOS(String elementID, MinimalSetElement platonicLetterProperty) {
		super(elementID);
		this.platonicLetterProperty = platonicLetterProperty;
	}

	@Override
	protected List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(platonicLetterProperty);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
