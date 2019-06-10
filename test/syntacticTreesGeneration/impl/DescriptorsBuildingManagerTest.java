package syntacticTreesGeneration.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.CharString;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;
import model.synTreeModel.IFrame;
import model.synTreeModel.IGrammaticalST;
import settings.Settings;
import syntacticTreesGeneration.IComponentGrouper;
import syntacticTreesGeneration.IDescriptorsBuildingManager;
import syntacticTreesGeneration.INewGenOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;

public class DescriptorsBuildingManagerTest {

	@Test
	public void whenComponentListContainsSingleGen1DescriptorThenExpectedNumberOfGen2Size1DescriptorsIsBuilt() 
			throws SynTreeGenerationException, CloneNotSupportedException {
		boolean expectedNumberOfGen2Size1DescriptorsIsBuilt;
		ISignalBuilder signalbuilder = new SignalBuilder("abc", "fromLeftToRight");
		ISignal signal = signalbuilder.getSignal();
		Frame gen1DescriptorA = (Frame) signal.getFrames().get(0);
		ArrayList<Frame> listOfComponents = new ArrayList<Frame>();
		listOfComponents.add(gen1DescriptorA);
		IDescriptorsBuildingManager descriptorsBuildingManager = 
				new DescriptorsBuildingManager((ICopycatSignal) signal, 1, listOfComponents);
		List<IGrammaticalST> listOfNewDescriptors = descriptorsBuildingManager.getListOfNewDescriptors();
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
		ISignalBuilder signalBuilder = new SignalBuilder("abc", "fromLeftToRight");
		List<Frame> descriptorsAB = new ArrayList<Frame>();
		ISignal signalABC = signalBuilder.getSignal();
		descriptorsAB.add((Frame) signalABC.getFrames().get(0));
		descriptorsAB.add((Frame) signalABC.getFrames().get(1));
		IDescriptorsBuildingManager descriptorsBuildingManager = new DescriptorsBuildingManager((ICopycatSignal) signalABC, 1, descriptorsAB);
		List<IGrammaticalST> listOfNewDescriptors = descriptorsBuildingManager.getListOfNewDescriptors();
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
		List<IGrammaticalST> newDescriptors = new ArrayList<IGrammaticalST>();
		ISignalBuilder signalBuilder = new SignalBuilder("abcd", "fromLeftToRight");
		ISignal signalABC = signalBuilder.getSignal();
		List<Frame> listOfFramesABC = new ArrayList<Frame>();
		for (IFrame frame : signalABC.getFrames()) {
			listOfFramesABC.add((Frame) frame);
		}
		IComponentGrouper componentGrouper = new ComponentGrouper(1, false, signalABC, listOfFramesABC);
		Set<List<Frame>> setsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setToBeFactorized : setsToBeFactorized) {
			IDescriptorsBuildingManager buildingManager = new DescriptorsBuildingManager((ICopycatSignal) signalABC, 1, setToBeFactorized);
			List<IGrammaticalST> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
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
		List<IGrammaticalST> newDescriptors = new ArrayList<IGrammaticalST>();
		List<Frame> factorizableNewDescriptors = new ArrayList<Frame>();
		List<IGrammaticalST> newGen3Descriptors = new ArrayList<IGrammaticalST>();
		ISignalBuilder signalBuilder = new SignalBuilder("abcd", "fromLeftToRight");
		ISignal signalABCD = signalBuilder.getSignal();
		List<Frame> listOfFramesABCD = new ArrayList<Frame>();
		for (IFrame frame : signalABCD.getFrames()) {
			listOfFramesABCD.add((Frame) frame);
		}		
		IComponentGrouper componentGrouper = new ComponentGrouper(1, false, signalABCD, listOfFramesABCD);
		Set<List<Frame>> setsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		for (List<Frame> setToBeFactorized : setsToBeFactorized) {
			IDescriptorsBuildingManager buildingManager = new DescriptorsBuildingManager((ICopycatSignal) signalABCD, 1, setToBeFactorized);
			List<IGrammaticalST> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
			newDescriptors.addAll(newDescriptorsForThisSet);
		}
		
		System.out.println("*********GEN2********");
		
		for (IGrammaticalST newDescriptor : newDescriptors) {
			
			for (String property : newDescriptor.getListOfPropertiesWithPath()) {
				System.out.println(property);
			}
			System.out.println("");
			
			boolean thisDescriptorDoesntCoverTheWholeString = 
					(DescriptorSpanGetter.getDescriptorSpan(newDescriptor).size() != signalABCD.getSignalSize());
			if (thisDescriptorDoesntCoverTheWholeString == true) {
				factorizableNewDescriptors.add((Frame) newDescriptor);
			}
		}
		componentGrouper = new ComponentGrouper(2, true, signalABCD, factorizableNewDescriptors);
		Set<List<Frame>> newSetsToBeFactorized = componentGrouper.getSetsOfFactorizableDescriptors();
		
		 System.out.println("*********GEN3********");
		
		for (List<Frame> setToBeFactorized : newSetsToBeFactorized) {
			
			int index = 1;
			for (Frame frame : setToBeFactorized) {
				List<String> listOfProperties = frame.getListOfPropertiesWithPath();
				System.out.println("frame n." + index);
				for (String property : listOfProperties) {
					System.out.println(property);
				}
				System.out.println("");
				index++;
			}
			System.out.println("");
			
			IDescriptorsBuildingManager buildingManager = 
					new DescriptorsBuildingManager((ICopycatSignal) signalABCD, 2, setToBeFactorized);
			List<IGrammaticalST> newDescriptorsForThisSet = buildingManager.getListOfNewDescriptors();
			newGen3Descriptors.addAll(newDescriptorsForThisSet);
			
			/*
			for (IGrammaticalST descriptor : newDescriptorsForThisSet) {
				List<String> listOfProperties = descriptor.getListOfPropertiesWithPath();		
				for (String property : listOfProperties) {
					System.out.println(property);
				}
				System.out.println("");	
			}
			System.out.println("");
			*/
				
		}
		
		/*
		for (IGrammaticalST descriptor : newGen3Descriptors) {
			List<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
			
			for (String property : listOfProperties) {
				System.out.println(property);
			}
			System.out.println("");	
		} 
		*/	
		
		newDescriptorsAreBuilt = (!newGen3Descriptors.isEmpty());
		assertTrue(newDescriptorsAreBuilt);
	}
	
