package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;

public class SequenceRelOS extends RelationOS implements HowManyRelationsOS {

	private SequenceOS sequence;
	
	public SequenceRelOS(String elementID, DimensionOS dimension, EnumerationOS enumeration, SequenceOS sequence) {
		super(elementID, dimension, enumeration);
		this.sequence = sequence;
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(sequence);
		return listOfComponents;
	}	

}
