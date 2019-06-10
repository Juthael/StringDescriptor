package syntacticTreesGeneration.impl;

import exceptions.SynTreeGenerationException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.ISequenceRelationalData;

public class SequenceRelationalData extends EnumerationRelationalData implements IRelationalData, ISequenceRelationalData {

	private final String commonDifference;
	
	public SequenceRelationalData(String dimensionValue, String enumerationValue, String commonDifference) 
			throws SynTreeGenerationException {
		super(dimensionValue, enumerationValue);
		if (!commonDifference.isEmpty()) {
			changeName();
			this.commonDifference = commonDifference;
		}
		else throw new SynTreeGenerationException("SequenceRelationalData : empty parameter.");
	}
	
	public SequenceRelationalData(ISequenceRelationalData sequenceRelationalData) {
		super((IEnumerationRelationalData) sequenceRelationalData);
		changeName();
		this.commonDifference = sequenceRelationalData.getCommonDifference();
	}
	
	@Override
	public String getCommonDifference() {
		return commonDifference;
	}	
	
	private void changeName() {
		super.relationName = "sequence";
	}

}
