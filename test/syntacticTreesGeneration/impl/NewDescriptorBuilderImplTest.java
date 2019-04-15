package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Group;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISynTreeElement;
import settings.Settings;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IGen2Size1RelationDataContainerBuilder;
import syntacticTreesGeneration.INewDescriptorBuilder;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.ISymmetryRelationalData;
import syntacticTreesGeneration.impl.EnumerationRelationalDataImpl;
import syntacticTreesGeneration.impl.Gen2Size1RelationDataContainerBuilderImpl;
import syntacticTreesGeneration.impl.NewDescriptorBuilderImpl;
import syntacticTreesGeneration.impl.RelationDataContainerImpl;
import syntacticTreesGeneration.impl.SequenceRelationalDataImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;
import syntacticTreesGeneration.impl.SymmetryRelationalDataImpl;

public class NewDescriptorBuilderImplTest {

	ISignalBuilder signalBuilder;
	ISignal signal;
	List<Group> listOfGroupsABC;
	
	@Test
	public void whenComponentsDontCoverThenWholeStringThenGroupIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfGroupsABC = signal.getGroups();	
		List<Group> listOfGroupsAB = new ArrayList<Group>();
		listOfGroupsAB.add(listOfGroupsABC.get(0));
		listOfGroupsAB.add(listOfGroupsABC.get(1));
		IRelationDataContainer relationDataContainer = new RelationDataContainerImpl();
		String dimension1 = "group/size";
		String dimension2 = "group/letter/platonicLetter";
		IEnumerationRelationalData enumerationRD1 = new EnumerationRelationalDataImpl(dimension1, "1,1");
		IEnumerationRelationalData enumerationRD2 = new EnumerationRelationalDataImpl(dimension2, "1,2");
		ISequenceRelationalData sequenceRD1 = new SequenceRelationalDataImpl(dimension1, "1,1", "0");
		ISequenceRelationalData sequenceRD2 = new SequenceRelationalDataImpl(dimension2, "1,2", "1");
		ISymmetryRelationalData symmetry1 = 
				new SymmetryRelationalDataImpl(dimension1, "1,1", "withoutCentralElement");
		relationDataContainer.addEnumeration(enumerationRD1);
		relationDataContainer.addEnumeration(enumerationRD2);
		relationDataContainer.addSequence(sequenceRD1);
		relationDataContainer.addSequence(sequenceRD2);
		relationDataContainer.addSymmetry(symmetry1);
		INewDescriptorBuilder newDescriptorBuilder = 
				new NewDescriptorBuilderImpl(signal, relationDataContainer, listOfGroupsAB);
		ISynTreeElement descriptor = newDescriptorBuilder.getNewDescriptor();
		assertEquals(descriptor.getDescriptorName(), "group");
	}
	
	@Test
	public void whenComponentsCoverTheWholeStringAndRDContainerEmptyThenCharStringIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfGroupsABC = signal.getGroups();			
		IRelationDataContainer relationDataContainer = new RelationDataContainerImpl();
		relationDataContainer.setNewDescriptorWillCoverTheWholeString(true);
		INewDescriptorBuilder newDescriptorBuilder = 
				new NewDescriptorBuilderImpl(signal, relationDataContainer, listOfGroupsABC);
		ISynTreeElement descriptor = newDescriptorBuilder.getNewDescriptor();
		assertEquals(descriptor.getDescriptorName(), "charString");
	}
	
	@Test
	public void whenComponentsCoverTheWholeStringAndRDContainerIsntEmptyThenCharStringIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfGroupsABC = signal.getGroups();			
		IRelationDataContainer relationDataContainer = new RelationDataContainerImpl();
		String dimension1 = "group/size";
		String dimension2 = "group/letter/platonicLetter";
		IEnumerationRelationalData enumerationRD1 = new EnumerationRelationalDataImpl(dimension1, "1,1,1");
		IEnumerationRelationalData enumerationRD2 = new EnumerationRelationalDataImpl(dimension2, "1,2,3");
		ISequenceRelationalData sequenceRD1 = new SequenceRelationalDataImpl(dimension1, "1,1,1", "0");
		ISequenceRelationalData sequenceRD2 = new SequenceRelationalDataImpl(dimension2, "1,2,3", "1");
		ISymmetryRelationalData symmetry1 = 
				new SymmetryRelationalDataImpl(dimension1, "1,1,1", "withCentralElement");
		relationDataContainer.addEnumeration(enumerationRD1);
		relationDataContainer.addEnumeration(enumerationRD2);
		relationDataContainer.addSequence(sequenceRD1);
		relationDataContainer.addSequence(sequenceRD2);
		relationDataContainer.addSymmetry(symmetry1);
		relationDataContainer.setNewDescriptorWillCoverTheWholeString(true);
		INewDescriptorBuilder newDescriptorBuilder = 
				new NewDescriptorBuilderImpl(signal, relationDataContainer, listOfGroupsABC);
		ISynTreeElement descriptor = newDescriptorBuilder.getNewDescriptor();
		assertEquals(descriptor.getDescriptorName(), "charString");		
	}
	
	@Test
	public void whenComponentsAreGen2Size1FromFirstLetterThenExpectedNumberOfDescriptorsIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfGroupsABC = signal.getGroups();	
		IGen2Size1RelationDataContainerBuilder gen2Size1ContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderImpl(signal, signal.getGroups().get(0));
		List<IRelationDataContainer> listOfContainers = 
				gen2Size1ContainerBuilder.getListOfRelationDataContainers();
		List<Group> listOfGroups = new ArrayList<Group>();
		listOfGroups.add(signal.getGroups().get(0));
		List<List<String>> listOfPropertyLists = new ArrayList<List<String>>();
		for (IRelationDataContainer container : listOfContainers) {
			INewDescriptorBuilder newDescBuilder = 
					new NewDescriptorBuilderImpl(signal, container, listOfGroups);
			ISynTreeElement descriptor = newDescBuilder.getNewDescriptor();
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
