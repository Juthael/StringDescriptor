package syntacticTreesGeneration.impl;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.AbsCommonDiff;
import model.copycatModel.synTreeGrammar.CharString;
import model.copycatModel.synTreeGrammar.CommonDiff;
import model.copycatModel.synTreeGrammar.Dimension;
import model.copycatModel.synTreeGrammar.Direction;
import model.copycatModel.synTreeGrammar.Enumeration;
import model.copycatModel.synTreeGrammar.Group;
import model.copycatModel.synTreeGrammar.GroupX;
import model.copycatModel.synTreeGrammar.Groups;
import model.copycatModel.synTreeGrammar.Position;
import model.copycatModel.synTreeGrammar.Relation;
import model.copycatModel.synTreeGrammar.Relations;
import model.copycatModel.synTreeGrammar.Sequence;
import model.copycatModel.synTreeGrammar.SequenceRel;
import model.copycatModel.synTreeGrammar.Size;
import model.copycatModel.synTreeGrammar.Structure;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.IEnumerationChecker;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.IRelationDataContainerBuilder;
import syntacticTreesGeneration.IRelationalData;
import syntacticTreesGeneration.ISequenceChecker;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.ISymmetryChecker;
import syntacticTreesGeneration.ISymmetryRelationalData;

public class RelationDataContainerBuilderImplTest {
	
	ISignal signalABC;
	ISignal signalABCD;
	List<Group> descriptorsSignalABC;
	List<Group> descriptorsSignalABCD;
	Group abcGroup;
	CharString charStringABC;
	
	@Before
	public void initialize() throws SynTreeGenerationException, CloneNotSupportedException {
		ISignalBuilder signalBuilderABC = new SignalBuilderImpl("abc", "fromLeftToRight");
		signalABC = signalBuilderABC.getSignal();
		descriptorsSignalABC = signalABC.getGroups();
		ISignalBuilder signalBuilderABCD = new SignalBuilderImpl("abcd", "fromLeftToRight");
		signalABCD = signalBuilderABCD.getSignal();
		descriptorsSignalABCD = signalABCD.getGroups();
		CommonDiff abcCommonDiff = new CommonDiff("1");
		AbsCommonDiff abcAbsCommonDiff = new AbsCommonDiff("1");
		Sequence abcSequence = new Sequence(abcCommonDiff, abcAbsCommonDiff);
		Enumeration abcEnumeration = new Enumeration("1,2,3");
		Dimension abcDimension = new Dimension("platonicLetter");
		SequenceRel abcRelation = new SequenceRel(abcDimension, abcEnumeration, abcSequence);
		Size abcSize = new Size("3");
		GroupX abcGroupX3 = new GroupX(descriptorsSignalABC);
		Groups abcGroups = new Groups(abcSize, abcGroupX3);
		Relations abcRelations = new Relations(abcGroups, abcDimension, abcRelation);
		Position abcPosition = new Position(Settings.AWAITING_POSITION_VALUE);
		Size abcGroupSize = new Size("3");
		abcGroup = new Group(false, abcGroupSize, abcPosition, abcRelations);
		Size abcGroupsSize = new Size("1");
		Groups abcGroupsGroups = new Groups(abcGroupsSize, abcGroup);
		Size charStringSize = new Size("3");
		Direction charStringDirection = new Direction("fromLeftToRight");
		Relation structureRelation = getMonoStructureRelation(descriptorsSignalABC);
		Structure structure = new Structure(charStringSize, structureRelation);
		charStringABC = new CharString(charStringDirection, structure, abcGroupsGroups); 
	}

	@Test
	public void whenListOfDescriptorsInParameterIsEmptyThenThrowsException() {
		List<Group> emptyList = new ArrayList<Group>();
		try {
			IRelationDataContainerBuilder relationDataContainerBuilder = 
					new RelationDataContainerBuilderImpl(signalABC, emptyList);
			IRelationDataContainer relationDataContainer  = relationDataContainerBuilder.getRelationDataContainer();
			fail();
		} 
		catch (SynTreeGenerationException expected) {
		}
	}
	
	@Test
	public void whenDescriptorsDontShareSameDimensionsThenRDContainerIsEmpty() throws SynTreeGenerationException {
		List<Group> listOfGroupWithDifferentSetsOfDimensions = new ArrayList<Group>();
		listOfGroupWithDifferentSetsOfDimensions.add(abcGroup);
		listOfGroupWithDifferentSetsOfDimensions.add(descriptorsSignalABCD.get(3));
		IRelationDataContainerBuilder relationDataContainerBuilder = 
				new RelationDataContainerBuilderImpl(signalABCD, listOfGroupWithDifferentSetsOfDimensions);
		IRelationDataContainer relationDataContainer = relationDataContainerBuilder.getRelationDataContainer();
		List<IEnumerationRelationalData> listOfEnumerationRelationalData = 
				relationDataContainer.getListOfEnumerations();
		assertTrue(listOfEnumerationRelationalData.isEmpty());
	}
	
