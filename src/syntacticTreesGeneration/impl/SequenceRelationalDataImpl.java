package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.DescriptorsBuilderException;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.ISequenceRelationalData;

public class SequenceRelationalDataImpl extends EnumerationRelationalDataImpl implements IRelationalData, ISequenceRelationalData {

	private final String commonDifference;
	
	public SequenceRelationalDataImpl(String dimensionValue, String enumerationValue, String commonDifference) 
			throws DescriptorsBuilderException {
		super(dimensionValue, enumerationValue);
		if (!commonDifference.isEmpty()) {
			changeName();
			this.commonDifference = commonDifference;
		}
		else throw new DescriptorsBuilderException("SequenceRelationalData : empty parameter.");
	}
	
	public SequenceRelationalDataImpl(ISequenceRelationalData sequenceRelationalData) {
		super((IEnumerationRelationalData) sequenceRelationalData);
		changeName();
		this.commonDifference = sequenceRelationalData.getCommonDifference();
	}
	
	@Override
	public String getCommonDifference() {
		return commonDifference;
	}
	
	@Override
	public void addDimensions(List<String> dimensions) {
		listOfDimensions.addAll(dimensions);
	}	
	
	private void changeName() {
		super.relationName = "sequence";
	}

}
