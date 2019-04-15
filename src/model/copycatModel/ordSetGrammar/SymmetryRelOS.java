package model.copycatModel.ordSetGrammar;

public class SymmetryRelOS extends RelationOS implements HowManyRelationsOS {

	private SymmetryOS symmetry;
	
	public SymmetryRelOS(String elementID, DimensionOS dimension, EnumerationOS enumeration, SymmetryOS symmetry) {
		super(elementID, dimension, enumeration);
		this.symmetry = symmetry;
	}

}
