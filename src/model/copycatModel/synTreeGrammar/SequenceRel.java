package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.DimensionOS;
import model.copycatModel.ordSetGrammar.EnumerationOS;
import model.copycatModel.ordSetGrammar.SequenceOS;
import model.copycatModel.ordSetGrammar.SequenceRelOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.GrammaticalST;
import settings.Settings;

public class SequenceRel extends Relation implements IGrammaticalST, Cloneable {

	private Sequence sequence;
	
	public SequenceRel(Dimension dimension, Enumeration enumeration, 
			Sequence sequence) {
		super(dimension, enumeration);
		this.sequence = sequence;
		setHashCode();
	}
	
	@Override
	public SequenceRel clone() throws CloneNotSupportedException {
		SequenceRel cloneSequenceRel;
		Dimension cloneDimension = dimension.clone();
		Enumeration cloneEnumeration = this.enumeration.clone();
		Sequence cloneSequence = sequence.clone();
		cloneSequenceRel = new SequenceRel(cloneDimension, cloneEnumeration, cloneSequence);
		return cloneSequenceRel;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(dimension, enumeration, sequence));
		return componentDescriptors;
	}	
	
	@Override
	protected List<IGrammaticalST> buildListOfRelevantComponentsForRelationBuilding() {
		List<IGrammaticalST> listOfRelevantComponents = new ArrayList<IGrammaticalST>();
		for (IElement component : getListOfComponents())
			listOfRelevantComponents.add((GrammaticalST) component);
		return listOfRelevantComponents;
	}	
	
	@Override
	public boolean getThisRelationIsUpgradable() {
		return ((sequence.getThisIsAConstantSequence() == false) || Settings.CONSTANT_SEQUENCES_CAN_BE_UPGRADED_TO_SETS);
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) throws OrderedSetsGenerationException {
		if (getThisRelationIsUpgradable() == true) {
			IOrderedSet sequenceRelOS;
			List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
			Integer sequenceRelIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
			String sequenceRelID = getDescriptorName().concat(sequenceRelIndex.toString());
			DimensionOS dimensionOS = (DimensionOS) dimension.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			EnumerationOS enumerationOS = (EnumerationOS) enumeration.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			SequenceOS sequenceOS = (SequenceOS) sequence.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			sequenceRelOS = new SequenceRelOS(sequenceRelID, dimensionOS, enumerationOS, sequenceOS);
			return sequenceRelOS;	
		}
		else throw new OrderedSetsGenerationException("SequenceRelation.upgradeAsTheElementOfAnOrderedSet() : "
				+ "this SequenceRel can't be upgraded.");
	}		

}
