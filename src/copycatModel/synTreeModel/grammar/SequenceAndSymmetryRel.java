package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;

public class SequenceAndSymmetryRel extends Relation implements Cloneable {

	private Sequence sequence;
	private Symmetry symmetry;
	
	public SequenceAndSymmetryRel(boolean codingDescriptor, Dimension dimension, Enumeration enumeration, 
			Sequence sequence, Symmetry symmetry) {
		super(codingDescriptor, dimension, enumeration);
		this.sequence = sequence;
		this.symmetry = symmetry;				
	}
	
	@Override
	protected SequenceAndSymmetryRel clone() throws CloneNotSupportedException {
		SequenceAndSymmetryRel cloneSequenceAndSymmetryRel;
		Dimension cloneDimension = dimension.clone();
		Enumeration cloneEnumeration = enumeration.clone();
		Sequence cloneSequence = sequence.clone();
		Symmetry cloneSymmetry = symmetry.clone();
		cloneSequenceAndSymmetryRel = new SequenceAndSymmetryRel(isCodingByDecomposition, 
				cloneDimension, cloneEnumeration, cloneSequence, cloneSymmetry);
		return cloneSequenceAndSymmetryRel;
	}
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(dimension, enumeration, sequence, symmetry));
		return componentDescriptors;
	}	
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeIntegrableElementImpl> listOfRelevantComponents = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(dimension, sequence));
		return listOfRelevantComponents;
	}	

}
