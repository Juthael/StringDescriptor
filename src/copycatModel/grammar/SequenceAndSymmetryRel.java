package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;

public class SequenceAndSymmetryRel extends Relation implements Cloneable {

	private Sequence sequence;
	private Symmetry symmetry;
	
	public SequenceAndSymmetryRel(boolean codingDescriptor, HowManyDimensions dimensionX, Enumeration enumeration, 
			Sequence sequence, Symmetry symmetry) {
		super(codingDescriptor, dimensionX, enumeration);
		this.sequence = sequence;
		this.symmetry = symmetry;				
	}
	
	@Override
	protected SequenceAndSymmetryRel clone() throws CloneNotSupportedException {
		SequenceAndSymmetryRel cloneSequenceAndSymmetryRel;
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
		Symmetry cloneSymmetry = symmetry.clone();
		cloneSequenceAndSymmetryRel = new SequenceAndSymmetryRel(isCodingDescriptor, 
				cloneDimensionX, cloneEnumeration, cloneSequence, cloneSymmetry);
		return cloneSequenceAndSymmetryRel;
	}
	
	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(dimensionX, enumeration, sequence, symmetry));
		return componentDescriptors;
	}	
	
	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfRelevantComponentsForRelationBuilding() {
		ArrayList<AbstractDescriptorV1> listOfRelevantComponents = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(dimensionX, sequence));
		return listOfRelevantComponents;
	}	

}
