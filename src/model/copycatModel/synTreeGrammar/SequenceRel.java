package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.DimensionOS;
import model.copycatModel.ordSetGrammar.EnumerationOS;
import model.copycatModel.ordSetGrammar.SequenceOS;
import model.copycatModel.ordSetGrammar.SequenceRelOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class SequenceRel extends Relation implements ISynTreeElement, Cloneable {

	private Sequence sequence;
	
	public SequenceRel(Dimension dimension, Enumeration enumeration, 
			Sequence sequence) {
		super(dimension, enumeration);
		this.sequence = sequence;			
	}
	
	@Override
	protected SequenceRel clone() throws CloneNotSupportedException {
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
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> listOfRelevantComponents = new ArrayList<SynTreeElementImpl>();
		for (IElement component : getListOfComponents())
			listOfRelevantComponents.add((SynTreeElementImpl) component);
		return listOfRelevantComponents;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
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

}
