package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationalDataInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;

public class SequenceRelationalDataV1 extends EnumerationRelationalDataV1 implements RelationalDataInterface, SequenceRelationalDataInterface {

	private final String commonDifference;
	
	public SequenceRelationalDataV1(String dimensionValue, String enumerationValue, String commonDifference) 
			throws DescriptorsBuilderCriticalException {
		super(dimensionValue, enumerationValue);
		if (!commonDifference.isEmpty()) {
			changeName();
			this.commonDifference = commonDifference;
		}
		else throw new DescriptorsBuilderCriticalException("SequenceRelationalData : empty parameter.");
	}
	
	public SequenceRelationalDataV1(SequenceRelationalDataInterface sequenceRelationalData) {
		super((EnumerationRelationalDataInterface) sequenceRelationalData);
		changeName();
		this.commonDifference = sequenceRelationalData.getCommonDifference();
	}
	
	@Override
	public String getCommonDifference() {
		return commonDifference;
	}
	
	@Override
	public void addDimensions(ArrayList<String> dimensions) {
		listOfDimensions.addAll(dimensions);
	}	
	
	private void changeName() {
		super.relationName = "sequence";
	}

}
