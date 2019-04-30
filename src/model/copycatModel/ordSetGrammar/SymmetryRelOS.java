package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;

public class SymmetryRelOS extends RelationOS implements HowManyRelationsOS {

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

}
