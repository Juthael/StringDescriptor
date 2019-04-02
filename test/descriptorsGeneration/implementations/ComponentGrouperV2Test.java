package descriptorsGeneration.implementations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import copycatModel.grammar.AbsCommonDiff;
import copycatModel.grammar.CommonDiff;
import copycatModel.grammar.Dimension;
import copycatModel.grammar.DimensionX2;
import copycatModel.grammar.Direction;
import copycatModel.grammar.Enumeration;
import copycatModel.grammar.Group;
import copycatModel.grammar.GroupX2;
import copycatModel.grammar.Groups;
import copycatModel.grammar.HowManyGroups;
import copycatModel.grammar.PlatonicLetter;
import copycatModel.grammar.Position;
import copycatModel.grammar.Relation;
import copycatModel.grammar.RelationX2;
import copycatModel.grammar.Relations;
import copycatModel.grammar.RelationsOrLetter;
import copycatModel.grammar.Sequence;
import copycatModel.grammar.SequenceRel;
import copycatModel.grammar.Size;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.ComponentGrouperV2;
import syntacticTreesGeneration.implementations.DescriptorSpanGetterV1;
import syntacticTreesGeneration.implementations.Gen2Size1RelationDataContainerBuilderV1;
import syntacticTreesGeneration.implementations.NewDescriptorBuilderV1;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.interfaces.ComponentGrouperInterface;
import syntacticTreesGeneration.interfaces.Gen2Size1RelationDataContainerBuilderInterface;
import syntacticTreesGeneration.interfaces.NewDescriptorBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;

class ComponentGrouperV2Test {

