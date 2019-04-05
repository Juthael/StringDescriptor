package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import copycatModel.ISynTreeIntegrableElement;
import copycatModel.ISignal;
import copycatModel.grammar.Group;
import exceptions.DescriptorsBuilderException;
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
	List<Group> descriptorsSignalABC;	
	
	@Before
	public void initialize() throws DescriptorsBuilderException {
		signalBuilderABC = new SignalBuilderImpl("abc", "fromLeftToRight");
		signalABC = signalBuilderABC.getSignal();
		descriptorsSignalABC = signalABC.getGroups();
	}
	
	@Test
	public void when1stGenDescriptorCover1stLetterThenRDContainerSizeIsAccordedToSettings() 
			throws DescriptorsBuilderException {
		int nbOfRDContainers = 0;
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, signalABC.getGroups().get(0));
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
			throws DescriptorsBuilderException {
		int nbOfRDContainers = 0;
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, signalABC.getGroups().get(2));
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
			throws DescriptorsBuilderException {
		int nbOfRDContainers = 0;
		ISignalBuilder signalBuilderABCD = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signalABCD = signalBuilderABCD.getSignal();
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilder = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABCD, signalABCD.getGroups().get(2));
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
	public void Gen2Size1RDContainerAllowsGroupBuildingWithoutThrowingException() 
			throws DescriptorsBuilderException, CloneNotSupportedException {
		List<ISynTreeIntegrableElement> newGroups = new ArrayList<ISynTreeIntegrableElement>();
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderA = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, signalABC.getGroups().get(0));
		List<IRelationDataContainer> listOfRDContainerA = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderB = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, signalABC.getGroups().get(1));
		List<IRelationDataContainer> listOfRDContainerB = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();
		IGen2Size1RelationDataContainerBuilder gen2Size1RDContainerBuilderC = 
				new Gen2Size1RelationDataContainerBuilderImpl(signalABC, signalABC.getGroups().get(2));
		List<IRelationDataContainer> listOfRDContainerC = 
				gen2Size1RDContainerBuilderA.getListOfRelationDataContainers();		
		List<Group> listWithGroupA = new ArrayList<Group>();
		List<Group> listWithGroupB = new ArrayList<Group>();
		List<Group> listWithGroupC = new ArrayList<Group>();
		listWithGroupA.add(signalABC.getGroups().get(0));
		listWithGroupB.add(signalABC.getGroups().get(1));
		listWithGroupC.add(signalABC.getGroups().get(2));
		try {
			for (IRelationDataContainer RDContainerA : listOfRDContainerA) {
				INewDescriptorBuilder newDescriptorBuilder = new NewDescriptorBuilderImpl(signalABC, RDContainerA, listWithGroupA);
				newGroups.add(newDescriptorBuilder.getNewDescriptor());
			}
			for (IRelationDataContainer RDContainerB : listOfRDContainerB) {
				INewDescriptorBuilder newDescriptorBuilder = new NewDescriptorBuilderImpl(signalABC, RDContainerB, listWithGroupB);
				newGroups.add(newDescriptorBuilder.getNewDescriptor());
			}
			for (IRelationDataContainer RDContainerC : listOfRDContainerC) {
				INewDescriptorBuilder newDescriptorBuilder = new NewDescriptorBuilderImpl(signalABC, RDContainerC, listWithGroupC);
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
