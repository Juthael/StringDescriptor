package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class GroupOS extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "group";
	private SizeOS size;
	private WhichPositionTypeOS positionType;
	private RelationsOrLetterOS relationsOrLetter;
	
	public GroupOS(String elementID, SizeOS size, WhichPositionTypeOS positionType, 
			RelationsOrLetterOS relationsOrLetter) {
		super(elementID);
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;
	}

	public GroupOS(String elementID, boolean isCodingByDecomposition, SizeOS size, WhichPositionTypeOS positionType, 
			RelationsOrLetterOS relationsOrLetter) {
		super(elementID, isCodingByDecomposition);
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;		
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		listOfComponents.add(positionType);
		listOfComponents.add(relationsOrLetter);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}

}
