package syntacticTreesGeneration.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.AbsCommonDiff;
import model.copycatModel.synTreeGrammar.CommonDiff;
import model.copycatModel.synTreeGrammar.Dimension;
import model.copycatModel.synTreeGrammar.DimensionX;
import model.copycatModel.synTreeGrammar.Enumeration;
import model.copycatModel.synTreeGrammar.Frame;
import model.copycatModel.synTreeGrammar.FrameX;
import model.copycatModel.synTreeGrammar.Components;
import model.copycatModel.synTreeGrammar.HowManyFrames;
import model.copycatModel.synTreeGrammar.Position;
import model.copycatModel.synTreeGrammar.Relation;
import model.copycatModel.synTreeGrammar.RelationX;
import model.copycatModel.synTreeGrammar.Relations;
import model.copycatModel.synTreeGrammar.RelationsOrLetter;
import model.copycatModel.synTreeGrammar.Sequence;
import model.copycatModel.synTreeGrammar.SequenceRel;
import model.copycatModel.synTreeGrammar.Size;
import model.synTreeModel.ISignal;
import settings.Settings;
import syntacticTreesGeneration.IComponentGrouper;
import syntacticTreesGeneration.IGen2Size1RelationDataContainerBuilder;
import syntacticTreesGeneration.INewDescriptorBuilder;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.ISignalBuilder;

public class ComponentGrouperImplTest {

