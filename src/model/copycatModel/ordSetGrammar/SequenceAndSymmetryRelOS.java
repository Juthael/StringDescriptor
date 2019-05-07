package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

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
		//HERE
		System.out.println("SET" + getElementID());
		List<String> listOfProperties = getListOfPropertiesWithPath();
		List<String> maxChains = getListOfLowerSetMaximalChains();
		System.out.println("Properties : ");
		for (String property : listOfProperties) {
			System.out.println(property);
		}
		System.out.println("");
		System.out.print("Max chains");
		for (String maxChain : maxChains) {
			System.out.println(maxChain);
		}
		System.out.println("");
		System.out.println("*******************");
		System.out.println("");
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(sequence);
		listOfComponents.add(symmetry);
		return listOfComponents;
	}
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!sequence.equals(idToIOrderedSet.get(sequence.getElementID())))
			sequence = (SequenceOS) idToIOrderedSet.get(sequence.getElementID());
		sequence.eliminateRedundancies(idToIOrderedSet);
		if (!symmetry.equals(idToIOrderedSet.get(symmetry.getElementID())))
			symmetry = (SymmetryOS) idToIOrderedSet.get(symmetry.getElementID());
		symmetry.eliminateRedundancies(idToIOrderedSet);
	}		

}
