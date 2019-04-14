package syntacticTreesGeneration.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import copycatModel.synTreeModel.ISignal;
import copycatModel.synTreeModel.ISynTreeIntegrableElement;
import copycatModel.synTreeModel.grammar.Group;
import settings.Settings;
import syntacticTreesGeneration.INewGenOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.NewGenOfDescriptorsBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class NewGenOfDescriptorsBuilderImplTest {

	private List<ISynTreeIntegrableElement> fullStringDescriptors = new ArrayList<ISynTreeIntegrableElement>();
	
	@Test
	public void whenStringLengthAndSettingsAllowItThenReturnsNextGenOfDescriptors() 
			throws Exception {
		boolean returnsNextGenOfDescriptors = true;
		ISignalBuilder signalBuilder = new SignalBuilderImpl("abcdefghij", "fromLeftToRight");
		ISignal signal = signalBuilder.getSignal();
		INewGenOfDescriptorsBuilder newGenOfDescriptorsBuilder = 
				new NewGenOfDescriptorsBuilderImpl(1, signal, signal.getGroups());
		List<ISynTreeIntegrableElement> gen2Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
		if (gen2Descriptors.isEmpty() && Settings.MAX_NB_OF_DESCRIPTOR_GENERATIONS >= 2)
			returnsNextGenOfDescriptors = false;
		List<Group> factorizableGen2Descriptors = new ArrayList<Group>();
		sortNewDescriptors(gen2Descriptors, factorizableGen2Descriptors); 
		/*	System.out.println("***** GEN2 *****");
		System.out.println("-> whole String descriptors : ");
		System.out.println("");
		printListOfDescriptors(fullStringDescriptors); */
		/* System.out.println("-> factorizable descriptors : ");
		printListOfGroupDescriptors(factorizableGen2Descriptors); 
		System.out.println(""); */
		newGenOfDescriptorsBuilder = new NewGenOfDescriptorsBuilderImpl(2, signal, factorizableGen2Descriptors);
		List<ISynTreeIntegrableElement> gen3Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
		if (gen3Descriptors.isEmpty() && Settings.MAX_NB_OF_DESCRIPTOR_GENERATIONS >= 3)
			returnsNextGenOfDescriptors = false;		
		List<Group> factorizableGen3Descriptors = new ArrayList<Group>();
		sortNewDescriptors(gen3Descriptors, factorizableGen3Descriptors);
		/*	System.out.println("***** GEN3 *****");
		System.out.println("-> whole String descriptors : ");
		System.out.println("");
		printListOfDescriptors(fullStringDescriptors); */
		/* System.out.println("-> factorizable descriptors : ");
		printListOfGroupDescriptors(factorizableGen3Descriptors); 
		System.out.println("");	*/
		newGenOfDescriptorsBuilder = new NewGenOfDescriptorsBuilderImpl(3, signal, factorizableGen3Descriptors);
		List<ISynTreeIntegrableElement> gen4Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
		if (gen4Descriptors.isEmpty() && Settings.MAX_NB_OF_DESCRIPTOR_GENERATIONS >= 4)
			returnsNextGenOfDescriptors = false;		
		List<Group> factorizableGen4Descriptors = new ArrayList<Group>();
		sortNewDescriptors(gen4Descriptors, factorizableGen4Descriptors);
		/* System.out.println("***** GEN4 *****");
		System.out.println("-> whole String descriptors : ");
		System.out.println("");
		printListOfDescriptors(fullStringDescriptors); */
		/* System.out.println("-> factorizable descriptors : ")
		printListOfGroupDescriptors(factorizableGen4Descriptors); 
		System.out.println("");	*/ 
		assertTrue(returnsNextGenOfDescriptors);
	}
	
	private void sortNewDescriptors(List<ISynTreeIntegrableElement> listOfNewDescriptors, 
			List<Group> nextListOfFactorizableDescriptors) throws Exception {
		for (ISynTreeIntegrableElement descriptor : listOfNewDescriptors) {
			if (descriptor.getDescriptorName().equals("group"))
				nextListOfFactorizableDescriptors.add((Group) descriptor);
			else if (descriptor.getDescriptorName().equals("charString"))
				fullStringDescriptors.add(descriptor);
			else throw new Exception();
		}
	}
	
	private void printListOfDescriptors(List<ISynTreeIntegrableElement> listOfDescriptors) {
		for (ISynTreeIntegrableElement descriptor : listOfDescriptors) {
			List<String> propertiesWithPath = descriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : propertiesWithPath) {
				System.out.println(propertyWithPath);
			}
			System.out.println("");
		}
	}
	
	private void printListOfGroupDescriptors(List<Group> listOfDescriptors) {
		for (ISynTreeIntegrableElement descriptor : listOfDescriptors) {
			List<String> propertiesWithPath = descriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : propertiesWithPath) {
				System.out.println(propertyWithPath);
			}
			System.out.println("");
		}
	}	

}
