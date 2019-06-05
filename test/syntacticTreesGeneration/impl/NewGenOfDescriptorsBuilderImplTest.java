package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.copycatModel.signal.ICopycatSignal;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;
import model.synTreeModel.IFrame;
import model.synTreeModel.IGrammaticalST;
import settings.Settings;
import syntacticTreesGeneration.INewGenOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.NewGenOfDescriptorsBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class NewGenOfDescriptorsBuilderImplTest {

	private List<IGrammaticalST> fullStringDescriptors = new ArrayList<IGrammaticalST>();
	
	@Test
	public void whenStringLengthAndSettingsAllowItThenReturnsNextGenOfDescriptors() 
			throws Exception {
		boolean returnsNextGenOfDescriptors = true;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcdefghij", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		List<Frame> listOfFrames = new ArrayList<Frame>();
		for (IFrame iFrame : signal.getFrames())
			listOfFrames.add((Frame) iFrame);		
		INewGenOfDescriptorsBuilder newGenOfDescriptorsBuilder = 
				new NewGenOfDescriptorsBuilderImpl(1, (ICopycatSignal) signal, listOfFrames);
		List<IGrammaticalST> gen2Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
		if (gen2Descriptors.isEmpty() && Settings.MAX_NB_OF_DESCRIPTOR_GENERATIONS >= 2)
			returnsNextGenOfDescriptors = false;
		List<Frame> factorizableGen2Descriptors = new ArrayList<Frame>();
		sortNewDescriptors(gen2Descriptors, factorizableGen2Descriptors); 
		/*	System.out.println("***** GEN2 *****");
		System.out.println("-> whole String descriptors : ");
		System.out.println("");
		printListOfDescriptors(fullStringDescriptors); */
		/* System.out.println("-> factorizable descriptors : ");
		printListOfFrameDescriptors(factorizableGen2Descriptors); 
		System.out.println(""); */
		newGenOfDescriptorsBuilder = new NewGenOfDescriptorsBuilderImpl(2, (ICopycatSignal) signal, factorizableGen2Descriptors);
		List<IGrammaticalST> gen3Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
		if (gen3Descriptors.isEmpty() && Settings.MAX_NB_OF_DESCRIPTOR_GENERATIONS >= 3)
			returnsNextGenOfDescriptors = false;		
		List<Frame> factorizableGen3Descriptors = new ArrayList<Frame>();
		sortNewDescriptors(gen3Descriptors, factorizableGen3Descriptors);
		/*	System.out.println("***** GEN3 *****");
		System.out.println("-> whole String descriptors : ");
		System.out.println("");
		printListOfDescriptors(fullStringDescriptors); */
		/* System.out.println("-> factorizable descriptors : ");
		printListOfFrameDescriptors(factorizableGen3Descriptors); 
		System.out.println("");	*/
		newGenOfDescriptorsBuilder = new NewGenOfDescriptorsBuilderImpl(3, (ICopycatSignal) signal, factorizableGen3Descriptors);
		List<IGrammaticalST> gen4Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
		if (gen4Descriptors.isEmpty() && Settings.MAX_NB_OF_DESCRIPTOR_GENERATIONS >= 4)
			returnsNextGenOfDescriptors = false;		
		List<Frame> factorizableGen4Descriptors = new ArrayList<Frame>();
		sortNewDescriptors(gen4Descriptors, factorizableGen4Descriptors);
		/* System.out.println("***** GEN4 *****");
		System.out.println("-> whole String descriptors : ");
		System.out.println("");
		printListOfDescriptors(fullStringDescriptors); */
		/* System.out.println("-> factorizable descriptors : ")
		printListOfFrameDescriptors(factorizableGen4Descriptors); 
		System.out.println("");	*/ 
		assertTrue(returnsNextGenOfDescriptors);
	}
	
	private void sortNewDescriptors(List<IGrammaticalST> listOfNewDescriptors, 
			List<Frame> nextListOfFactorizableDescriptors) throws Exception {
		for (IGrammaticalST descriptor : listOfNewDescriptors) {
			if (descriptor.getDescriptorName().equals("frame"))
				nextListOfFactorizableDescriptors.add((Frame) descriptor);
			else if (descriptor.getDescriptorName().equals("charString"))
				fullStringDescriptors.add(descriptor);
			else throw new Exception();
		}
	}
	
	private void printListOfDescriptors(List<IGrammaticalST> listOfDescriptors) {
		for (IGrammaticalST descriptor : listOfDescriptors) {
			List<String> propertiesWithPath = descriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : propertiesWithPath) {
				System.out.println(propertyWithPath);
			}
			System.out.println("");
		}
	}
	
	private void printListOfFrameDescriptors(List<Frame> listOfDescriptors) {
		for (IGrammaticalST descriptor : listOfDescriptors) {
			List<String> propertiesWithPath = descriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : propertiesWithPath) {
				System.out.println(propertyWithPath);
			}
			System.out.println("");
		}
	}	

}
