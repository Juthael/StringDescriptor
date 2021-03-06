package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public class SequenceAndSymmetryRelOS extends RelationOS implements IOrderedSet {

	private SequenceOS sequence;
	private SymmetryOS symmetry;
	
	public SequenceAndSymmetryRelOS(String elementID, DimensionOS dimension, EnumerationOS enumeration, 
			SequenceOS sequence, SymmetryOS symmetry) {
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
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (sequence.getElementID().equals(orderedSet.getElementID()) && sequence != orderedSet) {
			sequence = (SequenceOS) orderedSet;
		}
		else sequence.eliminateRedundancies(orderedSet);
		if (symmetry.getElementID().equals(orderedSet.getElementID()) && symmetry != orderedSet) {
			symmetry = (SymmetryOS) orderedSet;
		}
		else symmetry.eliminateRedundancies(orderedSet);
	}		

}
