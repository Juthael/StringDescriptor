package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import copycatModel.synTreeModel.ISignal;
import copycatModel.synTreeModel.grammar.AbsCommonDiff;
import copycatModel.synTreeModel.grammar.CommonDiff;
import copycatModel.synTreeModel.grammar.Dimension;
import copycatModel.synTreeModel.grammar.DimensionX2;
import copycatModel.synTreeModel.grammar.Enumeration;
import copycatModel.synTreeModel.grammar.Group;
import copycatModel.synTreeModel.grammar.GroupX2;
import copycatModel.synTreeModel.grammar.Groups;
import copycatModel.synTreeModel.grammar.HowManyGroups;
import copycatModel.synTreeModel.grammar.Position;
import copycatModel.synTreeModel.grammar.RelationX2;
import copycatModel.synTreeModel.grammar.Relations;
import copycatModel.synTreeModel.grammar.RelationsOrLetter;
import copycatModel.synTreeModel.grammar.Sequence;
import copycatModel.synTreeModel.grammar.SequenceRel;
import copycatModel.synTreeModel.grammar.Size;
import exceptions.SynTreeGenerationException;
import settings.Settings;
import syntacticTreesGeneration.IComponentGrouper;
import syntacticTreesGeneration.IGen2Size1RelationDataContainerBuilder;
import syntacticTreesGeneration.INewDescriptorBuilder;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ComponentGrouperImpl;
import syntacticTreesGeneration.impl.DescriptorSpanGetterImpl;
import syntacticTreesGeneration.impl.Gen2Size1RelationDataContainerBuilderImpl;
import syntacticTreesGeneration.impl.NewDescriptorBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class ComponentGrouperImplTest {

	@Test
	public void AllSetsCoverAdjacentLetters() throws SynTreeGenerationException {
		boolean setsCoverAdjacentLetters = true;
		int generationNumber = 1;
		boolean nextGenerationWillBeTheLast = false;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> previousGenOfDescriptors = signal.getGroups();
		IComponentGrouper componentGrouper = 
				new ComponentGrouperImpl(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		Set<List<Group>> setOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Group> setOfFactorizableDescriptors : setOfFactorizableDescriptorSets) {
			Set<Integer> setOfLetterPositionsCoveredByThisSet = new HashSet<Integer>();
			for (Group currentGroup : setOfFactorizableDescriptors) {
				List<Integer> letterPositionsCoveredByCurrentGroup = 
						DescriptorSpanGetterImpl.getDescriptorSpan(currentGroup);
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
	public void whenComponentsAre1stGenerationThenPositionsCoveredAreAsExpectedBelow() throws SynTreeGenerationException {
		boolean positionsCoveredAreAsExpectedBelow;
		Set<List<Integer>> setOfPositionListsExpectedForABCD = new HashSet<List<Integer>>();
		Set<List<Integer>> setOfPositionListsFounForABCD = new HashSet<List<Integer>>();
		List<Integer> pos1 = new ArrayList<Integer>(Arrays.asList(new Integer[] {1}));
		List<Integer> pos2 = new ArrayList<Integer>(Arrays.asList(new Integer[] {2}));
		List<Integer> pos3 = new ArrayList<Integer>(Arrays.asList(new Integer[] {3}));
		List<Integer> pos4 = new ArrayList<Integer>(Arrays.asList(new Integer[] {4}));
		List<Integer> pos12 = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2}));
		List<Integer> pos23 = new ArrayList<Integer>(Arrays.asList(new Integer[] {2,3}));
		List<Integer> pos34 = new ArrayList<Integer>(Arrays.asList(new Integer[] {3,4}));
		List<Integer> pos123 = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,3}));
		List<Integer> pos234 = new ArrayList<Integer>(Arrays.asList(new Integer[] {2,3,4}));
		List<Integer> pos1234 = new ArrayList<Integer>(Arrays.asList(new Integer[] {1,2,3,4}));
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
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> previousGenOfDescriptors = signal.getGroups();
		IComponentGrouper componentGrouper = 
				new ComponentGrouperImpl(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		Set<List<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			List<Integer> letterPositionsCoveredByThisSet = new ArrayList<Integer>();
			for (Group currentGroup : setOfFactorizableDescriptors) {
				List<Integer> positions = DescriptorSpanGetterImpl.getDescriptorSpan(currentGroup);
				letterPositionsCoveredByThisSet.addAll(positions);
			}
			setOfPositionListsFounForABCD.add(letterPositionsCoveredByThisSet);
		}
		positionsCoveredAreAsExpectedBelow = (setOfPositionListsExpectedForABCD.equals(setOfPositionListsFounForABCD));
		assertTrue(positionsCoveredAreAsExpectedBelow);
	}	
	
	@Test
	public void whenComponentsAre1stGenerationThenSetsCanBeOfSize1() throws SynTreeGenerationException {
		boolean oneSetAtLeastIsSize1 = false;
		int generationNumber = 1;
		boolean nextGenerationWillBeTheLast = false;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> previousGenOfDescriptors = signal.getGroups();
		IComponentGrouper componentGrouper = 
				new ComponentGrouperImpl(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		Set<List<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			if (setOfFactorizableDescriptors.size() == 1 && oneSetAtLeastIsSize1 == false)
				oneSetAtLeastIsSize1 = true;
		}
		assertTrue(oneSetAtLeastIsSize1);			
	}
	

	
	@Test
	public void whenComponentsArent1stGenerationThenSetsCanNotBeOfSize1() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean oneSetOfComponentsAtLeastIsSize1 = false;
		ArrayList<Group> previousDescriptors = new ArrayList<Group>();
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> previousGenOfDescriptors = signal.getGroups();
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
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderA = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(0));
		List<IRelationDataContainer> gen2Size1RDContainersA = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerA : gen2Size1RDContainersA) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerA, listOf1ComponentA);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}
		//Build Gen2Size1_B
		ArrayList<Group> listOf1ComponentB = new ArrayList<Group>();
		Group groupB = (Group) previousGenOfDescriptors.get(1);
		listOf1ComponentB.add(groupB);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderB = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(1));
		List<IRelationDataContainer> gen2Size1RDContainersB = 
				gen2Size1RDContainerBuilderB.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerB : gen2Size1RDContainersB) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerB, listOf1ComponentB);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}		
		//Build Gen2Size1_C
		ArrayList<Group> listOf1ComponentC = new ArrayList<Group>();
		Group groupC = (Group) previousGenOfDescriptors.get(2);
		listOf1ComponentC.add(groupC);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderC = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(2));
		List<IRelationDataContainer> gen2Size1RDContainersC = 
				gen2Size1RDContainerBuilderC.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerC : gen2Size1RDContainersC) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerC, listOf1ComponentC);
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
		Position positionAB = new Position(false, Settings.AWAITING_POSITION_VALUE);
		Group groupAB = new Group(false, size2, positionAB, relationsAB);
		previousDescriptors.add(groupAB);
		//BC
		SequenceRel relationBCplatonicLetter = new SequenceRel(false, dimensionPlatonicLetter, enumeration23, sequence1);
		SequenceRel relationBCSize = new SequenceRel(false, dimensionSize, enumeration11, sequence0);
		RelationX2 relationX2BC = new RelationX2(false, relationBCplatonicLetter, relationBCSize);
		HowManyGroups groupX2BC = new GroupX2(false, previousGenOfDescriptors.get(1), previousGenOfDescriptors.get(2));
		Groups groupsBC = new Groups(false, size2, groupX2BC);
		RelationsOrLetter relationsBC = new Relations(false, groupsBC, dimensionX2, relationX2BC);
		Position positionBC = new Position(false, Settings.AWAITING_POSITION_VALUE);
		Group groupBC = new Group(false, size2, positionBC, relationsBC);
		previousDescriptors.add(groupBC);		
		//Assert
		IComponentGrouper componentGrouper = new ComponentGrouperImpl(2, false, signal, previousDescriptors);
		Set<List<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
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
	public void whenNextGenerationWontBeTheLastThenSetsDontHaveToCoverTheWholeString() throws SynTreeGenerationException {
		boolean oneSetAtLeastDoNotCoverTheWholeString = false;
		int generationNumber = 1;
		boolean nextGenerationWillBeTheLast = false;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> previousGenOfDescriptors = signal.getGroups();
		IComponentGrouper componentGrouper = 
				new ComponentGrouperImpl(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		Set<List<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			Set<Integer> setOfLetterPositionsCovered = new HashSet<Integer>();
			for (Group currentGroup : setOfFactorizableDescriptors) {
				List<Integer> letterPositionsCoveredByThisGroup = DescriptorSpanGetterImpl.getDescriptorSpan(currentGroup);
				setOfLetterPositionsCovered.addAll(letterPositionsCoveredByThisGroup);
			}
			if (setOfLetterPositionsCovered.size() != signal.getSignalSize())
				oneSetAtLeastDoNotCoverTheWholeString = true;
			
		}
		assertTrue(oneSetAtLeastDoNotCoverTheWholeString);		
	}
	

	
	@Test
	public void whenNextGenerationWillBeTheLastThenAllSetsCoverTheWholeString() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean oneSetAtLeastDoNotCoverTheWholeString = false;
		ArrayList<Group> previousDescriptors = new ArrayList<Group>();
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> previousGenOfDescriptors = signal.getGroups();
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
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderA = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(0));
		List<IRelationDataContainer> gen2Size1RDContainersA = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerA : gen2Size1RDContainersA) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerA, listOf1ComponentA);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}
		//Build Gen2Size1_B
		ArrayList<Group> listOf1ComponentB = new ArrayList<Group>();
		Group groupB = (Group) previousGenOfDescriptors.get(1);
		listOf1ComponentB.add(groupB);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderB = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(1));
		List<IRelationDataContainer> gen2Size1RDContainersB = 
				gen2Size1RDContainerBuilderB.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerB : gen2Size1RDContainersB) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerB, listOf1ComponentB);
			Group thisDescriptor = (Group) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}		
		//Build Gen2Size1_C
		ArrayList<Group> listOf1ComponentC = new ArrayList<Group>();
		Group groupC = (Group) previousGenOfDescriptors.get(2);
		listOf1ComponentC.add(groupC);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderC = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(2));
		List<IRelationDataContainer> gen2Size1RDContainersC = 
				gen2Size1RDContainerBuilderC.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerC : gen2Size1RDContainersC) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerC, listOf1ComponentC);
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
		Position positionAB = new Position(false, Settings.AWAITING_POSITION_VALUE);
		Group groupAB = new Group(false, size2, positionAB, relationsAB);
		previousDescriptors.add(groupAB);
		//BC
		SequenceRel relationBCplatonicLetter = new SequenceRel(false, dimensionPlatonicLetter, enumeration23, sequence1);
		SequenceRel relationBCSize = new SequenceRel(false, dimensionSize, enumeration11, sequence0);
		RelationX2 relationX2BC = new RelationX2(false, relationBCplatonicLetter, relationBCSize);
		HowManyGroups groupX2BC = new GroupX2(false, previousGenOfDescriptors.get(1), previousGenOfDescriptors.get(2));
		Groups groupsBC = new Groups(false, size2, groupX2BC);
		RelationsOrLetter relationsBC = new Relations(false, groupsBC, dimensionX2, relationX2BC);
		Position positionBC = new Position(false, Settings.AWAITING_POSITION_VALUE);
		Group groupBC = new Group(false, size2, positionBC, relationsBC);
		previousDescriptors.add(groupBC);		
		//Assert
		IComponentGrouper componentGrouper = new ComponentGrouperImpl(2, true, signal, previousDescriptors);
		Set<List<Group>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Group> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			Set<Integer> setOfLetterPositionsCovered = new HashSet<Integer>();
			for (Group currentGroup : setOfFactorizableDescriptors) {
				List<Integer> letterPositionsCoveredByThisGroup = DescriptorSpanGetterImpl.getDescriptorSpan(currentGroup);
				setOfLetterPositionsCovered.addAll(letterPositionsCoveredByThisGroup);
			}
			if (setOfLetterPositionsCovered.size() != signal.getSignalSize())
				oneSetAtLeastDoNotCoverTheWholeString = true;
			
		}
		assertFalse(oneSetAtLeastDoNotCoverTheWholeString);		
	}	

}
