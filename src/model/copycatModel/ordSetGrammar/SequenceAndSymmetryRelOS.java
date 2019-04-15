package model.copycatModel.ordSetGrammar;

import java.util.List;

public class SequenceAndSymmetryRelOS extends RelationOS implements HowManyRelationsOS {

	private SequenceOS sequence;
	private SymmetryOS symmetry;
	
	public SequenceAndSymmetryRelOS(String elementID, DimensionOS dimension, EnumerationOS enumeration, SequenceOS sequence, 
			SymmetryOS symmetry) {
		super(elementID, dimension, enumeration);
		this.sequence = sequence;
		this.symmetry = symmetry;
	}

}
