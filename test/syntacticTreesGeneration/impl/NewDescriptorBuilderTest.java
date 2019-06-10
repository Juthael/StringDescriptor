package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;
import model.synTreeModel.IFrame;
import model.synTreeModel.IGrammaticalST;
import settings.Settings;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IGen2Size1RelationDataContainerBuilder;
import syntacticTreesGeneration.INewDescriptorBuilder;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.ISymmetryRelationalData;
import syntacticTreesGeneration.impl.EnumerationRelationalData;
import syntacticTreesGeneration.impl.Gen2Size1RelationDataContainerBuilder;
import syntacticTreesGeneration.impl.NewDescriptorBuilder;
import syntacticTreesGeneration.impl.RelationDataContainer;
import syntacticTreesGeneration.impl.SequenceRelationalData;
import syntacticTreesGeneration.impl.SignalBuilder;
import syntacticTreesGeneration.impl.SymmetryRelationalData;

public class NewDescriptorBuilderTest {

	ISignalBuilder signalBuilder;
	ISignal signal;
	List<Frame> listOfFramesABC;
	
	@Test
	public void whenComponentsDontCoverThenWholeStringThenFrameIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		signalBuilder = new SignalBuilder("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfFramesABC = new ArrayList<Frame>();
		for (IFrame iFrame : signal.getFrames())
			listOfFramesABC.add((Frame) iFrame);
		List<Frame> listOfFramesAB = new ArrayList<Frame>();
		listOfFramesAB.add(listOfFramesABC.get(0));
		listOfFramesAB.add(listOfFramesABC.get(1));
		IRelationDataContainer relationDataContainer = new RelationDataContainer();
		String dimension1 = "frame/size";
		String dimension2 = "frame/letter/platonicLetter";
		IEnumerationRelationalData enumerationRD1 = new EnumerationRelationalData(dimension1, "1,1");
		IEnumerationRelationalData enumerationRD2 = new EnumerationRelationalData(dimension2, "1,2");
		ISequenceRelationalData sequenceRD1 = new SequenceRelationalData(dimension1, "1,1", "0");
		ISequenceRelationalData sequenceRD2 = new SequenceRelationalData(dimension2, "1,2", "1");
		ISymmetryRelationalData symmetry1 = 
				new SymmetryRelationalData(dimension1, "1,1", "withoutCentralElement");
		relationDataContainer.addEnumeration(enumerationRD1);
		relationDataContainer.addEnumeration(enumerationRD2);
		relationDataContainer.addSequence(sequenceRD1);
		relationDataContainer.addSequence(sequenceRD2);
		relationDataContainer.addSymmetry(symmetry1);
		INewDescriptorBuilder newDescriptorBuilder = 
				new NewDescriptorBuilder((ICopycatSignal) signal, relationDataContainer, listOfFramesAB);
		IGrammaticalST descriptor = newDescriptorBuilder.getNewDescriptor();
		assertEquals(descriptor.getDescriptorName(), "frame");
	}
	
	@Test
	public void whenComponentsCoverTheWholeStringAndRDContainerEmptyThenCharStringIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		try {
			signalBuilder = new SignalBuilder("abc", "fromLeftToRight");
			signal = signalBuilder.getSignal();
			listOfFramesABC = new ArrayList<Frame>();
			for (IFrame iFrame : signal.getFrames())
				listOfFramesABC.add((Frame) iFrame);			
			IRelationDataContainer relationDataContainer = new RelationDataContainer();
			relationDataContainer.setNewDescriptorWillCoverTheWholeString(true);
			INewDescriptorBuilder newDescriptorBuilder = 
					new NewDescriptorBuilder((ICopycatSignal) signal, relationDataContainer, listOfFramesABC);
			IGrammaticalST descriptor = newDescriptorBuilder.getNewDescriptor();
			assertEquals(descriptor.getDescriptorName(), "charString");
		}
		catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void whenComponentsCoverTheWholeStringAndRDContainerIsntEmptyThenCharStringIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		signalBuilder = new SignalBuilder("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfFramesABC = new ArrayList<Frame>();
		for (IFrame iFrame : signal.getFrames())
			listOfFramesABC.add((Frame) iFrame);
		IRelationDataContainer relationDataContainer = new RelationDataContainer();
		String dimension1 = "frame/size";
		String dimension2 = "frame/letter/platonicLetter";
		IEnumerationRelationalData enumerationRD1 = new EnumerationRelationalData(dimension1, "1,1,1");
		IEnumerationRelationalData enumerationRD2 = new EnumerationRelationalData(dimension2, "1,2,3");
		ISequenceRelationalData sequenceRD1 = new SequenceRelationalData(dimension1, "1,1,1", "0");
		ISequenceRelationalData sequenceRD2 = new SequenceRelationalData(dimension2, "1,2,3", "1");
		ISymmetryRelationalData symmetry1 = 
				new SymmetryRelationalData(dimension1, "1,1,1", "withCentralElement");
		relationDataContainer.addEnumeration(enumerationRD1);
		relationDataContainer.addEnumeration(enumerationRD2);
		relationDataContainer.addSequence(sequenceRD1);
		relationDataContainer.addSequence(sequenceRD2);
		relationDataContainer.addSymmetry(symmetry1);
		relationDataContainer.setNewDescriptorWillCoverTheWholeString(true);
		INewDescriptorBuilder newDescriptorBuilder = 
				new NewDescriptorBuilder((ICopycatSignal) signal, relationDataContainer, listOfFramesABC);
		IGrammaticalST descriptor = newDescriptorBuilder.getNewDescriptor();
		assertEquals(descriptor.getDescriptorName(), "charString");		
	}
	
	@Test
	public void whenComponentsAreGen2Size1FromFirstLetterThenExpectedNumberOfDescriptorsIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		signalBuilder = new SignalBuilder("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfFramesABC = new ArrayList<Frame>();
		for (IFrame iFrame : signal.getFrames())
			listOfFramesABC.add((Frame) iFrame);
		IGen2Size1RelationDataContainerBuilder gen2Size1ContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilder(signal, (Frame) signal.getFrames().get(0));
		List<IRelationDataContainer> listOfContainers = 
				gen2Size1ContainerBuilder.getListOfRelationDataContainers();
		List<Frame> listOfFrames = new ArrayList<Frame>();
		listOfFrames.add((Frame) signal.getFrames().get(0));
		List<List<String>> listOfPropertyLists = new ArrayList<List<String>>();
		for (IRelationDataContainer container : listOfContainers) {
			INewDescriptorBuilder newDescBuilder = 
					new NewDescriptorBuilder((ICopycatSignal) signal, container, listOfFrames);
			IGrammaticalST descriptor = newDescBuilder.getNewDescriptor();
			List<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
			listOfPropertyLists.add(listOfProperties);
			/* for (String property : listOfProperties)
				System.out.println(property);
			System.out.println(""); */
		}
		int nbOfDescriptorsExpected = 0;
		if (Settings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS)
			nbOfDescriptorsExpected++;
		if (Settings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER) {
			for (int i=Settings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; 
					i<=Settings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; i++) {
				nbOfDescriptorsExpected++;
			}
		}
		assertTrue(nbOfDescriptorsExpected == listOfPropertyLists.size());
	}

}