	@Test
	public void AllSetsCoverAdjacentLetters() throws SynTreeGenerationException {
		boolean setsCoverAdjacentLetters = true;
		int generationNumber = 1;
		boolean nextGenerationWillBeTheLast = false;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Frame> previousGenOfDescriptors = signal.getFrames();
		IComponentGrouper componentGrouper = 
				new ComponentGrouperImpl(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		Set<List<Frame>> setOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setOfFactorizableDescriptors : setOfFactorizableDescriptorSets) {
			Set<Integer> setOfLetterPositionsCoveredByThisSet = new HashSet<Integer>();
			for (Frame currentFrame : setOfFactorizableDescriptors) {
				List<Integer> letterPositionsCoveredByCurrentFrame = 
						DescriptorSpanGetterImpl.getDescriptorSpan(currentFrame);
				setOfLetterPositionsCoveredByThisSet.addAll(letterPositionsCoveredByCurrentFrame);
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
		List<Frame> previousGenOfDescriptors = signal.getFrames();
		IComponentGrouper componentGrouper = 
				new ComponentGrouperImpl(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		Set<List<Frame>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			List<Integer> letterPositionsCoveredByThisSet = new ArrayList<Integer>();
			for (Frame currentFrame : setOfFactorizableDescriptors) {
				List<Integer> positions = DescriptorSpanGetterImpl.getDescriptorSpan(currentFrame);
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
		List<Frame> previousGenOfDescriptors = signal.getFrames();
		IComponentGrouper componentGrouper = 
				new ComponentGrouperImpl(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		Set<List<Frame>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			if (setOfFactorizableDescriptors.size() == 1 && oneSetAtLeastIsSize1 == false)
				oneSetAtLeastIsSize1 = true;
		}
		assertTrue(oneSetAtLeastIsSize1);			
	}
	

	
	@Test
	public void whenComponentsArent1stGenerationThenSetsCanNotBeOfSize1() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean oneSetOfComponentsAtLeastIsSize1 = false;
		ArrayList<Frame> previousDescriptors = new ArrayList<Frame>();
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Frame> previousGenOfDescriptors = signal.getFrames();
		CommonDiff commonDiff1 = new CommonDiff("1");
		AbsCommonDiff absCommonDiff1 = new AbsCommonDiff("1");
		CommonDiff commonDiff0 = new CommonDiff("0");
		AbsCommonDiff absCommonDiff0 = new AbsCommonDiff("0");
		Sequence sequence1 = new Sequence(commonDiff1, absCommonDiff1);
		Sequence sequence0 = new Sequence(commonDiff0, absCommonDiff0);
		Enumeration enumeration12 = new Enumeration("1,2");
		Enumeration enumeration23 = new Enumeration("2,3");
		Enumeration enumeration11 = new Enumeration("1,1");
		Dimension dimensionPlatonicLetter = new Dimension("platonicLetter");
		Dimension dimensionSize = new Dimension("size");
		Size size2 = new Size("2");
		//Build Gen2Size1_A
		ArrayList<Frame> listOf1ComponentA = new ArrayList<Frame>();
		Frame frameA = (Frame) previousGenOfDescriptors.get(0);
		listOf1ComponentA.add(frameA);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderA = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(0));
		List<IRelationDataContainer> gen2Size1RDContainersA = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerA : gen2Size1RDContainersA) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerA, listOf1ComponentA);
			Frame thisDescriptor = (Frame) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}
		//Build Gen2Size1_B
		ArrayList<Frame> listOf1ComponentB = new ArrayList<Frame>();
		Frame frameB = (Frame) previousGenOfDescriptors.get(1);
		listOf1ComponentB.add(frameB);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderB = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(1));
		List<IRelationDataContainer> gen2Size1RDContainersB = 
				gen2Size1RDContainerBuilderB.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerB : gen2Size1RDContainersB) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerB, listOf1ComponentB);
			Frame thisDescriptor = (Frame) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}		
		//Build Gen2Size1_C
		ArrayList<Frame> listOf1ComponentC = new ArrayList<Frame>();
		Frame frameC = (Frame) previousGenOfDescriptors.get(2);
		listOf1ComponentC.add(frameC);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderC = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(2));
		List<IRelationDataContainer> gen2Size1RDContainersC = 
				gen2Size1RDContainerBuilderC.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerC : gen2Size1RDContainersC) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerC, listOf1ComponentC);
			Frame thisDescriptor = (Frame) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}			
		
		//Build Gen2usual
		List<Dimension> listOfDimensionsX2 = new ArrayList<Dimension>();
		listOfDimensionsX2.add(dimensionPlatonicLetter);
		listOfDimensionsX2.add(dimensionSize);
		DimensionX dimensionX2 = new DimensionX(listOfDimensionsX2);
		//AB
		SequenceRel relationABplatonicLetter = new SequenceRel(dimensionPlatonicLetter, enumeration12, sequence1);
		SequenceRel relationABSize = new SequenceRel(dimensionSize, enumeration11, sequence0);
		List<Relation> listOfRelationsAB = new ArrayList<Relation>();
		listOfRelationsAB.add(relationABplatonicLetter);
		listOfRelationsAB.add(relationABSize);
		RelationX relationX2AB = new RelationX(listOfRelationsAB);
		List<Frame> previousGenOfDescriptors01 = new ArrayList<Frame>();
		previousGenOfDescriptors01.add(previousGenOfDescriptors.get(0));
		previousGenOfDescriptors01.add(previousGenOfDescriptors.get(1));
		HowManyFrames frameX2AB = new FrameX(previousGenOfDescriptors01);
		Components componentsAB = new Components(size2, frameX2AB);
		RelationsOrLetter relationsAB = new Relations(componentsAB, dimensionX2, relationX2AB);
		Position positionAB = new Position(Settings.AWAITING_POSITION_VALUE);
		Frame frameAB = new Frame(false, size2, positionAB, relationsAB);
		previousDescriptors.add(frameAB);
		//BC
		SequenceRel relationBCplatonicLetter = new SequenceRel(dimensionPlatonicLetter, enumeration23, sequence1);
		SequenceRel relationBCSize = new SequenceRel(dimensionSize, enumeration11, sequence0);
		List<Relation> listOfRelationsBC = new ArrayList<Relation>();
		listOfRelationsBC.add(relationBCplatonicLetter);
		listOfRelationsBC.add(relationBCSize);
		RelationX relationX2BC = new RelationX(listOfRelationsBC);
		List<Frame> previousGenOfDescriptors12 = new ArrayList<Frame>();
		previousGenOfDescriptors12.add(previousGenOfDescriptors.get(1));
		previousGenOfDescriptors12.add(previousGenOfDescriptors.get(2));
		HowManyFrames frameX2BC = new FrameX(previousGenOfDescriptors12);
		Components componentsBC = new Components(size2, frameX2BC);
		RelationsOrLetter relationsBC = new Relations(componentsBC, dimensionX2, relationX2BC);
		Position positionBC = new Position(Settings.AWAITING_POSITION_VALUE);
		Frame frameBC = new Frame(false, size2, positionBC, relationsBC);
		previousDescriptors.add(frameBC);		
		//Assert
		IComponentGrouper componentGrouper = new ComponentGrouperImpl(2, false, signal, previousDescriptors);
		Set<List<Frame>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			if (setOfFactorizableDescriptors.size() < 1)
				oneSetOfComponentsAtLeastIsSize1 = true;
			/* StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("positions : ");
			for (Frame frame : setOfFactorizableDescriptors) {
				ArrayList<Integer> positionList = DescriptorSpanGetterV1.getDescriptorSpan(frame);
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
		List<Frame> previousGenOfDescriptors = signal.getFrames();
		IComponentGrouper componentGrouper = 
				new ComponentGrouperImpl(generationNumber, nextGenerationWillBeTheLast, signal, previousGenOfDescriptors);
		Set<List<Frame>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			Set<Integer> setOfLetterPositionsCovered = new HashSet<Integer>();
			for (Frame currentFrame : setOfFactorizableDescriptors) {
				List<Integer> letterPositionsCoveredByThisFrame = DescriptorSpanGetterImpl.getDescriptorSpan(currentFrame);
				setOfLetterPositionsCovered.addAll(letterPositionsCoveredByThisFrame);
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
		ArrayList<Frame> previousDescriptors = new ArrayList<Frame>();
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Frame> previousGenOfDescriptors = signal.getFrames();
		CommonDiff commonDiff1 = new CommonDiff("1");
		AbsCommonDiff absCommonDiff1 = new AbsCommonDiff("1");
		CommonDiff commonDiff0 = new CommonDiff("0");
		AbsCommonDiff absCommonDiff0 = new AbsCommonDiff("0");
		Sequence sequence1 = new Sequence(commonDiff1, absCommonDiff1);
		Sequence sequence0 = new Sequence(commonDiff0, absCommonDiff0);
		Enumeration enumeration12 = new Enumeration("1,2");
		Enumeration enumeration23 = new Enumeration("2,3");
		Enumeration enumeration11 = new Enumeration("1,1");
		Dimension dimensionPlatonicLetter = new Dimension("platonicLetter");
		Dimension dimensionSize = new Dimension("size");
		Size size2 = new Size("2");
		//Build Gen2Size1_A
		ArrayList<Frame> listOf1ComponentA = new ArrayList<Frame>();
		Frame frameA = (Frame) previousGenOfDescriptors.get(0);
		listOf1ComponentA.add(frameA);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderA = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(0));
		List<IRelationDataContainer> gen2Size1RDContainersA = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerA : gen2Size1RDContainersA) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerA, listOf1ComponentA);
			Frame thisDescriptor = (Frame) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}
		//Build Gen2Size1_B
		ArrayList<Frame> listOf1ComponentB = new ArrayList<Frame>();
		Frame frameB = (Frame) previousGenOfDescriptors.get(1);
		listOf1ComponentB.add(frameB);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderB = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(1));
		List<IRelationDataContainer> gen2Size1RDContainersB = 
				gen2Size1RDContainerBuilderB.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerB : gen2Size1RDContainersB) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerB, listOf1ComponentB);
			Frame thisDescriptor = (Frame) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}		
		//Build Gen2Size1_C
		ArrayList<Frame> listOf1ComponentC = new ArrayList<Frame>();
		Frame frameC = (Frame) previousGenOfDescriptors.get(2);
		listOf1ComponentC.add(frameC);
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderC = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, previousGenOfDescriptors.get(2));
		List<IRelationDataContainer> gen2Size1RDContainersC = 
				gen2Size1RDContainerBuilderC.getListOfRelationDataContainers();
		for (IRelationDataContainer gen2Size1RDContainerC : gen2Size1RDContainersC) {
			INewDescriptorBuilder newDescBuilder = new NewDescriptorBuilderImpl(signal, gen2Size1RDContainerC, listOf1ComponentC);
			Frame thisDescriptor = (Frame) newDescBuilder.getNewDescriptor();
			previousDescriptors.add(thisDescriptor);
		}			
		
		//Build Gen2usual
		List<Dimension> listOfDimensionsX2 = new ArrayList<Dimension>();
		listOfDimensionsX2.add(dimensionPlatonicLetter);
		listOfDimensionsX2.add(dimensionSize);
		DimensionX dimensionX2 = new DimensionX(listOfDimensionsX2);
		//AB
		SequenceRel relationABplatonicLetter = new SequenceRel(dimensionPlatonicLetter, enumeration12, sequence1);
		SequenceRel relationABSize = new SequenceRel(dimensionSize, enumeration11, sequence0);
		List<Relation> listOfRelationsAB = new ArrayList<Relation>();
		listOfRelationsAB.add(relationABplatonicLetter);
		listOfRelationsAB.add(relationABSize);		
		RelationX relationX2AB = new RelationX(listOfRelationsAB);
		List<Frame> previousGenOfDescriptors01B = new ArrayList<Frame>();
		previousGenOfDescriptors01B.add(previousGenOfDescriptors.get(0));
		previousGenOfDescriptors01B.add(previousGenOfDescriptors.get(1));		
		HowManyFrames frameX2AB = new FrameX(previousGenOfDescriptors01B);
		Components componentsAB = new Components(size2, frameX2AB);
		RelationsOrLetter relationsAB = new Relations(componentsAB, dimensionX2, relationX2AB);
		Position positionAB = new Position(Settings.AWAITING_POSITION_VALUE);
		Frame frameAB = new Frame(false, size2, positionAB, relationsAB);
		previousDescriptors.add(frameAB);
		//BC
		SequenceRel relationBCplatonicLetter = new SequenceRel(dimensionPlatonicLetter, enumeration23, sequence1);
		SequenceRel relationBCSize = new SequenceRel(dimensionSize, enumeration11, sequence0);
		List<Relation> listOfRelationsBC = new ArrayList<Relation>();
		listOfRelationsBC.add(relationBCplatonicLetter);
		listOfRelationsBC.add(relationBCSize);
		RelationX relationX2BC = new RelationX(listOfRelationsBC);
		List<Frame> previousGenOfDescriptors12B = new ArrayList<Frame>();
		previousGenOfDescriptors12B.add(previousGenOfDescriptors.get(1));
		previousGenOfDescriptors12B.add(previousGenOfDescriptors.get(2));		
		HowManyFrames frameX2BC = new FrameX(previousGenOfDescriptors12B);
		Components componentsBC = new Components(size2, frameX2BC);
		RelationsOrLetter relationsBC = new Relations(componentsBC, dimensionX2, relationX2BC);
		Position positionBC = new Position(Settings.AWAITING_POSITION_VALUE);
		Frame frameBC = new Frame(false, size2, positionBC, relationsBC);
		previousDescriptors.add(frameBC);		
		//Assert
		IComponentGrouper componentGrouper = new ComponentGrouperImpl(2, true, signal, previousDescriptors);
		Set<List<Frame>> listOfFactorizableDescriptorSets = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setOfFactorizableDescriptors : listOfFactorizableDescriptorSets) {
			Set<Integer> setOfLetterPositionsCovered = new HashSet<Integer>();
			for (Frame currentFrame : setOfFactorizableDescriptors) {
				List<Integer> letterPositionsCoveredByThisFrame = DescriptorSpanGetterImpl.getDescriptorSpan(currentFrame);
				setOfLetterPositionsCovered.addAll(letterPositionsCoveredByThisFrame);
			}
			if (setOfLetterPositionsCovered.size() != signal.getSignalSize())
				oneSetAtLeastDoNotCoverTheWholeString = true;
			
		}
		assertFalse(oneSetAtLeastDoNotCoverTheWholeString);		
	}	

}
