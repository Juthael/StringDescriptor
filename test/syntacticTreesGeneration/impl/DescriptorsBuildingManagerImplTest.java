package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import copycatModel.ISynTreeIntegrableElement;
import copycatModel.ISignal;
import copycatModel.grammar.CharString;
import copycatModel.grammar.Group;
import exceptions.SynTreeGenerationException;
import settings.Settings;
import syntacticTreesGeneration.IComponentGrouper;
import syntacticTreesGeneration.IDescriptorsBuildingManager;
import syntacticTreesGeneration.INewGenOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ComponentGrouperImpl;
import syntacticTreesGeneration.impl.DescriptorSpanGetterImpl;
import syntacticTreesGeneration.impl.DescriptorsBuildingManagerImpl;
import syntacticTreesGeneration.impl.NewGenOfDescriptorsBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class DescriptorsBuildingManagerImplTest {

	@Test
	public void whenComponentListContainsSingleGen1DescriptorThenExpectedNumberOfGen2Size1DescriptorsIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean expectedNumberOfGen2Size1DescriptorsIsBuilt;
		ISignalBuilder signalbuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		ISignal signal = signalbuilder.getSignal();
		Group gen1DescriptorA = signal.getGroups().get(0);
		ArrayList<Group> listOfComponents = new ArrayList<Group>();
		listOfComponents.add(gen1DescriptorA);
		IDescriptorsBuildingManager descriptorsBuildingManager = 
				new DescriptorsBuildingManagerImpl(signal, 1, listOfComponents);
		List<ISynTreeIntegrableElement> listOfNewDescriptors = descriptorsBuildingManager.getListOfNewDescriptors();
		/* for (AbstractDescriptorInterface newDescriptor : listOfNewDescriptors) {
			ArrayList<String> propertiesWithPath = newDescriptor.getListOfPropertiesWithPath();
			for (String property : propertiesWithPath)
				System.out.println(property);
			System.out.println("");
		} */
		int listSize = listOfNewDescriptors.size();
		int expectedSize = 0;
		if (Settings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS)
			expectedSize = 1;
		if (Settings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER) {
			for (int i = Settings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; i <= Settings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; i++) {
				expectedSize++;
			}
		}
		expectedNumberOfGen2Size1DescriptorsIsBuilt = (listSize == expectedSize);
		assertTrue(expectedNumberOfGen2Size1DescriptorsIsBuilt);
	}
	
	@Test
	public void whenGen1ComponentsThenNewDescriptorsAreBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean newDescriptorsAreBuilt = false;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abc", "fromLeftToRight");
		List<Group> descriptorsAB = new ArrayList<Group>();
		ISignal signalABC = signalBuilder.getSignal();
		descriptorsAB.add(signalABC.getGroups().get(0));
		descriptorsAB.add(signalABC.getGroups().get(1));
		IDescriptorsBuildingManager descriptorsBuildingManager = new DescriptorsBuildingManagerImpl(signalABC, 1, descriptorsAB);
		List<ISynTreeIntegrableElement> listOfNewDescriptors = descriptorsBuildingManager.getListOfNewDescriptors();
		/* for (AbstractDescriptorInterface newDescriptor : listOfNewDescriptors) {
			ArrayList<String> propertiesWithPath = newDescriptor.getListOfPropertiesWithPath();
			for (String property : propertiesWithPath)
				System.out.println(property);
			System.out.println("");
		} */
		newDescriptorsAreBuilt = (!listOfNewDescriptors.isEmpty());
		assertTrue(newDescriptorsAreBuilt);
	}
	
	@Test
	public void whenSetsOfGen1ComponentsThenNewDescriptorsAreBuiltForEachSet() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean descriptorsAreBuiltForEachSet = true;
		List<ISynTreeIntegrableElement> newDescriptors = new ArrayList<ISynTreeIntegrableElement>();
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signalABC = signalBuilder.getSignal();
		IComponentGrouper componentGrouper = new ComponentGrouperImpl(1, false, signalABC, signalABC.getGroups());
		Set<List<Group>> setsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Group> setToBeFactorized : setsToBeFactorized) {
			IDescriptorsBuildingManager buildingManager = new DescriptorsBuildingManagerImpl(signalABC, 1, setToBeFactorized);
			List<ISynTreeIntegrableElement> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
			if (newDescriptorsForThisSet.isEmpty())
				descriptorsAreBuiltForEachSet = false;
			newDescriptors.addAll(newDescriptorsForThisSet);
		}
		/* for (AbstractDescriptorInterface descriptor : newDescriptors) {
			ArrayList<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
			for (String property : listOfProperties) {
				System.out.println(property);
			}
			System.out.println("");
		} */			
		assertTrue(descriptorsAreBuiltForEachSet);
	}	
	
	@Test
	public void whenSetsOfGen2ComponentsThenNewDescriptorsAreBuilt() throws SynTreeGenerationException, CloneNotSupportedException {
		boolean newDescriptorsAreBuilt = true;
		List<ISynTreeIntegrableElement> newDescriptors = new ArrayList<ISynTreeIntegrableElement>();
		List<Group> factorizableNewDescriptors = new ArrayList<Group>();
		List<ISynTreeIntegrableElement> newGen3Descriptors = new ArrayList<ISynTreeIntegrableElement>();
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signalABCD = signalBuilder.getSignal();
		IComponentGrouper componentGrouper = new ComponentGrouperImpl(1, false, signalABCD, signalABCD.getGroups());
		Set<List<Group>> setsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Group> setToBeFactorized : setsToBeFactorized) {
			IDescriptorsBuildingManager buildingManager = new DescriptorsBuildingManagerImpl(signalABCD, 1, setToBeFactorized);
			List<ISynTreeIntegrableElement> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
			newDescriptors.addAll(newDescriptorsForThisSet);
		}
		/*System.out.println("*********GEN2********"); */
		for (ISynTreeIntegrableElement newDescriptor : newDescriptors) {
			/* for (String property : newDescriptor.getListOfPropertiesWithPath()) {
				System.out.println(property);
			}
			System.out.println(""); */		
			boolean thisDescriptorDoesntCoverTheWholeString = 
					(DescriptorSpanGetterImpl.getDescriptorSpan(newDescriptor).size() != signalABCD.getSignalSize());
			if (thisDescriptorDoesntCoverTheWholeString == true) {
				factorizableNewDescriptors.add((Group) newDescriptor);
			}
		}
		componentGrouper = new ComponentGrouperImpl(2, true, signalABCD, factorizableNewDescriptors);
		Set<List<Group>> newSetsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		/* System.out.println("*********GEN3********"); */
		for (List<Group> setToBeFactorized : newSetsToBeFactorized) {
			IDescriptorsBuildingManager buildingManager = 
					new DescriptorsBuildingManagerImpl(signalABCD, 2, setToBeFactorized);
			List<ISynTreeIntegrableElement> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
			newGen3Descriptors.addAll(newDescriptorsForThisSet);
		}	
		for (ISynTreeIntegrableElement descriptor : newGen3Descriptors) {
			List<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
			/* for (String property : listOfProperties) {
				System.out.println(property);
			}
			System.out.println(""); */
		} 	
		newDescriptorsAreBuilt = (!newGen3Descriptors.isEmpty());
		assertTrue(newDescriptorsAreBuilt);
	}
	
	@Test
	public void whenStringIsSize1ThenDescriptorsAreBuilt() throws SynTreeGenerationException, CloneNotSupportedException {
		ISignalBuilder signalBuilder = new SignalBuilderImpl("a", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		IDescriptorsBuildingManager descriptorsBM = new DescriptorsBuildingManagerImpl(signal, 1, signal.getGroups());
		List<ISynTreeIntegrableElement> descriptors = descriptorsBM.getListOfNewDescriptors();
		/* for (AbstractDescriptorInterface descriptor : descriptors) {
			for (String property : descriptor.getListOfPropertiesWithPath()) {
				System.out.println(property);
			}
			System.out.println("");
		} */
		assertTrue(!descriptors.isEmpty());
	}
	
	@Test
	public void whenParameterIsSetOfGen2DescriptorsThenNoExceptionIsThrown() 
			throws SynTreeGenerationException, CloneNotSupportedException{
		List<CharString> listOfDescriptorsCoveringTheWholeString = new ArrayList<CharString>();
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcde", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Group> previousGenOfFactorizableDescriptors = signal.getGroups();
		try {
			// System.out.println("GEN1 : ");
			// printSetOfGroups(previousGenOfFactorizableDescriptors);
			INewGenOfDescriptorsBuilder newGenOfDescriptorsBuilder = 
						new NewGenOfDescriptorsBuilderImpl(1, signal, previousGenOfFactorizableDescriptors);
			previousGenOfFactorizableDescriptors = new ArrayList<Group>();
			List<ISynTreeIntegrableElement> newGenOfDescriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			for (ISynTreeIntegrableElement descriptor : newGenOfDescriptors) {
				if (descriptor.getDescriptorName().equals("charString"))
					listOfDescriptorsCoveringTheWholeString.add((CharString) descriptor);
				else if (descriptor.getDescriptorName().equals("group"))
					previousGenOfFactorizableDescriptors.add((Group) descriptor);
				else throw new SynTreeGenerationException("RelationalDescriptorsBuilder : "
						+ "unexpected type of descriptor was found.");
			}
			// System.out.println("GEN2 : ");
			// printSetOfGroups(previousGenOfFactorizableDescriptors);
			// printSetOfCharString(listOfDescriptorsCoveringTheWholeString);
			newGenOfDescriptorsBuilder = 
					new NewGenOfDescriptorsBuilderImpl(2, signal, previousGenOfFactorizableDescriptors);
			previousGenOfFactorizableDescriptors = new ArrayList<Group>();
			newGenOfDescriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			for (ISynTreeIntegrableElement descriptor : newGenOfDescriptors) {
				if (descriptor.getDescriptorName().equals("charString"))
					listOfDescriptorsCoveringTheWholeString.add((CharString) descriptor);
				else if (descriptor.getDescriptorName().equals("group"))
					previousGenOfFactorizableDescriptors.add((Group) descriptor);
				else throw new SynTreeGenerationException("RelationalDescriptorsBuilder : "
						+ "unexpected type of descriptor was found.");
			}
			// System.out.println("GEN3 : ");
			// printSetOfGroups(previousGenOfFactorizableDescriptors);
			// printSetOfCharString(listOfDescriptorsCoveringTheWholeString);
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			fail();
		}
		
	}
	
	private void printSetOfGroups(ArrayList<Group> listOfDescriptors) {
		for (Group group : listOfDescriptors) {
			List<String> properties = group.getListOfPropertiesWithPath();
			for (String property : properties) {
				System.out.println(property);
			}
			System.out.println("");
		}
	}
	
	private void printSetOfCharString(ArrayList<CharString> listOfDescriptors) {
		for (CharString charString : listOfDescriptors) {
			List<String> properties = charString.getListOfPropertiesWithPath();
			for (String property : properties) {
				System.out.println(property);
			}
			System.out.println("");
		}
	}	

}