	@Test
	public void whenStringIsSize1ThenDescriptorsAreBuilt() throws SynTreeGenerationException, CloneNotSupportedException {
		ISignalBuilder signalBuilder = new SignalBuilder("a", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Frame> listOfFramesA = new ArrayList<Frame>();
		for (IFrame frame : signal.getFrames()) {
			listOfFramesA.add((Frame) frame);
		}		
		IDescriptorsBuildingManager descriptorsBM = new DescriptorsBuildingManager((ICopycatSignal) signal, 1, listOfFramesA);
		List<IGrammaticalST> descriptors = descriptorsBM.getListOfNewDescriptors();
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
		ISignalBuilder signalBuilder = new SignalBuilder("abcde", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Frame> previousGenOfFactorizableDescriptors = new ArrayList<Frame>();
		for (IFrame iFrame : signal.getFrames())
			previousGenOfFactorizableDescriptors.add((Frame) iFrame);
		try {
			// System.out.println("GEN1 : ");
			// printSetOfFrames(previousGenOfFactorizableDescriptors);
			INewGenOfDescriptorsBuilder newGenOfDescriptorsBuilder = 
						new NewGenOfDescriptorsBuilder(1,(ICopycatSignal) signal, previousGenOfFactorizableDescriptors);
			previousGenOfFactorizableDescriptors = new ArrayList<Frame>();
			List<IGrammaticalST> newGenOfDescriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			for (IGrammaticalST descriptor : newGenOfDescriptors) {
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
					new NewGenOfDescriptorsBuilder(2, (ICopycatSignal) signal, previousGenOfFactorizableDescriptors);
			previousGenOfFactorizableDescriptors = new ArrayList<Frame>();
			newGenOfDescriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
			for (IGrammaticalST descriptor : newGenOfDescriptors) {
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