	@Test
	void AllSetsCoverAdjacentLetters() throws DescriptorsBuilderCriticalException {
		boolean setsCoverAdjacentLetters = true;
		int generationNumber = 1;
		boolean nextGenerationWillBeTheLast = false;
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abcd", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		ArrayList<Group> previousGenOfDescriptors = signal.getGroups();
		ComponentGrouperInterface componentGrouper = 
				new ComponentGrouperV2(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		HashSet<ArrayList<Group>> setOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (ArrayList<Group> setOfFactorizableDescriptors : setOfFactorizableDescriptorSets) {
			HashSet<Integer> setOfLetterPositionsCoveredByThisSet = new HashSet<Integer>();
			for (Group currentGroup : setOfFactorizableDescriptors) {
				ArrayList<Integer> letterPositionsCoveredByCurrentGroup = 
						DescriptorSpanGetterV1.getDescriptorSpan(currentGroup);
				setOfLetterPositionsCoveredByThisSet.addAll(letterPositionsCoveredByCurrentGroup);
			}
			int minimalPosition = -1;
			int positionIndex = 0;
			while(minimalPosition == -1) {
				if (setOfLetterPositionsCoveredByThisSet.contains(positionIndex))
					minimalPosition = positionIndex;
				else positionIndex++;
			}
			for (int j=minimalPosition ; j<(minimalPosition + setOfLetterPositionsCoveredByThisSet.size()) ; j++) {
				if (!setOfLetterPositionsCoveredByThisSet.contains(j))
					setsCoverAdjacentLetters = false;
			}
		}
		assertTrue(setsCoverAdjacentLetters);	
	}	
	
	@Test
	void whenComponentsAre1stGenerationThenPositionsCoveredAreAsExpectedBelow() throws DescriptorsBuilderCriticalException {
		boolean positionsCoveredAreAsExpectedBelow;
		HashSet<ArrayList<Integer>> setOfPositionListsExpectedForABCD = new HashSet<ArrayList<Integer>>();
		HashSet<ArrayList<Integer>> setOfPositionListsFounForABCD = new HashSet<ArrayList<Integer>>();
		ArrayList<Integer> pos1 = new ArrayList<Integer>(Arrays.asList(new Integer[] {1}));
		ArrayList<Integer> pos2 = new ArrayList<Integer>(Arrays.asList(new Integer[] {2}));
		ArrayList<Integer> pos3 = new ArrayList<Integer>(Arrays.asList(new Integer[] {3}));
		ArrayList<Integer> pos4 = new ArrayList<Integer>(Arrays.asList(new Integer[] {4}));
		ArrayList<Integer> pos12 = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2}));
		ArrayList<Integer> pos23 = new ArrayList<Integer>(Arrays.asList(new Integer[] {2,3}));
		ArrayList<Integer> pos34 = new ArrayList<Integer>(Arrays.asList(new Integer[] {3,4}));
		ArrayList<Integer> pos123 = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,3}));
		ArrayList<Integer> pos234 = new ArrayList<Integer>(Arrays.asList(new Integer[] {2,3,4}));
		ArrayList<Integer> pos1234 = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,3,4}));
		setOfPositionListsExpectedForABCD.add(pos1);
		setOfPositionListsExpectedForABCD.add(pos2);
		setOfPositionListsExpectedForABCD.add(pos3);
		setOfPositionListsExpectedForABCD.add(pos4);
		setOfPositionListsExpectedForABCD.add(pos12);
		setOfPositionListsExpectedForABCD.add(pos23);
		setOfPositionListsExpectedForABCD.add(pos34);
		setOfPositionListsExpectedForABCD.add(pos123);
		setOfPositionListsExpectedForABCD.add(pos234);
		setOfPositionListsExpectedForABCD.add(pos1234);
		int generationNumber = 1;
		boolean nextGenerationWillBeTheLast = false;
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abcd", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		ArrayList<Group> previousGenOfDescriptors = signal.getGroups();
		ComponentGrouperInterface componentGrouper = 
				new ComponentGrouperV2(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		HashSet<ArrayList<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (ArrayList<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			ArrayList<Integer> letterPositionsCoveredByThisSet = new ArrayList<Integer>();
			for (Group currentGroup : setOfFactorizableDescriptors) {
				ArrayList<Integer> positions = DescriptorSpanGetterV1.getDescriptorSpan(currentGroup);
				letterPositionsCoveredByThisSet.addAll(positions);
			}
			setOfPositionListsFounForABCD.add(letterPositionsCoveredByThisSet);
		}
		positionsCoveredAreAsExpectedBelow = (setOfPositionListsExpectedForABCD.equals(setOfPositionListsFounForABCD));
		assertTrue(positionsCoveredAreAsExpectedBelow);
	}	
	
	@Test
	void whenComponentsAre1stGenerationThenSetsCanBeOfSize1() throws DescriptorsBuilderCriticalException {
		boolean oneSetAtLeastIsSize1 = false;
		int generationNumber = 1;
		boolean nextGenerationWillBeTheLast = false;
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abcd", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		ArrayList<Group> previousGenOfDescriptors = signal.getGroups();
		ComponentGrouperInterface componentGrouper = 
				new ComponentGrouperV2(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		HashSet<ArrayList<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (ArrayList<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			if (setOfFactorizableDescriptors.size() == 1 && oneSetAtLeastIsSize1 == false)
				oneSetAtLeastIsSize1 = true;
		}
		assertTrue(oneSetAtLeastIsSize1);			
	}
	

	
	@Test
	void whenComponentsArent1stGenerationThenSetsCanNotBeOfSize1() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		boolean oneSetOfComponentsAtLeastIsSize1 = false;
		ArrayList<Group> previousDescriptors = new ArrayList<Group>();
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		ArrayList<Group> previousGenOfDescriptors = signal.getGroups();
		CommonDiff commonDiff1 = new CommonDiff(false, "1");
		AbsCommonDiff absCommonDiff1 = new AbsCommonDiff(false, "1");
		CommonDiff commonDiff0 = new CommonDiff(false, "0");
		AbsCommonDiff absCommonDiff0 = new AbsCommonDiff(false, "0");
		Sequence sequence1 = new Sequence(false, commonDiff1, absCommonDiff1);
		Sequence sequence0 = new Sequence(false, commonDiff0, absCommonDiff0);
		Enumeration enumeration12 = new Enumeration(false, "1,2");
		Enumeration enumeration23 = new Enumeration(false, "2,3");
		Enumeration enumeration11 = new Enumeration(false, "1,1");
		Dimension dimensionPlatonicLetter = new Dimension(false, "platonicLetter");
		Dimension dimensionSize = new Dimension(false, "size");
		Size size2 = new Size(false, "2");
		//Build Gen2Size1_A
		ArrayList<Group> listOf1ComponentA = new ArrayList<Group>();
		Group groupA = (Group) previousGenOfDescriptors.get(0);
		listOf1ComponentA.add(groupA);
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilderA = 
				new Gen2Size1RelationDataContainerBuilderV1(signal, previousGenOfDescriptors.get(0));
		ArrayList<RelationDataContainerInterface> gen2Size1RDContainersA = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		for (RelationDataContainerInterface gen2Size1RDContainerA : gen2Size1RDContainersA) {
			NewDescriptorBuilderInterface newDescBuilder = new NewDescriptorBuilderV1(signal, gen2Size1RDContainerA, listOf1ComponentA);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}
		//Build Gen2Size1_B
		ArrayList<Group> listOf1ComponentB = new ArrayList<Group>();
		Group groupB = (Group) previousGenOfDescriptors.get(1);
		listOf1ComponentB.add(groupB);
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilderB = 
				new Gen2Size1RelationDataContainerBuilderV1(signal, previousGenOfDescriptors.get(1));
		ArrayList<RelationDataContainerInterface> gen2Size1RDContainersB = 
				gen2Size1RDContainerBuilderB.getListOfRelationDataContainers();
		for (RelationDataContainerInterface gen2Size1RDContainerB : gen2Size1RDContainersB) {
			NewDescriptorBuilderInterface newDescBuilder = new NewDescriptorBuilderV1(signal, gen2Size1RDContainerB, listOf1ComponentB);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}		
		//Build Gen2Size1_C
		ArrayList<Group> listOf1ComponentC = new ArrayList<Group>();
		Group groupC = (Group) previousGenOfDescriptors.get(2);
		listOf1ComponentC.add(groupC);
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilderC = 
				new Gen2Size1RelationDataContainerBuilderV1(signal, previousGenOfDescriptors.get(2));
		ArrayList<RelationDataContainerInterface> gen2Size1RDContainersC = 
				gen2Size1RDContainerBuilderC.getListOfRelationDataContainers();
		for (RelationDataContainerInterface gen2Size1RDContainerC : gen2Size1RDContainersC) {
			NewDescriptorBuilderInterface newDescBuilder = new NewDescriptorBuilderV1(signal, gen2Size1RDContainerC, listOf1ComponentC);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}			
		
		//Build Gen2usual
		DimensionX2 dimensionX2 = new DimensionX2(false, dimensionPlatonicLetter, dimensionSize);
		//AB
		SequenceRel relationABplatonicLetter = new SequenceRel(false, dimensionPlatonicLetter, enumeration12, sequence1);
		SequenceRel relationABSize = new SequenceRel(false, dimensionSize, enumeration11, sequence0);
		RelationX2 relationX2AB = new RelationX2(false, relationABplatonicLetter, relationABSize);
		HowManyGroups groupX2AB = new GroupX2(false, previousGenOfDescriptors.get(0), previousGenOfDescriptors.get(1));
		Groups groupsAB = new Groups(false, size2, groupX2AB);
		RelationsOrLetter relationsAB = new Relations(false, groupsAB, dimensionX2, relationX2AB);
		Position positionAB = new Position(false, DescGenSettings.AWAITING_POSITION_VALUE);
		Group groupAB = new Group(false, size2, positionAB, relationsAB);
		previousDescriptors.add(groupAB);
		//BC
		SequenceRel relationBCplatonicLetter = new SequenceRel(false, dimensionPlatonicLetter, enumeration23, sequence1);
		SequenceRel relationBCSize = new SequenceRel(false, dimensionSize, enumeration11, sequence0);
		RelationX2 relationX2BC = new RelationX2(false, relationBCplatonicLetter, relationBCSize);
		HowManyGroups groupX2BC = new GroupX2(false, previousGenOfDescriptors.get(1), previousGenOfDescriptors.get(2));
		Groups groupsBC = new Groups(false, size2, groupX2BC);
		RelationsOrLetter relationsBC = new Relations(false, groupsBC, dimensionX2, relationX2BC);
		Position positionBC = new Position(false, DescGenSettings.AWAITING_POSITION_VALUE);
		Group groupBC = new Group(false, size2, positionBC, relationsBC);
		previousDescriptors.add(groupBC);		
		//Assert
		ComponentGrouperInterface componentGrouper = new ComponentGrouperV2(2, false, signal, previousDescriptors);
		HashSet<ArrayList<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (ArrayList<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			if (setOfFactorizableDescriptors.size() < 1)
				oneSetOfComponentsAtLeastIsSize1 = true;
			/* StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("positions : ");
			for (Group group : setOfFactorizableDescriptors) {
				ArrayList<Integer> positionList = DescriptorSpanGetterV1.getDescriptorSpan(group);
				for (Integer position : positionList) {
					stringBuilder.append(Integer.toString(position));
					stringBuilder.append(" ");
				}
				stringBuilder.append("+");
			}
			System.out.println(stringBuilder.toString()); */
		}
		assertFalse(oneSetOfComponentsAtLeastIsSize1);		
	}
	
	@Test
	void whenNextGenerationWontBeTheLastThenSetsDontHaveToCoverTheWholeString() throws DescriptorsBuilderCriticalException {
		boolean oneSetAtLeastDoNotCoverTheWholeString = false;
		int generationNumber = 1;
		boolean nextGenerationWillBeTheLast = false;
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abcd", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		ArrayList<Group> previousGenOfDescriptors = signal.getGroups();
		ComponentGrouperInterface componentGrouper = 
				new ComponentGrouperV2(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		HashSet<ArrayList<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (ArrayList<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			HashSet<Integer> setOfLetterPositionsCovered = new HashSet<Integer>();
			for (Group currentGroup : setOfFactorizableDescriptors) {
				ArrayList<Integer> letterPositionsCoveredByThisGroup = DescriptorSpanGetterV1.getDescriptorSpan(currentGroup);
				setOfLetterPositionsCovered.addAll(letterPositionsCoveredByThisGroup);
			}
			if (setOfLetterPositionsCovered.size() != signal.getSignalSize())
				oneSetAtLeastDoNotCoverTheWholeString = true;
			
		}
		assertTrue(oneSetAtLeastDoNotCoverTheWholeString);		
	}
	

	
	@Test
	void whenNextGenerationWillBeTheLastThenAllSetsCoverTheWholeString() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		boolean oneSetAtLeastDoNotCoverTheWholeString = false;
		ArrayList<Group> previousDescriptors = new ArrayList<Group>();
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		ArrayList<Group> previousGenOfDescriptors = signal.getGroups();
		CommonDiff commonDiff1 = new CommonDiff(false, "1");
		AbsCommonDiff absCommonDiff1 = new AbsCommonDiff(false, "1");
		CommonDiff commonDiff0 = new CommonDiff(false, "0");
		AbsCommonDiff absCommonDiff0 = new AbsCommonDiff(false, "0");
		Sequence sequence1 = new Sequence(false, commonDiff1, absCommonDiff1);
		Sequence sequence0 = new Sequence(false, commonDiff0, absCommonDiff0);
		Enumeration enumeration12 = new Enumeration(false, "1,2");
		Enumeration enumeration23 = new Enumeration(false, "2,3");
		Enumeration enumeration11 = new Enumeration(false, "1,1");
		Dimension dimensionPlatonicLetter = new Dimension(false, "platonicLetter");
		Dimension dimensionSize = new Dimension(false, "size");
		Size size2 = new Size(false, "2");
		//Build Gen2Size1_A
		ArrayList<Group> listOf1ComponentA = new ArrayList<Group>();
		Group groupA = (Group) previousGenOfDescriptors.get(0);
		listOf1ComponentA.add(groupA);
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilderA = 
				new Gen2Size1RelationDataContainerBuilderV1(signal, previousGenOfDescriptors.get(0));
		ArrayList<RelationDataContainerInterface> gen2Size1RDContainersA = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		for (RelationDataContainerInterface gen2Size1RDContainerA : gen2Size1RDContainersA) {
			NewDescriptorBuilderInterface newDescBuilder = new NewDescriptorBuilderV1(signal, gen2Size1RDContainerA, listOf1ComponentA);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}
		//Build Gen2Size1_B
		ArrayList<Group> listOf1ComponentB = new ArrayList<Group>();
		Group groupB = (Group) previousGenOfDescriptors.get(1);
		listOf1ComponentB.add(groupB);
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilderB = 
				new Gen2Size1RelationDataContainerBuilderV1(signal, previousGenOfDescriptors.get(1));
		ArrayList<RelationDataContainerInterface> gen2Size1RDContainersB = 
				gen2Size1RDContainerBuilderB.getListOfRelationDataContainers();
		for (RelationDataContainerInterface gen2Size1RDContainerB : gen2Size1RDContainersB) {
			NewDescriptorBuilderInterface newDescBuilder = new NewDescriptorBuilderV1(signal, gen2Size1RDContainerB, listOf1ComponentB);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}		
		//Build Gen2Size1_C
		ArrayList<Group> listOf1ComponentC = new ArrayList<Group>();
		Group groupC = (Group) previousGenOfDescriptors.get(2);
		listOf1ComponentC.add(groupC);
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilderC = 
				new Gen2Size1RelationDataContainerBuilderV1(signal, previousGenOfDescriptors.get(2));
		ArrayList<RelationDataContainerInterface> gen2Size1RDContainersC = 
				gen2Size1RDContainerBuilderC.getListOfRelationDataContainers();
		for (RelationDataContainerInterface gen2Size1RDContainerC : gen2Size1RDContainersC) {
			NewDescriptorBuilderInterface newDescBuilder = new NewDescriptorBuilderV1(signal, gen2Size1RDContainerC, listOf1ComponentC);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}			
		
		//Build Gen2usual
		DimensionX2 dimensionX2 = new DimensionX2(false, dimensionPlatonicLetter, dimensionSize);
		//AB
		SequenceRel relationABplatonicLetter = new SequenceRel(false, dimensionPlatonicLetter, enumeration12, sequence1);
		SequenceRel relationABSize = new SequenceRel(false, dimensionSize, enumeration11, sequence0);
		RelationX2 relationX2AB = new RelationX2(false, relationABplatonicLetter, relationABSize);
		HowManyGroups groupX2AB = new GroupX2(false, previousGenOfDescriptors.get(0), previousGenOfDescriptors.get(1));
		Groups groupsAB = new Groups(false, size2, groupX2AB);
		RelationsOrLetter relationsAB = new Relations(false, groupsAB, dimensionX2, relationX2AB);
		Position positionAB = new Position(false, DescGenSettings.AWAITING_POSITION_VALUE);
		Group groupAB = new Group(false, size2, positionAB, relationsAB);
		previousDescriptors.add(groupAB);
		//BC
		SequenceRel relationBCplatonicLetter = new SequenceRel(false, dimensionPlatonicLetter, enumeration23, sequence1);
		SequenceRel relationBCSize = new SequenceRel(false, dimensionSize, enumeration11, sequence0);
		RelationX2 relationX2BC = new RelationX2(false, relationBCplatonicLetter, relationBCSize);
		HowManyGroups groupX2BC = new GroupX2(false, previousGenOfDescriptors.get(1), previousGenOfDescriptors.get(2));
		Groups groupsBC = new Groups(false, size2, groupX2BC);
		RelationsOrLetter relationsBC = new Relations(false, groupsBC, dimensionX2, relationX2BC);
		Position positionBC = new Position(false, DescGenSettings.AWAITING_POSITION_VALUE);
		Group groupBC = new Group(false, size2, positionBC, relationsBC);
		previousDescriptors.add(groupBC);		
		//Assert
		ComponentGrouperInterface componentGrouper = new ComponentGrouperV2(2, true, signal, previousDescriptors);
		HashSet<ArrayList<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (ArrayList<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			HashSet<Integer> setOfLetterPositionsCovered = new HashSet<Integer>();
			for (Group currentGroup : setOfFactorizableDescriptors) {
				ArrayList<Integer> letterPositionsCoveredByThisGroup = DescriptorSpanGetterV1.getDescriptorSpan(currentGroup);
				setOfLetterPositionsCovered.addAll(letterPositionsCoveredByThisGroup);
			}
			if (setOfLetterPositionsCovered.size() != signal.getSignalSize())
				oneSetAtLeastDoNotCoverTheWholeString = true;
			
		}
		assertFalse(oneSetAtLeastDoNotCoverTheWholeString);		
	}	

}
