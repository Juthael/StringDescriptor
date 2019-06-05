package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalExplicitOS;
import model.orderedSetModel.impl.MinimalOS;
import settings.Settings;

public class PlatonicLetterOS extends NonMinimalExplicitOS implements IOrderedSet {

	private static final String NAME = "platonicLetter";
	private MinimalOS platonicLetterProperty;
	
	public PlatonicLetterOS(String elementID, MinimalOS platonicLetterProperty) {
		super(elementID);
		this.platonicLetterProperty = platonicLetterProperty;
		if (Settings.MAKE_ELEMENT_ID_MORE_EXPLICIT)
			setElementID(getExplicitID());
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
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (platonicLetterProperty.getElementID().equals(orderedSet.getElementID()) && platonicLetterProperty != orderedSet)
			platonicLetterProperty = (MinimalOS) orderedSet;
	}

	@Override
	public String getExplicitID() {
		String explicitID;
		StringBuilder sB = new StringBuilder();
		int asciiLetterValue = Integer.parseInt(platonicLetterProperty.getElementID()) + 64;
		char character = (char) asciiLetterValue;
		sB.append(NAME);
		sB.append("(");
		sB.append(character);
		sB.append(")");
		explicitID = sB.toString();
		return explicitID;
	}		

}
