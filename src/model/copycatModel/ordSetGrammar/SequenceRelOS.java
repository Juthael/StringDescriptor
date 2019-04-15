package model.copycatModel.ordSetGrammar;

public class SequenceRelOS extends RelationOS implements HowManyRelationsOS {

	private SequenceOS sequence;
	
	public SequenceRelOS(String elementID, DimensionOS dimension, EnumerationOS enumeration, SequenceOS sequence) {
		super(elementID, dimension, enumeration);
		this.sequence = sequence;
	}

}
