package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;

public class SequenceRel extends Relation implements Cloneable {

	private Sequence sequence;
	
	public SequenceRel(boolean codingDescriptor, Dimension dimension, Enumeration enumeration, 
			Sequence sequence) {
		super(codingDescriptor, dimension, enumeration);
		this.sequence = sequence;			
	}
	
	@Override
	protected SequenceRel clone() throws CloneNotSupportedException {
		SequenceRel cloneSequenceRel;
		Dimension cloneDimension = dimension.clone();
		Enumeration cloneEnumeration = this.enumeration.clone();
		Sequence cloneSequence = sequence.clone();
		cloneSequenceRel = new SequenceRel(isCodingDescriptor, cloneDimension, cloneEnumeration, 
				cloneSequence);
		return cloneSequenceRel;
	}	
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(dimension, enumeration, sequence));
		return componentDescriptors;
	}	
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeIntegrableElementImpl> listOfRelevantComponents = buildListOfComponents();
		return listOfRelevantComponents;
	}	

}
