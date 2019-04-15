package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.SetElementImpl;

public class GroupOS extends SetElementImpl implements HowManyGroupsOS {

	private SizeOS size;
	private PositionOS position;
	private RelationsOrLetterOS relationsOrLetter;
	
	public GroupOS(String elementID, SizeOS size, PositionOS position, RelationsOrLetterOS relationsOrLetter) {
		super(elementID);
		this.size = size;
		this.position = position;
		this.relationsOrLetter = relationsOrLetter;
	}

	public GroupOS(String elementID, boolean isCodingByDecomposition, SizeOS size, PositionOS position, 
			RelationsOrLetterOS relationsOrLetter) {
		super(elementID, isCodingByDecomposition);
		this.size = size;
		this.position = position;
		this.relationsOrLetter = relationsOrLetter;		
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
