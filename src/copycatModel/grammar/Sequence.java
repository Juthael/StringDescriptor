package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class Sequence extends SynTreeIntegrableElementImpl implements Cloneable {

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
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(commonDiff, absCommonDiff));
		return componentDescriptors;
	}
	
	protected List<SynTreeIntegrableElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(commonDiff));
		return componentDescriptors;
	}	
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
