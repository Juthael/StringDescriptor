package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import model.copycatModel.ordSetGrammar.AbsCommonDiffOS;
import model.copycatModel.ordSetGrammar.CommonDiffOS;
import model.copycatModel.ordSetGrammar.SequenceOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.GrammaticalST;

public class Sequence extends GrammaticalST implements IGrammaticalST, Cloneable {

	private static final String DESCRIPTOR_NAME = "sequence";
	private CommonDiff commonDiff;
	private AbsCommonDiff absCommonDiff;
	
	public Sequence(CommonDiff commonDiff, AbsCommonDiff absCommonDiff) {
		this.commonDiff = commonDiff;
		this.absCommonDiff = absCommonDiff;	
		setHashCode();
	}
	
	@Override
	protected Sequence clone() throws CloneNotSupportedException {
		Sequence cloneSequence;
		CommonDiff cloneCommonDiff = commonDiff.clone();
		AbsCommonDiff cloneAbsCommonDiff = absCommonDiff.clone();
		cloneSequence = new Sequence(cloneCommonDiff, cloneAbsCommonDiff);
		return cloneSequence;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(commonDiff, absCommonDiff));
		return componentDescriptors;
	}
	
	public boolean getThisIsAConstantSequence() {
		boolean thisIsAConstantSequence = false;
		if (commonDiff.getValue().equals("0"))
			thisIsAConstantSequence = true;
		return thisIsAConstantSequence;
	}
	
	protected List<IGrammaticalST> buildListOfRelevantComponentsForRelationBuilding() {
		List<IGrammaticalST> componentDescriptors = new ArrayList<IGrammaticalST>(
				Arrays.asList(commonDiff));
		return componentDescriptors;
	}	

	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet sequenceOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer sequenceIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String sequenceID = getDescriptorName().concat(sequenceIndex.toString());
		CommonDiffOS commonDiffOS = (CommonDiffOS) commonDiff.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		AbsCommonDiffOS absCommonDiffOS = 
				(AbsCommonDiffOS) absCommonDiff.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		sequenceOS = new SequenceOS(sequenceID, commonDiffOS, absCommonDiffOS);
		return sequenceOS;		
	}	

}
