package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.AbsCommonDiffOS;
import model.copycatModel.ordSetGrammar.CommonDiffOS;
import model.copycatModel.ordSetGrammar.SequenceOS;
import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class Sequence extends SynTreeElementImpl implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_NAME = "sequence";
	private CommonDiff commonDiff;
	private AbsCommonDiff absCommonDiff;
	
	public Sequence(CommonDiff commonDiff, AbsCommonDiff absCommonDiff) {
		this.commonDiff = commonDiff;
		this.absCommonDiff = absCommonDiff;	
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
	
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> componentDescriptors = new ArrayList<SynTreeElementImpl>(
				Arrays.asList(commonDiff));
		return componentDescriptors;
	}	

	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement sequenceOS;
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
