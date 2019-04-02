package descriptorsGeneration.implementations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.Gen2Size1RelationDataContainerBuilderV1;
import syntacticTreesGeneration.implementations.NewDescriptorBuilderV1;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.interfaces.Gen2Size1RelationDataContainerBuilderInterface;
import syntacticTreesGeneration.interfaces.NewDescriptorBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;

class Gen2Size1RelationDataContainerBuilderV1Test {

	SignalBuilderInterface signalBuilderABC;
	SignalInterface signalABC;
	ArrayList<Group> descriptorsSignalABC;	
	
	@BeforeEach
	void initialize() throws DescriptorsBuilderCriticalException {
		signalBuilderABC = new SignalBuilderV1("abc", "fromLeftToRight");
		signalABC = signalBuilderABC.getSignal();
		descriptorsSignalABC = signalABC.getGroups();
	}
	
	@Test
	void when1stGenDescriptorCover1stLetterThenRDContainerSizeIsAccordedToSettings() 
			throws DescriptorsBuilderCriticalException {
		int nbOfRDContainers = 0;
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderV1(signalABC, signalABC.getGroups().get(0));
		ArrayList<RelationDataContainerInterface> listOfRDContainer = 
				gen2Size1RDContainerBuilder.getListOfRelationDataContainers();
		if (DescGenSettings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS) {
			nbOfRDContainers++;
		}
		if (DescGenSettings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER) {
			for (int inc = DescGenSettings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; inc <= DescGenSettings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; inc++)
				nbOfRDContainers++;
		}
		assertEquals(listOfRDContainer.size(), nbOfRDContainers);
	}
	
	@Test
	void when1stGenDescriptorCoverLastLetterThenRDContainerSizeIsAccordedToSettings() 
			throws DescriptorsBuilderCriticalException {
		int nbOfRDContainers = 0;
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderV1(signalABC, signalABC.getGroups().get(2));
		ArrayList<RelationDataContainerInterface> listOfRDContainer = 
				gen2Size1RDContainerBuilder.getListOfRelationDataContainers();
		if (DescGenSettings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS) {
			nbOfRDContainers++;
		}
		if (DescGenSettings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_LAST_LETTER) {
			for (int inc = DescGenSettings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; inc <= DescGenSettings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; inc++)
				nbOfRDContainers++;
		}
		assertEquals(listOfRDContainer.size(), nbOfRDContainers);		
	}
	
	@Test
	void when1stGenDescriptorDoesntCoverSpecialPositionThenRDContainerSizeIsAccordedToSettings() 
			throws DescriptorsBuilderCriticalException {
		int nbOfRDContainers = 0;
		SignalBuilderInterface signalBuilderABCD = new SignalBuilderV1("abcd", "fromLeftToRight");
		SignalInterface signalABCD = signalBuilderABCD.getSignal();
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderV1(signalABCD, signalABCD.getGroups().get(2));
		ArrayList<RelationDataContainerInterface> listOfRDContainer = 
				gen2Size1RDContainerBuilder.getListOfRelationDataContainers();
		if (DescGenSettings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_ALL_LETTERS) {
			nbOfRDContainers++;
		}
		if (DescGenSettings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_ALL_LETTERS) {
			for (int inc = DescGenSettings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; inc <= DescGenSettings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; inc++)
				nbOfRDContainers++;
		}
		assertEquals(listOfRDContainer.size(), nbOfRDContainers);			
	}
	
	@Test
	void Gen2Size1RDContainerAllowsGroupBuildingWithoutThrowingException() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		ArrayList<AbstractDescriptorInterface> newGroups = new ArrayList<AbstractDescriptorInterface>();
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilderA = 
				new Gen2Size1RelationDataContainerBuilderV1(signalABC, signalABC.getGroups().get(0));
		ArrayList<RelationDataContainerInterface> listOfRDContainerA = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilderB = 
				new Gen2Size1RelationDataContainerBuilderV1(signalABC, signalABC.getGroups().get(1));
		ArrayList<RelationDataContainerInterface> listOfRDContainerB = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		Gen2Size1RelationDataContainerBuilderInterface gen2Size1RDContainerBuilderC = 
				new Gen2Size1RelationDataContainerBuilderV1(signalABC, signalABC.getGroups().get(2));
		ArrayList<RelationDataContainerInterface> listOfRDContainerC = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();		
		ArrayList<Group> listWithGroupA = new ArrayList<Group>();
		ArrayList<Group> listWithGroupB = new ArrayList<Group>();
		ArrayList<Group> listWithGroupC = new ArrayList<Group>();
		listWithGroupA.add(signalABC.getGroups().get(0));
		listWithGroupB.add(signalABC.getGroups().get(1));
		listWithGroupC.add(signalABC.getGroups().get(2));
		try {
			for (RelationDataContainerInterface RDContainerA : listOfRDContainerA) {
				NewDescriptorBuilderInterface newDescriptorBuilder = new NewDescriptorBuilderV1(signalABC, RDContainerA, listWithGroupA);
				newGroups.add(newDescriptorBuilder.getNewDescriptor());
			}
			for (RelationDataContainerInterface RDContainerB : listOfRDContainerB) {
				NewDescriptorBuilderInterface newDescriptorBuilder = new NewDescriptorBuilderV1(signalABC, RDContainerB, listWithGroupB);
				newGroups.add(newDescriptorBuilder.getNewDescriptor());
			}
			for (RelationDataContainerInterface RDContainerC : listOfRDContainerC) {
				NewDescriptorBuilderInterface newDescriptorBuilder = new NewDescriptorBuilderV1(signalABC, RDContainerC, listWithGroupC);
				newGroups.add(newDescriptorBuilder.getNewDescriptor());
			}			
		}
		catch (Exception unexpected) {
			fail();
		}
		/*
		for (AbstractDescriptorInterface descriptor : newGroups) {
			ArrayList<String> properties = descriptor.getListOfPropertiesWithPath();
			for (String property : properties)
				System.out.println(property);
			System.out.println("");			
		}
		*/
	}

}
