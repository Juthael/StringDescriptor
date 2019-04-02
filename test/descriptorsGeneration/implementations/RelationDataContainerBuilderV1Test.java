package descriptorsGeneration.implementations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import copycatModel.grammar.AbsCommonDiff;
import copycatModel.grammar.CharString;
import copycatModel.grammar.CommonDiff;
import copycatModel.grammar.Dimension;
import copycatModel.grammar.Direction;
import copycatModel.grammar.Enumeration;
import copycatModel.grammar.Group;
import copycatModel.grammar.GroupX3;
import copycatModel.grammar.Groups;
import copycatModel.grammar.Position;
import copycatModel.grammar.Relation;
import copycatModel.grammar.Relations;
import copycatModel.grammar.Sequence;
import copycatModel.grammar.SequenceRel;
import copycatModel.grammar.Size;
import copycatModel.grammar.Structure;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.CharStringBuilderV1;
import syntacticTreesGeneration.implementations.DescriptorSpanGetterV1;
import syntacticTreesGeneration.implementations.EnumerationCheckerV1;
import syntacticTreesGeneration.implementations.RelationBuilderV1;
import syntacticTreesGeneration.implementations.RelationDataContainerBuilderV1;
import syntacticTreesGeneration.implementations.SequenceCheckerV1;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.implementations.SymmetryCheckerV1;
import syntacticTreesGeneration.interfaces.EnumerationCheckerInterface;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.RelationalDataInterface;
import syntacticTreesGeneration.interfaces.SequenceCheckerInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;
import syntacticTreesGeneration.interfaces.SymmetryCheckerInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;

class RelationDataContainerBuilderV1Test {
	
	SignalInterface signalABC;
	SignalInterface signalABCD;
	ArrayList<Group> descriptorsSignalABC;
	ArrayList<Group> descriptorsSignalABCD;
	Group abcGroup;
	CharString charStringABC;
	
	@BeforeEach
	void initialize() throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		SignalBuilderInterface signalBuilderABC = new SignalBuilderV1("abc", "fromLeftToRight");
		signalABC = signalBuilderABC.getSignal();
		descriptorsSignalABC = signalABC.getGroups();
		SignalBuilderInterface signalBuilderABCD = new SignalBuilderV1("abcd", "fromLeftToRight");
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
		Position abcPosition = new Position(false, DescGenSettings.AWAITING_POSITION_VALUE);
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
	void whenListOfDescriptorsInParameterIsEmptyThenThrowsException() {
		ArrayList<Group> emptyList = new ArrayList<Group>();
		try {
			RelationDataContainerBuilderInterface relationDataContainerBuilder = 
					new RelationDataContainerBuilderV1(signalABC, emptyList);
			RelationDataContainerInterface relationDataContainer  = relationDataContainerBuilder.getRelationDataContainer();
			fail();
		} 
		catch (DescriptorsBuilderCriticalException expected) {
		}
	}
	
	@Test
	void whenDescriptorsDontShareSameDimensionsThenRDContainerIsEmpty() throws DescriptorsBuilderCriticalException {
		ArrayList<Group> listOfGroupWithDifferentSetsOfDimensions = new ArrayList<Group>();
		listOfGroupWithDifferentSetsOfDimensions.add(abcGroup);
		listOfGroupWithDifferentSetsOfDimensions.add(descriptorsSignalABCD.get(3));
		RelationDataContainerBuilderInterface relationDataContainerBuilder = 
				new RelationDataContainerBuilderV1(signalABCD, listOfGroupWithDifferentSetsOfDimensions);
		RelationDataContainerInterface relationDataContainer = relationDataContainerBuilder.getRelationDataContainer();
		ArrayList<EnumerationRelationalDataInterface> listOfEnumerationRelationalData = 
				relationDataContainer.getListOfEnumerations();
		assertTrue(listOfEnumerationRelationalData.isEmpty());
	}
	
	@Test
	void whenDescriptorsCoverTheWholeStringThenRDContainerBooleanSetToTrue() throws DescriptorsBuilderCriticalException {
		RelationDataContainerBuilderInterface relationDataContainerBuilder = 
				new RelationDataContainerBuilderV1(signalABC, descriptorsSignalABC);
		RelationDataContainerInterface relationDataContainer = relationDataContainerBuilder.getRelationDataContainer();
		assertTrue(relationDataContainer.getNewDescriptorWillCoverTheFullString());
	}
	
