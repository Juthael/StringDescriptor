package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;
import model.synTreeModel.IFrame;
import model.synTreeModel.IGrammaticalST;
import settings.Settings;
import syntacticTreesGeneration.IGen2Size1RelationDataContainerBuilder;
import syntacticTreesGeneration.INewDescriptorBuilder;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.Gen2Size1RelationDataContainerBuilderImpl;
import syntacticTreesGeneration.impl.NewDescriptorBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class Gen2Size1RelationDataContainerBuilderImplTest {

	ISignalBuilder signalBuilderABC;
	ISignal signalABC;
	List<Frame> descriptorsSignalABC = new ArrayList<Frame>();	
	
	@Before
	public void initialize() throws SynTreeGenerationException, CloneNotSupportedException {
		signalBuilderABC = new SignalBuilderImpl("abc", "fromLeftToRight");
		signalABC = signalBuilderABC.getSignal();
		for (IFrame iFrame :  signalABC.getFrames())
			descriptorsSignalABC.add((Frame) iFrame);
	}
	
	@Test
	public void when1stGenDescriptorCover1stLetterThenRDContainerSizeIsAccordedToSettings() 
			throws SynTreeGenerationException {
		int nbOfRDContainers = 0;
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, (Frame) signalABC.getFrames().get(0));
		List<IRelationDataContainer> listOfRDContainer = 
				gen2Size1RDContainerBuilder.getListOfRelationDataContainers();
		if (Settings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS) {
			nbOfRDContainers++;
		}
		if (Settings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER) {
			for (int inc = Settings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; inc <= Settings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; inc++)
				nbOfRDContainers++;
		}
		assertEquals(listOfRDContainer.size(), nbOfRDContainers);
	}
	
	@Test
	public void when1stGenDescriptorCoverLastLetterThenRDContainerSizeIsAccordedToSettings() 
			throws SynTreeGenerationException {
		int nbOfRDContainers = 0;
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, (Frame) signalABC.getFrames().get(2));
		List<IRelationDataContainer> listOfRDContainer = 
				gen2Size1RDContainerBuilder.getListOfRelationDataContainers();
		if (Settings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS) {
			nbOfRDContainers++;
		}
		if (Settings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_LAST_LETTER) {
			for (int inc = Settings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; inc <= Settings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; inc++)
				nbOfRDContainers++;
		}
		assertEquals(listOfRDContainer.size(), nbOfRDContainers);		
	}
	
	@Test
	public void when1stGenDescriptorDoesntCoverSpecialPositionThenRDContainerSizeIsAccordedToSettings() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		int nbOfRDContainers = 0;
		ISignalBuilder signalBuilderABCD = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signalABCD = signalBuilderABCD.getSignal();
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABCD, (Frame) signalABCD.getFrames().get(2));
		List<IRelationDataContainer> listOfRDContainer = 
				gen2Size1RDContainerBuilder.getListOfRelationDataContainers();
		if (Settings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_ALL_LETTERS) {
			nbOfRDContainers++;
		}
		if (Settings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_ALL_LETTERS) {
			for (int inc = Settings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; inc <= Settings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; inc++)
				nbOfRDContainers++;
		}
		assertEquals(listOfRDContainer.size(), nbOfRDContainers);			
	}
	
	@Test
	public void gen2Size1RDContainerAllowsFrameBuildingWithoutThrowingException() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		List<IGrammaticalST> newFrames = new ArrayList<IGrammaticalST>();
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderA = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, (Frame) signalABC.getFrames().get(0));
		List<IRelationDataContainer> listOfRDContainerA = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderB = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, (Frame) signalABC.getFrames().get(1));
		List<IRelationDataContainer> listOfRDContainerB = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderC = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, (Frame) signalABC.getFrames().get(2));
		List<IRelationDataContainer> listOfRDContainerC = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();		
		List<Frame> listWithFrameA = new ArrayList<Frame>();
		List<Frame> listWithFrameB = new ArrayList<Frame>();
		List<Frame> listWithFrameC = new ArrayList<Frame>();
		listWithFrameA.add((Frame) signalABC.getFrames().get(0));
		listWithFrameB.add((Frame) signalABC.getFrames().get(1));
		listWithFrameC.add((Frame) signalABC.getFrames().get(2));
		try {
			for (IRelationDataContainer RDContainerA : listOfRDContainerA) {
				INewDescriptorBuilder newDescriptorBuilder = new NewDescriptorBuilderImpl((ICopycatSignal) signalABC, RDContainerA, listWithFrameA);
				newFrames.add(newDescriptorBuilder.getNewDescriptor());
			}
			for (IRelationDataContainer RDContainerB : listOfRDContainerB) {
				INewDescriptorBuilder newDescriptorBuilder = new NewDescriptorBuilderImpl((ICopycatSignal) signalABC, RDContainerB, listWithFrameB);
				newFrames.add(newDescriptorBuilder.getNewDescriptor());
			}
			for (IRelationDataContainer RDContainerC : listOfRDContainerC) {
				INewDescriptorBuilder newDescriptorBuilder = new NewDescriptorBuilderImpl((ICopycatSignal) signalABC, RDContainerC, listWithFrameC);
				newFrames.add(newDescriptorBuilder.getNewDescriptor());
			}			
		}
		catch (Exception unexpected) {
			fail();
		}
		/*
		for (AbstractDescriptorInterface descriptor : newFrames) {
			ArrayList<String> properties = descriptor.getListOfPropertiesWithPath();
			for (String property : properties)
				System.out.println(property);
			System.out.println("");			
		}
		*/
	}

}
