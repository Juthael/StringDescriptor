package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;

public class SequenceAndSymmetryRelOS extends RelationOS implements HowManyRelationsOS {

	private SequenceOS sequence;
	private ISymmetryOS symmetry;
	
	public SequenceAndSymmetryRelOS(String elementID, IDimensionOS dimension, IEnumerationOS enumeration, 
			SequenceOS sequence, ISymmetryOS symmetry) {
		super(elementID, dimension, enumeration);
		this.sequence = sequence;
		this.symmetry = symmetry;
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(sequence);
		listOfComponents.add(symmetry);
		return listOfComponents;
	}		

}
