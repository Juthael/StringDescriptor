package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class GroupOS extends NonMinimalRelevantSetElement implements HowManyGroupsOS {

	private static final String NAME = "group";
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
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		listOfComponents.add(position);
		listOfComponents.add(relationsOrLetter);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
