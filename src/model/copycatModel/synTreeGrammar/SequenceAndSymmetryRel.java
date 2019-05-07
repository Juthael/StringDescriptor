package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.DimensionOS;
import model.copycatModel.ordSetGrammar.EnumerationOS;
import model.copycatModel.ordSetGrammar.SequenceAndSymmetryRelOS;
import model.copycatModel.ordSetGrammar.SequenceOS;
import model.copycatModel.ordSetGrammar.SymmetryOS;
import model.copycatModel.ordSetGrammar.SymmetryRelOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

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
	public boolean getThisRelationIsUpgradable() {
		return ((sequence.getThisIsAConstantSequence() == false) || 
				(Settings.ENUM_OF_IDENTICAL_ELEMENTS_CAN_NEVER_BE_UPGRADED == false));
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet relationOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer sequenceAndSymmetryRelIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String relationID = getDescriptorName().concat(sequenceAndSymmetryRelIndex.toString());
		DimensionOS dimensionOS = (DimensionOS) dimension.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		EnumerationOS enumerationOS = (EnumerationOS) enumeration.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		SymmetryOS symmetryOS = (SymmetryOS) symmetry.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		if (sequence.getThisIsAConstantSequence() == false || Settings.CONSTANT_SEQUENCES_CAN_BE_UPGRADED_TO_SETS) {
			SequenceOS sequenceOS = (SequenceOS) sequence.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			relationOS = 
					new SequenceAndSymmetryRelOS(relationID, dimensionOS, enumerationOS, sequenceOS, symmetryOS);	
		}
		else {
			relationOS = new SymmetryRelOS(relationID, dimensionOS, enumerationOS, symmetryOS);
		}
		return relationOS;
	}		

}
