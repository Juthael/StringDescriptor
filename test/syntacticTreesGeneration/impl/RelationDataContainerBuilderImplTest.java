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
import model.copycatModel.synTreeGrammar.Frame;
import model.copycatModel.synTreeGrammar.FrameX;
import model.copycatModel.synTreeGrammar.Components;
import model.copycatModel.synTreeGrammar.Position;
import model.copycatModel.synTreeGrammar.Relation;
import model.copycatModel.synTreeGrammar.Relations;
import model.copycatModel.synTreeGrammar.Sequence;
import model.copycatModel.synTreeGrammar.SequenceRel;
import model.copycatModel.synTreeGrammar.Size;
import model.copycatModel.synTreeGrammar.Structure;
import model.synTreeModel.IFrame;
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
	List<Frame> descriptorsSignalABC;
	List<Frame> descriptorsSignalABCD;
	Frame abcFrame;
	CharString charStringABC;
	
	@Before
	public void initialize() throws SynTreeGenerationException, CloneNotSupportedException {
		ISignalBuilder signalBuilderABC = new SignalBuilderImpl("abc", "fromLeftToRight");
		signalABC = signalBuilderABC.getSignal();
		List<Frame> listOfFramesABC = new ArrayList<Frame>();
		for (IFrame iFrame : signalABC.getFrames())
			listOfFramesABC.add((Frame) iFrame);
		descriptorsSignalABC = listOfFramesABC;
		ISignalBuilder signalBuilderABCD = new SignalBuilderImpl("abcd", "fromLeftToRight");
		signalABCD = signalBuilderABCD.getSignal();
		List<Frame> listOfFramesABCD = new ArrayList<Frame>();
		for (IFrame iFrame : signalABCD.getFrames())
			listOfFramesABCD.add((Frame) iFrame);		
		descriptorsSignalABCD = listOfFramesABCD;
		CommonDiff abcCommonDiff = new CommonDiff("1");
		AbsCommonDiff abcAbsCommonDiff = new AbsCommonDiff("1");
		Sequence abcSequence = new Sequence(abcCommonDiff, abcAbsCommonDiff);
		Enumeration abcEnumeration = new Enumeration("1,2,3");
		Dimension abcDimension = new Dimension("platonicLetter");
		SequenceRel abcRelation = new SequenceRel(abcDimension, abcEnumeration, abcSequence);
		Size abcSize = new Size("3");
		FrameX abcFrameX3 = new FrameX(signalABC.getFrames());
		Components abcComponents = new Components(abcSize, abcFrameX3);
		Relations abcRelations = new Relations(abcComponents, abcDimension, abcRelation);
		Position abcPosition = new Position(Settings.AWAITING_POSITION_VALUE);
		Size abcFrameSize = new Size("3");
		abcFrame = new Frame(false, abcFrameSize, abcPosition, abcRelations);
		Size abcComponentsSize = new Size("1");
		Components abcComponentsFrames = new Components(abcComponentsSize, abcFrame);
		Size charStringSize = new Size("3");
		Direction charStringDirection = new Direction("fromLeftToRight");
		Relation structureRelation = getMonoStructureRelation(descriptorsSignalABC);
		Structure structure = new Structure(charStringSize, structureRelation);
		charStringABC = new CharString(charStringDirection, structure, abcComponentsFrames); 
	}

	@Test
	public void whenListOfDescriptorsInParameterIsEmptyThenThrowsException() {
		List<Frame> emptyList = new ArrayList<Frame>();
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
		List<Frame> listOfFrameWithDifferentSetsOfDimensions = new ArrayList<Frame>();
		listOfFrameWithDifferentSetsOfDimensions.add(abcFrame);
		listOfFrameWithDifferentSetsOfDimensions.add(descriptorsSignalABCD.get(3));
		IRelationDataContainerBuilder relationDataContainerBuilder = 
				new RelationDataContainerBuilderImpl(signalABCD, listOfFrameWithDifferentSetsOfDimensions);
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
		List<Frame> listOfRelatedFrames = new ArrayList<Frame>();
		listOfRelatedFrames.add(descriptorsSignalABC.get(0));
		listOfRelatedFrames.add(descriptorsSignalABC.get(1));
		IRelationDataContainerBuilder relationDataContainerBuilder = 
				new RelationDataContainerBuilderImpl(signalABC, listOfRelatedFrames);
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
	
	private Relation getMonoStructureRelation(List<Frame> listOfFrames) throws SynTreeGenerationException, CloneNotSupportedException {
		Relation structureRelation;
		String dimension = "charString/components/frame/size";
		List<String> listOfSizeValues = new ArrayList<String>();
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for(Frame frame : listOfFrames) {
			listOfLetterPositions.addAll(DescriptorSpanGetterImpl.getDescriptorSpan(frame));
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
	
	private String getCharStringSizeValue(List<Frame> listOfFrames) throws SynTreeGenerationException {
		String charStringSizeValue;
		List<Integer> listOfLetterPositions = new ArrayList<Integer>();
		for (Frame frame : listOfFrames) {
			listOfLetterPositions.addAll(DescriptorSpanGetterImpl.getDescriptorSpan(frame));
		}
		charStringSizeValue = Integer.toString(listOfLetterPositions.size());
		return charStringSizeValue;
	}	
	
	

}
