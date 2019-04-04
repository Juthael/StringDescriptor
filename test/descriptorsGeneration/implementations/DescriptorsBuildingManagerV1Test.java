package descriptorsGeneration.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import copycatModel.grammar.CharString;
import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.ComponentGrouperV2;
import syntacticTreesGeneration.implementations.DescriptorSpanGetterV1;
import syntacticTreesGeneration.implementations.DescriptorsBuildingManagerV1;
import syntacticTreesGeneration.implementations.NewGenOfDescriptorsBuilderV1;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.interfaces.ComponentGrouperInterface;
import syntacticTreesGeneration.interfaces.DescriptorsBuildingManagerInterface;
import syntacticTreesGeneration.interfaces.NewGenOfDescriptorsBuilderInterface;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;

class DescriptorsBuildingManagerV1Test {

	@Test
	void whenComponentListContainsSingleGen1DescriptorThenExpectedNumberOfGen2Size1DescriptorsIsBuilt() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		boolean expectedNumberOfGen2Size1DescriptorsIsBuilt;
		SignalBuilderInterface signalbuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		SignalInterface signal = signalbuilder.getSignal();
		Group gen1DescriptorA = signal.getGroups().get(0);
		ArrayList<Group> listOfComponents = new ArrayList<Group>();
		listOfComponents.add(gen1DescriptorA);
		DescriptorsBuildingManagerInterface descriptorsBuildingManager = 
				new DescriptorsBuildingManagerV1(signal, 1, listOfComponents);
		ArrayList<AbstractDescriptorInterface> listOfNewDescriptors = descriptorsBuildingManager.getListOfNewDescriptors();
		/* for (AbstractDescriptorInterface newDescriptor : listOfNewDescriptors) {
			ArrayList<String> propertiesWithPath = newDescriptor.getListOfPropertiesWithPath();
			for (String property : propertiesWithPath)
				System.out.println(property);
			System.out.println("");
		} */
		int listSize = listOfNewDescriptors.size();
		int expectedSize = 0;
		if (DescGenSettings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS)
			expectedSize = 1;
		if (DescGenSettings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER) {
			for (int i = DescGenSettings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT ; i <= DescGenSettings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT ; i++) {
				expectedSize++;
			}
		}
		expectedNumberOfGen2Size1DescriptorsIsBuilt = (listSize == expectedSize);
		assertTrue(expectedNumberOfGen2Size1DescriptorsIsBuilt);
	}
	
	@Test
	void whenGen1ComponentsThenNewDescriptorsAreBuilt() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		boolean newDescriptorsAreBuilt = false;
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abc", "fromLeftToRight");
		ArrayList<Group> descriptorsAB = new ArrayList<Group>();
		SignalInterface signalABC = signalBuilder.getSignal();
		descriptorsAB.add(signalABC.getGroups().get(0));
		descriptorsAB.add(signalABC.getGroups().get(1));
		DescriptorsBuildingManagerInterface descriptorsBuildingManager = new DescriptorsBuildingManagerV1(signalABC, 1, descriptorsAB);
		ArrayList<AbstractDescriptorInterface> listOfNewDescriptors = descriptorsBuildingManager.getListOfNewDescriptors();
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
	void whenSetsOfGen1ComponentsThenNewDescriptorsAreBuiltForEachSet() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		boolean descriptorsAreBuiltForEachSet = true;
		ArrayList<AbstractDescriptorInterface> newDescriptors = new ArrayList<AbstractDescriptorInterface>();
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abcd", "fromLeftToRight");
		SignalInterface signalABC = signalBuilder.getSignal();
		ComponentGrouperInterface componentGrouper = new ComponentGrouperV2(1, false, signalABC, signalABC.getGroups());
		HashSet<ArrayList<Group>> setsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		for (ArrayList<Group> setToBeFactorized : setsToBeFactorized) {
			DescriptorsBuildingManagerInterface buildingManager = new DescriptorsBuildingManagerV1(signalABC, 1, setToBeFactorized);
			ArrayList<AbstractDescriptorInterface> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
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
	void whenSetsOfGen2ComponentsThenNewDescriptorsAreBuilt() throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		boolean newDescriptorsAreBuilt = true;
		ArrayList<AbstractDescriptorInterface> newDescriptors = new ArrayList<AbstractDescriptorInterface>();
		ArrayList<Group> factorizableNewDescriptors = new ArrayList<Group>();
		ArrayList<AbstractDescriptorInterface> newGen3Descriptors = new ArrayList<AbstractDescriptorInterface>();
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abcd", "fromLeftToRight");
		SignalInterface signalABCD = signalBuilder.getSignal();
		ComponentGrouperInterface componentGrouper = new ComponentGrouperV2(1, false, signalABCD, signalABCD.getGroups());
		HashSet<ArrayList<Group>> setsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		for (ArrayList<Group> setToBeFactorized : setsToBeFactorized) {
			DescriptorsBuildingManagerInterface buildingManager = new DescriptorsBuildingManagerV1(signalABCD, 1, setToBeFactorized);
			ArrayList<AbstractDescriptorInterface> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
			newDescriptors.addAll(newDescriptorsForThisSet);
		}
		// System.out.println("*********GEN2********");
		for (AbstractDescriptorInterface newDescriptor : newDescriptors) {
			/*for (String property : newDescriptor.getListOfPropertiesWithPath()) {
				System.out.println(property);
			}
			System.out.println("");*/				
			boolean thisDescriptorDoesntCoverTheWholeString = 
					(DescriptorSpanGetterV1.getDescriptorSpan(newDescriptor).size() != signalABCD.getSignalSize());
			if (thisDescriptorDoesntCoverTheWholeString == true) {
				factorizableNewDescriptors.add((Group) newDescriptor);
			}
		}
		componentGrouper = new ComponentGrouperV2(2, true, signalABCD, factorizableNewDescriptors);
		HashSet<ArrayList<Group>> newSetsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		// System.out.println("*********GEN3********");
		for (ArrayList<Group> setToBeFactorized : newSetsToBeFactorized) {
			DescriptorsBuildingManagerInterface buildingManager = 
					new DescriptorsBuildingManagerV1(signalABCD, 2, setToBeFactorized);
			ArrayList<AbstractDescriptorInterface> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
			newGen3Descriptors.addAll(newDescriptorsForThisSet);
		}	
		/* for (AbstractDescriptorInterface descriptor : newGen3Descriptors) {
			ArrayList<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
			for (String property : listOfProperties) {
				System.out.println(property);
			}
			System.out.println("");
		} */	
		newDescriptorsAreBuilt = (!newGen3Descriptors.isEmpty());
		assertTrue(newDescriptorsAreBuilt);
	}
	
	@Test
	void whenStringIsSize1ThenDescriptorsAreBuilt() throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("a", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		DescriptorsBuildingManagerInterface descriptorsBM = new DescriptorsBuildingManagerV1(signal, 1, signal.getGroups());
		ArrayList<AbstractDescriptorInterface> descriptors = descriptorsBM.getListOfNewDescriptors();
		/* for (AbstractDescriptorInterface descriptor : descriptors) {
			for (String property : descriptor.getListOfPropertiesWithPath()) {
				System.out.println(property);
			}
			System.out.println("");
		} */
		assertTrue(!descriptors.isEmpty());
	}
	
	@Test
	void whenParameterIsSetOfGen2DescriptorsThenNoExceptionIsThrown() 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException{
		ArrayList<CharString> listOfDescriptorsCoveringTheWholeString = new ArrayList<CharString>();
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abcde", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		ArrayList<Group> previousGenOfFactorizableDescriptors = signal.getGroups();
		try {
			// System.out.println("GEN1 : ");
			// printSetOfGroups(previousGenOfFactorizableDescriptors);
			NewGenOfDescriptorsBuilderInterface newGenOfDescriptorsBuilder = 
						new NewGenOfDescriptorsBuilderV1(1, signal, previousGenOfFactorizableDescriptors);
			previousGenOfFactorizableDescriptors = new ArrayList<Group>();
			ArrayList<AbstractDescriptorInterface> newGenOfDescriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			for (AbstractDescriptorInterface descriptor : newGenOfDescriptors) {
				if (descriptor.getDescriptorName().equals("charString"))
					listOfDescriptorsCoveringTheWholeString.add((CharString) descriptor);
				else if (descriptor.getDescriptorName().equals("group"))
					previousGenOfFactorizableDescriptors.add((Group) descriptor);
				else throw new DescriptorsBuilderCriticalException("RelationalDescriptorsBuilder : "
						+ "unexpected type of descriptor was found.");
			}
			// System.out.println("GEN2 : ");
			// printSetOfGroups(previousGenOfFactorizableDescriptors);
			// printSetOfCharString(listOfDescriptorsCoveringTheWholeString);
			newGenOfDescriptorsBuilder = 
					new NewGenOfDescriptorsBuilderV1(2, signal, previousGenOfFactorizableDescriptors);
			previousGenOfFactorizableDescriptors = new ArrayList<Group>();
			newGenOfDescriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			for (AbstractDescriptorInterface descriptor : newGenOfDescriptors) {
				if (descriptor.getDescriptorName().equals("charString"))
					listOfDescriptorsCoveringTheWholeString.add((CharString) descriptor);
				else if (descriptor.getDescriptorName().equals("group"))
					previousGenOfFactorizableDescriptors.add((Group) descriptor);
				else throw new DescriptorsBuilderCriticalException("RelationalDescriptorsBuilder : "
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
