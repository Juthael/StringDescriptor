package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public class SymmetryRelOS extends RelationOS implements IOrderedSet {

	private SymmetryOS symmetry;
	
	public SymmetryRelOS(String elementID, DimensionOS dimension, EnumerationOS enumeration, SymmetryOS symmetry) {
		super(elementID, dimension, enumeration);
		this.symmetry = symmetry;
	}
	
	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(symmetry);
		return listOfComponents;
	}	
	
	@Override
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!symmetry.equals(idToIOrderedSet.get(symmetry.getElementID())))
			symmetry = (SymmetryOS) idToIOrderedSet.get(symmetry.getElementID());
		symmetry.eliminateRedundancies(idToIOrderedSet);
	}	

}
