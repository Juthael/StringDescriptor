package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public class SequenceRelOS extends RelationOS implements IOrderedSet {

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
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!sequence.equals(idToIOrderedSet.get(sequence.getElementID())))
			sequence = (SequenceOS) idToIOrderedSet.get(sequence.getElementID());
		sequence.eliminateRedundancies(idToIOrderedSet);
	}	

}