	@Test
	void whenDescriptorsAreRelatedThenRDContainerIsntEmpty() throws DescriptorsBuilderCriticalException {
		ArrayList<Group> listOfRelatedGroups = new ArrayList<Group>();
		listOfRelatedGroups.add(descriptorsSignalABC.get(0));
		listOfRelatedGroups.add(descriptorsSignalABC.get(1));
		RelationDataContainerBuilderInterface relationDataContainerBuilder = 
				new RelationDataContainerBuilderV1(signalABC, listOfRelatedGroups);
		RelationDataContainerInterface relationDataContainer = relationDataContainerBuilder.getRelationDataContainer();
		assertFalse(relationDataContainer.getListOfEnumerations().isEmpty());
	}
	
	@Test
	void whenDescriptorsFormSequenceThenRDContainerContainsEnumAndSequence() throws DescriptorsBuilderCriticalException {
		boolean enumerationFound;
		boolean sequenceFound;
		boolean containerContainsEnumAndSequence;
		RelationDataContainerBuilderInterface relationDataContainerBuilder = 
				new RelationDataContainerBuilderV1(signalABC, descriptorsSignalABC);
		RelationDataContainerInterface relationDataContainer = relationDataContainerBuilder.getRelationDataContainer();
		enumerationFound = (!relationDataContainer.getListOfEnumerations().isEmpty());
		sequenceFound = (!relationDataContainer.getListOfSequences().isEmpty());
		containerContainsEnumAndSequence = (enumerationFound == true && sequenceFound == true);
		assertTrue(containerContainsEnumAndSequence);
	}
	
	private Relation getMonoStructureRelation(List<Group> listOfGroups) throws DescriptorsBuilderCriticalException {
		Relation structureRelation;
		String dimension = "charString/groups/group/size";
		ArrayList<String> listOfSizeValues = new ArrayList<String>();
		ArrayList<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for(Group group : listOfGroups) {
			listOfLetterPositions.addAll(DescriptorSpanGetterV1.getDescriptorSpan(group));
		}
		listOfSizeValues.add(Integer.toString(listOfLetterPositions.size()));
		ArrayList<RelationalDataInterface> listOfRelationalData = new ArrayList<RelationalDataInterface>();
		EnumerationCheckerInterface enumerationChecker = new EnumerationCheckerV1(true, dimension, listOfSizeValues);
		if (enumerationChecker.getEnumerationWasFound() == true) {
			EnumerationRelationalDataInterface enumerationRelationalData = enumerationChecker.getEnumerationRelationalData();
			listOfRelationalData.add(enumerationRelationalData);
			SequenceCheckerInterface sequenceChecker = 
					new SequenceCheckerV1(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (sequenceChecker.getSequenceWasFound() == true) {
				SequenceRelationalDataInterface sequenceRelationalData = sequenceChecker.getSequenceRelationalData();
				listOfRelationalData.add(sequenceRelationalData);
			}
			SymmetryCheckerInterface symmetryChecker = 
					new SymmetryCheckerV1(true, dimension, listOfSizeValues, enumerationRelationalData);
			if (symmetryChecker.getSymmetryWasFound() == true) {
				SymmetryRelationalDataInterface symmetryRelationalData = symmetryChecker.getSymmetryRelationalData();
				listOfRelationalData.add(symmetryRelationalData);
			}
			structureRelation = RelationBuilderV1.buildRelation(listOfRelationalData);
		}
		else throw new DescriptorsBuilderCriticalException("CharStringBuilder.getStructureRelation() : "
				+ "no enumeration was found.");
		return structureRelation;	
	}	
	
	private String getCharStringSizeValue(List<Group> listOfGroups) throws DescriptorsBuilderCriticalException {
		String charStringSizeValue;
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for (Group group : listOfGroups) {
			listOfLetterPositions.addAll(DescriptorSpanGetterV1.getDescriptorSpan(group));
		}
		charStringSizeValue = Integer.toString(listOfLetterPositions.size());
		return charStringSizeValue;
	}	
	
	

}
