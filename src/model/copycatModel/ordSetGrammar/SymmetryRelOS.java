package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;

public class SymmetryRelOS extends RelationOS implements HowManyRelationsOS {

	private ISymmetryOS symmetry;
	
	public SymmetryRelOS(String elementID, IDimensionOS dimension, IEnumerationOS enumeration, ISymmetryOS symmetry) {
		super(elementID, dimension, enumeration);
		this.symmetry = symmetry;
	}
	
	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(symmetry);
		return listOfComponents;
	}	

}
