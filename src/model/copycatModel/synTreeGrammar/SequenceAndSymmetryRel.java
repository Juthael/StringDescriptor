package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.IDimensionOS;
import model.copycatModel.ordSetGrammar.IEnumerationOS;
import model.copycatModel.ordSetGrammar.ISymmetryOS;
import model.copycatModel.ordSetGrammar.SequenceAndSymmetryRelOS;
import model.copycatModel.ordSetGrammar.SequenceOS;
import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
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
	public List<IElement> getListOfComponents(){
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
	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement sequenceAndSymmetryRelOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer sequenceAndSymmetryRelIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String sequenceAndSymmetryRelID = getDescriptorName().concat(sequenceAndSymmetryRelIndex.toString());
		IDimensionOS dimensionOS = (IDimensionOS) dimension.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		IEnumerationOS enumerationOS = (IEnumerationOS) enumeration.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		SequenceOS sequenceOS = (SequenceOS) sequence.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		ISymmetryOS symmetryOS = (ISymmetryOS) symmetry.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		sequenceAndSymmetryRelOS = 
				new SequenceAndSymmetryRelOS(sequenceAndSymmetryRelID, dimensionOS, enumerationOS, sequenceOS, symmetryOS);
		return sequenceAndSymmetryRelOS;		
	}		

}
