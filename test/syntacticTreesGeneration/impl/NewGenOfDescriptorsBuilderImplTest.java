package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISynTreeElement;
import settings.Settings;
import syntacticTreesGeneration.INewGenOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.NewGenOfDescriptorsBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class NewGenOfDescriptorsBuilderImplTest {

	private List<ISynTreeElement> fullStringDescriptors = new ArrayList<ISynTreeElement>();
	
	@Test
	public void whenStringLengthAndSettingsAllowItThenReturnsNextGenOfDescriptors() 
			throws Exception {
		boolean returnsNextGenOfDescriptors = true;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcdefghij", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		INewGenOfDescriptorsBuilder newGenOfDescriptorsBuilder = 
				new NewGenOfDescriptorsBuilderImpl(1, signal, signal.getFrames());
		List<ISynTreeElement> gen2Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
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
		newGenOfDescriptorsBuilder = new NewGenOfDescriptorsBuilderImpl(2, signal, factorizableGen2Descriptors);
		List<ISynTreeElement> gen3Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
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
		newGenOfDescriptorsBuilder = new NewGenOfDescriptorsBuilderImpl(3, signal, factorizableGen3Descriptors);
		List<ISynTreeElement> gen4Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
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
	
	private void sortNewDescriptors(List<ISynTreeElement> listOfNewDescriptors, 
			List<Frame> nextListOfFactorizableDescriptors) throws Exception {
		for (ISynTreeElement descriptor : listOfNewDescriptors) {
			if (descriptor.getDescriptorName().equals("frame"))
				nextListOfFactorizableDescriptors.add((Frame) descriptor);
			else if (descriptor.getDescriptorName().equals("charString"))
				fullStringDescriptors.add(descriptor);
			else throw new Exception();
		}
	}
	
	private void printListOfDescriptors(List<ISynTreeElement> listOfDescriptors) {
		for (ISynTreeElement descriptor : listOfDescriptors) {
			List<String> propertiesWithPath = descriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : propertiesWithPath) {
				System.out.println(propertyWithPath);
			}
			System.out.println("");
		}
	}
	
	private void printListOfFrameDescriptors(List<Frame> listOfDescriptors) {
		for (ISynTreeElement descriptor : listOfDescriptors) {
			List<String> propertiesWithPath = descriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : propertiesWithPath) {
				System.out.println(propertyWithPath);
			}
			System.out.println("");
		}
	}	

}
