package model.copycatModel.ordSetGrammar;

import java.util.List;

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
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (symmetry.getElementID().equals(orderedSet.getElementID()) && symmetry != orderedSet) {
			symmetry = (SymmetryOS) orderedSet;
		}
		else symmetry.eliminateRedundancies(orderedSet);
	}	

}
