package descriptorsGeneration.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.EnumerationRelationalDataV1;
import syntacticTreesGeneration.implementations.Gen2Size1RelationDataContainerBuilderV1;
import syntacticTreesGeneration.implementations.NewDescriptorBuilderV1;
import syntacticTreesGeneration.implementations.RelationDataContainerV1;
import syntacticTreesGeneration.implementations.SequenceRelationalDataV1;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.implementations.SymmetryRelationalDataV1;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.Gen2Size1RelationDataContainerBuilderInterface;
import syntacticTreesGeneration.interfaces.NewDescriptorBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;
import syntacticTreesGeneration.interfaces.SymmetryRelationalDataInterface;

class NewDescriptorBuilderV1Test {

	SignalBuilderInterface signalBuilder;
	SignalInterface signal;
	ArrayList<Group> listOfGroupsABC;
	
	@Test
	void whenComponentsDontCoverThenWholeStringThenGroupIsBuilt() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfGroupsABC = signal.getGroups();	
		ArrayList<Group> listOfGroupsAB = new ArrayList<Group>();
		listOfGroupsAB.add(listOfGroupsABC.get(0));
		listOfGroupsAB.add(listOfGroupsABC.get(1));
		RelationDataContainerInterface relationDataContainer = new RelationDataContainerV1();
		String dimension1 = "group/size";
		String dimension2 = "group/letter/platonicLetter";
		EnumerationRelationalDataInterface enumerationRD1 = new EnumerationRelationalDataV1(dimension1, "1,1");
		EnumerationRelationalDataInterface enumerationRD2 = new EnumerationRelationalDataV1(dimension2, "1,2");
		SequenceRelationalDataInterface sequenceRD1 = new SequenceRelationalDataV1(dimension1, "1,1", "0");
		SequenceRelationalDataInterface sequenceRD2 = new SequenceRelationalDataV1(dimension2, "1,2", "1");
		SymmetryRelationalDataInterface symmetry1 = 
				new SymmetryRelationalDataV1(dimension1, "1,1", "withoutCentralElement");
		relationDataContainer.addEnumeration(enumerationRD1);
		relationDataContainer.addEnumeration(enumerationRD2);
		relationDataContainer.addSequence(sequenceRD1);
		relationDataContainer.addSequence(sequenceRD2);
		relationDataContainer.addSymmetry(symmetry1);
		relationDataContainer.cleanValuesRedundancies();
		NewDescriptorBuilderInterface newDescriptorBuilder = 
				new NewDescriptorBuilderV1(signal, relationDataContainer, listOfGroupsAB);
		AbstractDescriptorInterface descriptor = newDescriptorBuilder.getNewDescriptor();
		assertEquals(descriptor.getDescriptorName(), "group");
	}
	
	@Test
	void whenComponentsCoverTheWholeStringAndRDContainerEmptyThenCharStringIsBuilt() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfGroupsABC = signal.getGroups();			
		RelationDataContainerInterface relationDataContainer = new RelationDataContainerV1();
		relationDataContainer.setNewDescriptorWillCoverTheWholeString(true);
		NewDescriptorBuilderInterface newDescriptorBuilder = 
				new NewDescriptorBuilderV1(signal, relationDataContainer, listOfGroupsABC);
		AbstractDescriptorInterface descriptor = newDescriptorBuilder.getNewDescriptor();
		assertEquals(descriptor.getDescriptorName(), "charString");
	}
	
	@Test
	void whenComponentsCoverTheWholeStringAndRDContainerIsntEmptyThenCharStringIsBuilt() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfGroupsABC = signal.getGroups();			
		RelationDataContainerInterface relationDataContainer = new RelationDataContainerV1();
		String dimension1 = "group/size";
		String dimension2 = "group/letter/platonicLetter";
		EnumerationRelationalDataInterface enumerationRD1 = new EnumerationRelationalDataV1(dimension1, "1,1,1");
		EnumerationRelationalDataInterface enumerationRD2 = new EnumerationRelationalDataV1(dimension2, "1,2,3");
		SequenceRelationalDataInterface sequenceRD1 = new SequenceRelationalDataV1(dimension1, "1,1,1", "0");
		SequenceRelationalDataInterface sequenceRD2 = new SequenceRelationalDataV1(dimension2, "1,2,3", "1");
		SymmetryRelationalDataInterface symmetry1 = 
				new SymmetryRelationalDataV1(dimension1, "1,1,1", "withCentralElement");
		relationDataContainer.addEnumeration(enumerationRD1);
		relationDataContainer.addEnumeration(enumerationRD2);
		relationDataContainer.addSequence(sequenceRD1);
		relationDataContainer.addSequence(sequenceRD2);
		relationDataContainer.addSymmetry(symmetry1);
		relationDataContainer.setNewDescriptorWillCoverTheWholeString(true);
		relationDataContainer.cleanValuesRedundancies();
		NewDescriptorBuilderInterface newDescriptorBuilder = 
				new NewDescriptorBuilderV1(signal, relationDataContainer, listOfGroupsABC);
		AbstractDescriptorInterface descriptor = newDescriptorBuilder.getNewDescriptor();
		assertEquals(descriptor.getDescriptorName(), "charString");		
	}
	
	@Test
	void whenComponentsAreGen2Size1FromFirstLetterThenExpectedNumberOfDescriptorsIsBuilt() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		signal = signalBuilder.getSignal();
		listOfGroupsABC = signal.getGroups();	
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1ContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderV1(signal, signal.getGroups().get(0));
		List<RelationDataContainerInterface> listOfContainers = 
				gen2Size1ContainerBuilder.getListOfRelationDataContainers();
		ArrayList<Group> listOfGroups = new ArrayList<Group>();
		listOfGroups.add(signal.getGroups().get(0));
		List<List<String>> listOfPropertyLists = new ArrayList<List<String>>();
		for (RelationDataContainerInterface container : listOfContainers) {
			NewDescriptorBuilderInterface newDescBuilder = 
					new NewDescriptorBuilderV1(signal, container, listOfGroups);
			AbstractDescriptorInterface descriptor = newDescBuilder.getNewDescriptor();
			List<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
			listOfPropertyLists.add(listOfProperties);
			/* for (String property : listOfProperties)
				System.out.println(property);
			System.out.println(""); */
		}
		int nbOfDescriptorsExpected = 0;
		if (DescGenSettings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS)
			nbOfDescriptorsExpected++;
		if (DescGenSettings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER) {
			for (int i=DescGenSettings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; 
					i<=DescGenSettings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; i++) {
				nbOfDescriptorsExpected++;
			}
		}
		assertTrue(nbOfDescriptorsExpected == listOfPropertyLists.size());
	}

}
