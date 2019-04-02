package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

public class Sequence extends AbstractDescriptorV1 implements Cloneable {

	private static final String descriptorName = "sequence";
	private CommonDiff commonDiff;
	private AbsCommonDiff absCommonDiff;
	
	public Sequence(boolean codingDescriptor, CommonDiff commonDiff, AbsCommonDiff absCommonDiff) {
		super(codingDescriptor);
		this.commonDiff = commonDiff;
		this.absCommonDiff = absCommonDiff;	
	}
	
	@Override
	protected Sequence clone() throws CloneNotSupportedException {
		Sequence cloneSequence;
		CommonDiff cloneCommonDiff = commonDiff.clone();
		AbsCommonDiff cloneAbsCommonDiff = absCommonDiff.clone();
		cloneSequence = new Sequence(isCodingDescriptor, cloneCommonDiff, cloneAbsCommonDiff);
		return cloneSequence;
	}
	
	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(commonDiff, absCommonDiff));
		return componentDescriptors;
	}
	
	protected ArrayList<AbstractDescriptorV1> buildListOfRelevantComponentsForRelationBuilding() {
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(commonDiff));
		return componentDescriptors;
	}	
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
