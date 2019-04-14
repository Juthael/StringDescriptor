package syntacticTreesGeneration.impl;



import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import copycatModel.synTreeModel.ISignal;
import copycatModel.synTreeModel.grammar.AbsCommonDiff;
import copycatModel.synTreeModel.grammar.CharString;
import copycatModel.synTreeModel.grammar.CommonDiff;
import copycatModel.synTreeModel.grammar.Dimension;
import copycatModel.synTreeModel.grammar.Direction;
import copycatModel.synTreeModel.grammar.Enumeration;
import copycatModel.synTreeModel.grammar.Group;
import copycatModel.synTreeModel.grammar.GroupX3;
import copycatModel.synTreeModel.grammar.Groups;
import copycatModel.synTreeModel.grammar.Position;
import copycatModel.synTreeModel.grammar.Relation;
import copycatModel.synTreeModel.grammar.Relations;
import copycatModel.synTreeModel.grammar.Sequence;
import copycatModel.synTreeModel.grammar.SequenceRel;
import copycatModel.synTreeModel.grammar.Size;
import copycatModel.synTreeModel.grammar.Structure;
import exceptions.SynTreeGenerationException;
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
import syntacticTreesGeneration.impl.DescriptorSpanGetterImpl;
import syntacticTreesGeneration.impl.EnumerationCheckerImpl;
import syntacticTreesGeneration.impl.RelationBuilderImpl;
import syntacticTreesGeneration.impl.RelationDataContainerBuilderImpl;
import syntacticTreesGeneration.impl.SequenceCheckerImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;
import syntacticTreesGeneration.impl.SymmetryCheckerImpl;

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
		CommonDiff abcCommonDiff = new CommonDiff(false, "1");
		AbsCommonDiff abcAbsCommonDiff = new AbsCommonDiff(false, "1");
		Sequence abcSequence = new Sequence(false, abcCommonDiff, abcAbsCommonDiff);
		Enumeration abcEnumeration = new Enumeration(false, "1,2,3");
		Dimension abcDimension = new Dimension(false, "platonicLetter");
		SequenceRel abcRelation = new SequenceRel(false, abcDimension, abcEnumeration, abcSequence);
		Size abcSize = new Size(false, "3");
		GroupX3 abcGroupX3 = new GroupX3(false, descriptorsSignalABC.get(0), descriptorsSignalABC.get(1), 
				descriptorsSignalABC.get(2));
		Groups abcGroups = new Groups(false, abcSize, abcGroupX3);
		Relations abcRelations = new Relations(false, abcGroups, abcDimension, abcRelation);
		Position abcPosition = new Position(false, Settings.AWAITING_POSITION_VALUE);
		Size abcGroupSize = new Size(false, "3");
		abcGroup = new Group(false, abcGroupSize, abcPosition, abcRelations);
		Size abcGroupsSize = new Size(false, "1");
		Groups abcGroupsGroups = new Groups(false, abcGroupsSize, abcGroup);
		Size charStringSize = new Size(false, "3");
		Direction charStringDirection = new Direction(false, "fromLeftToRight");
		Relation structureRelation = getMonoStructureRelation(descriptorsSignalABC);
		Structure structure = new Structure(false, charStringSize, structureRelation);
		charStringABC = new CharString(false, charStringDirection, structure, abcGroupsGroups); 
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
