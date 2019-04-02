package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

public class SequenceRel extends Relation implements Cloneable {

	private Sequence sequence;
	
	public SequenceRel(boolean codingDescriptor, HowManyDimensions dimensionX, Enumeration enumeration, Sequence sequence) {
		super(codingDescriptor, dimensionX, enumeration);
		this.sequence = sequence;			
	}
	
	@Override
	protected SequenceRel clone() throws CloneNotSupportedException {
		SequenceRel cloneSequenceRel;
		HowManyDimensions cloneDimensionX;
		switch (dimensionX.getDescriptorName()) {
			case "dimension" :
				Dimension dimensionCasted = (Dimension) dimensionX;
				cloneDimensionX = dimensionCasted.clone();
				break;
			case "dimensionX2" :
				DimensionX2 dimensionX2Casted = (DimensionX2) dimensionX;
				cloneDimensionX = dimensionX2Casted.clone();
				break;
			case "dimensionX3" :
				DimensionX3 dimensionX3Casted = (DimensionX3) dimensionX;
				cloneDimensionX = dimensionX3Casted.clone();
				break;
			default : throw new CloneNotSupportedException("Relation : error in clone() method.");
		}			
		Enumeration cloneEnumeration = this.enumeration.clone();
		Sequence cloneSequence = sequence.clone();
		cloneSequenceRel = new SequenceRel(isCodingDescriptor, cloneDimensionX, cloneEnumeration, 
				cloneSequence);
		return cloneSequenceRel;
	}	
	
	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(dimensionX, enumeration, sequence));
		return componentDescriptors;
	}	
	
	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfRelevantComponentsForRelationBuilding() {
		ArrayList<AbstractDescriptorV1> listOfRelevantComponents = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(dimensionX, sequence));
		return listOfRelevantComponents;
	}	

}
