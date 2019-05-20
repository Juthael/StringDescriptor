package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISynTreeElement;
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
		Frame gen1DescriptorA = signal.getFrames().get(0);
		ArrayList<Frame> listOfComponents = new ArrayList<Frame>();
		listOfComponents.add(gen1DescriptorA);
		IDescriptorsBuildingManager descriptorsBuildingManager = 
				new DescriptorsBuildingManagerImpl(signal, 1, listOfComponents);
		List<ISynTreeElement> listOfNewDescriptors = descriptorsBuildingManager.getListOfNewDescriptors();
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
		List<Frame> descriptorsAB = new ArrayList<Frame>();
		ISignal signalABC = signalBuilder.getSignal();
		descriptorsAB.add(signalABC.getFrames().get(0));
		descriptorsAB.add(signalABC.getFrames().get(1));
		IDescriptorsBuildingManager descriptorsBuildingManager = new DescriptorsBuildingManagerImpl(signalABC, 1, descriptorsAB);
		List<ISynTreeElement> listOfNewDescriptors = descriptorsBuildingManager.getListOfNewDescriptors();
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
		List<ISynTreeElement> newDescriptors = new ArrayList<ISynTreeElement>();
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signalABC = signalBuilder.getSignal();
		IComponentGrouper componentGrouper = new ComponentGrouperImpl(1, false, signalABC, signalABC.getFrames());
		Set<List<Frame>> setsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setToBeFactorized : setsToBeFactorized) {
			IDescriptorsBuildingManager buildingManager = new DescriptorsBuildingManagerImpl(signalABC, 1, setToBeFactorized);
			List<ISynTreeElement> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
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
		List<ISynTreeElement> newDescriptors = new ArrayList<ISynTreeElement>();
		List<Frame> factorizableNewDescriptors = new ArrayList<Frame>();
		List<ISynTreeElement> newGen3Descriptors = new ArrayList<ISynTreeElement>();
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcd", "fromLeftToRight");
		ISignal signalABCD = signalBuilder.getSignal();
		IComponentGrouper componentGrouper = new ComponentGrouperImpl(1, false, signalABCD, signalABCD.getFrames());
		Set<List<Frame>> setsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setToBeFactorized : setsToBeFactorized) {
			IDescriptorsBuildingManager buildingManager = new DescriptorsBuildingManagerImpl(signalABCD, 1, setToBeFactorized);
			List<ISynTreeElement> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
			newDescriptors.addAll(newDescriptorsForThisSet);
		}
		/*System.out.println("*********GEN2********"); */
		for (ISynTreeElement newDescriptor : newDescriptors) {
			/* for (String property : newDescriptor.getListOfPropertiesWithPath()) {
				System.out.println(property);
			}
			System.out.println(""); */		
			boolean thisDescriptorDoesntCoverTheWholeString = 
					(DescriptorSpanGetterImpl.getDescriptorSpan(newDescriptor).size() != signalABCD.getSignalSize());
			if (thisDescriptorDoesntCoverTheWholeString == true) {
				factorizableNewDescriptors.add((Frame) newDescriptor);
			}
		}
		componentGrouper = new ComponentGrouperImpl(2, true, signalABCD, factorizableNewDescriptors);
		Set<List<Frame>> newSetsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		/* System.out.println("*********GEN3********"); */
		for (List<Frame> setToBeFactorized : newSetsToBeFactorized) {
			IDescriptorsBuildingManager buildingManager = 
					new DescriptorsBuildingManagerImpl(signalABCD, 2, setToBeFactorized);
			List<ISynTreeElement> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
			newGen3Descriptors.addAll(newDescriptorsForThisSet);
		}	
		for (ISynTreeElement descriptor : newGen3Descriptors) {
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
		IDescriptorsBuildingManager descriptorsBM = new DescriptorsBuildingManagerImpl(signal, 1, signal.getFrames());
		List<ISynTreeElement> descriptors = descriptorsBM.getListOfNewDescriptors();
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
		List<Frame> previousGenOfFactorizableDescriptors = signal.getFrames();
		try {
			// System.out.println("GEN1 : ");
			// printSetOfFrames(previousGenOfFactorizableDescriptors);
			INewGenOfDescriptorsBuilder newGenOfDescriptorsBuilder = 
						new NewGenOfDescriptorsBuilderImpl(1, signal, previousGenOfFactorizableDescriptors);
			previousGenOfFactorizableDescriptors = new ArrayList<Frame>();
			List<ISynTreeElement> newGenOfDescriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			for (ISynTreeElement descriptor : newGenOfDescriptors) {
				if (descriptor.getDescriptorName().equals("charString"))
					listOfDescriptorsCoveringTheWholeString.add((CharString) descriptor);
				else if (descriptor.getDescriptorName().equals("frame"))
					previousGenOfFactorizableDescriptors.add((Frame) descriptor);
				else throw new SynTreeGenerationException("RelationalDescriptorsBuilder : "
						+ "unexpected type of descriptor was found.");
			}
			// System.out.println("GEN2 : ");
			// printSetOfFrames(previousGenOfFactorizableDescriptors);
			// printSetOfCharString(listOfDescriptorsCoveringTheWholeString);
			newGenOfDescriptorsBuilder = 
					new NewGenOfDescriptorsBuilderImpl(2, signal, previousGenOfFactorizableDescriptors);
			previousGenOfFactorizableDescriptors = new ArrayList<Frame>();
			newGenOfDescriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			for (ISynTreeElement descriptor : newGenOfDescriptors) {
				if (descriptor.getDescriptorName().equals("charString"))
					listOfDescriptorsCoveringTheWholeString.add((CharString) descriptor);
				else if (descriptor.getDescriptorName().equals("frame"))
					previousGenOfFactorizableDescriptors.add((Frame) descriptor);
				else throw new SynTreeGenerationException("RelationalDescriptorsBuilder : "
						+ "unexpected type of descriptor was found.");
			}
			// System.out.println("GEN3 : ");
			// printSetOfFrames(previousGenOfFactorizableDescriptors);
			// printSetOfCharString(listOfDescriptorsCoveringTheWholeString);
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			fail();
		}
		
	}
	
	private void printSetOfFrames(ArrayList<Frame> listOfDescriptors) {
		for (Frame frame : listOfDescriptors) {
			List<String> properties = frame.getListOfPropertiesWithPath();
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