	@Test
	public void whenDescriptorsCoverTheWholeStringThenRDContainerBooleanSetToTrue() throws SynTreeGenerationException {
		IRelationDataContainerBuilder relationDataContainerBuilder = 
				new RelationDataContainerBuilderImpl(signalABC, descriptorsSignalABC);
		IRelationDataContainer relationDataContainer = relationDataContainerBuilder.getRelationDataContainer();
		assertTrue(relationDataContainer.getNewDescriptorWillCoverTheFullString());
	}
	
	@Test
	public void whenDescriptorsAreRelatedThenRDContainerIsntEmpty() throws SynTreeGenerationException {
		List<Group> listOfRelatedGroups = new ArrayList<Group>();
		listOfRelatedGroups.add(descriptorsSignalABC.get(0));
		listOfRelatedGroups.add(descriptorsSignalABC.get(1));
		IRelationDataContainerBuilder relationDataContainerBuilder = 
				new RelationDataContainerBuilderImpl(signalABC, listOfRelatedGroups);
		IRelationDataContainer relationDataContainer = relationDataContainerBuilder.getRelationDataContainer();
		assertFalse(relationDataContainer.getListOfEnumerations().isEmpty());
	}
	
	@Test
	public void whenDescriptorsFormSequenceThenRDContainerContainsEnumAndSequence() throws SynTreeGenerationException {
		boolean enumerationFound;
		boolean sequenceFound;
		boolean containerContainsEnumAndSequence;
		IRelationDataContainerBuilder relationDataContainerBuilder = 
				new RelationDataContainerBuilderImpl(signalABC, descriptorsSignalABC);
		IRelationDataContainer relationDataContainer = relationDataContainerBuilder.getRelationDataContainer();
		enumerationFound = (!relationDataContainer.getListOfEnumerations().isEmpty());
		sequenceFound = (!relationDataContainer.getListOfSequences().isEmpty());
		containerContainsEnumAndSequence = (enumerationFound == true && sequenceFound == true);
		assertTrue(containerContainsEnumAndSequence);
	}
	
	private Relation getMonoStructureRelation(List<Group> listOfGroups) throws SynTreeGenerationException {
		Relation structureRelation;
		String dimension = "charString/groups/group/size";
		List<String> listOfSizeValues = new ArrayList<String>();
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for(Group group : listOfGroups) {
			listOfLetterPositions.addAll(DescriptorSpanGetterImpl.getDescriptorSpan(group));
		}
		listOfSizeValues.add(Integer.toString(listOfLetterPositions.size()));
		List<IRelationalData> listOfRelationalData = new ArrayList<IRelationalData>();
		IEnumerationChecker enumerationChecker = new EnumerationCheckerImpl(true, dimension, listOfSizeValues);
		if (enumerationChecker.getEnumerationWasFound() == true) {
			IEnumerationRelationalData enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			listOfRelationalData.add(enumerationRelationalData);
			ISequenceChecker sequenceChecker = 
					new SequenceCheckerImpl(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (sequenceChecker.getSequenceWasFound() == true) {
				ISequenceRelationalData sequenceRelationalData = sequenceChecker.getSequenceRelationalData();
				listOfRelationalData.add(sequenceRelationalData);
			}
			ISymmetryChecker symmetryChecker = 
					new SymmetryCheckerImpl(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (symmetryChecker.getSymmetryWasFound() == true) {
				ISymmetryRelationalData symmetryRelationalData = symmetryChecker.getSymmetryRelationalData();
				listOfRelationalData.add(symmetryRelationalData);
			}
			structureRelation = RelationBuilderImpl.buildRelation(listOfRelationalData);
		}
		else throw new SynTreeGenerationException("CharStringBuilder.getStructureRelation() : "
				+ "no enumeration was found.");
		return structureRelation;	
	}	
	
	private String getCharStringSizeValue(List<Group> listOfGroups) throws SynTreeGenerationException {
		String charStringSizeValue;
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for (Group group : listOfGroups) {
			listOfLetterPositions.addAll(DescriptorSpanGetterImpl.getDescriptorSpan(group));
		}
		charStringSizeValue = Integer.toString(listOfLetterPositions.size());
		return charStringSizeValue;
	}	
	
	

}
