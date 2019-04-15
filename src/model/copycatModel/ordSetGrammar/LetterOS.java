package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.SetElementImpl;

public class LetterOS extends SetElementImpl implements RelationsOrLetterOS {

	private PositionOS position;
	private PlatonicLetterOS platonicLetter;
	
	public LetterOS(String elementID, PositionOS position, PlatonicLetterOS platonicLetter) {
		super(elementID);
		this.position = position;
		this.platonicLetter = platonicLetter;
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
