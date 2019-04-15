package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.generalModel.IElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class SequenceAndSymmetryRel extends Relation implements ISynTreeElement, Cloneable {

	private Sequence sequence;
	private Symmetry symmetry;
	
	public SequenceAndSymmetryRel(Dimension dimension, Enumeration enumeration, 
			Sequence sequence, Symmetry symmetry) {
		super(dimension, enumeration);
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
		cloneSequenceAndSymmetryRel = new SequenceAndSymmetryRel(cloneDimension, 
				cloneEnumeration, cloneSequence, cloneSymmetry);
		return cloneSequenceAndSymmetryRel;
	}
	
	@Override
	protected List<IElement> buildListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(dimension, enumeration, sequence, symmetry));
		return componentDescriptors;
	}	
	
	@Override
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> listOfRelevantComponents = new ArrayList<SynTreeElementImpl>(
				Arrays.asList(dimension, sequence));
		return listOfRelevantComponents;
	}	

}
