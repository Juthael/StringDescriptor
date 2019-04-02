package descriptorsGeneration.implementations;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.SignalInterface;
import settings.DescGenSettings;
import syntacticTreesGeneration.implementations.NewGenOfDescriptorsBuilderV1;
import syntacticTreesGeneration.implementations.SignalBuilderV1;
import syntacticTreesGeneration.interfaces.NewGenOfDescriptorsBuilderInterface;
import syntacticTreesGeneration.interfaces.SignalBuilderInterface;

class NewGenOfDescriptorsBuilderV1Test {

	private ArrayList<AbstractDescriptorInterface> fullStringDescriptors = new ArrayList<AbstractDescriptorInterface>();
	
	@Test
	void whenStringLengthAndSettingsAllowItThenReturnsNextGenOfDescriptors() 
			throws Exception {
		boolean returnsNextGenOfDescriptors = true;
		SignalBuilderInterface signalBuilder = new SignalBuilderV1("abcdefghij", "fromLeftToRight");
		SignalInterface signal = signalBuilder.getSignal();
		NewGenOfDescriptorsBuilderInterface newGenOfDescriptorsBuilder = 
				new NewGenOfDescriptorsBuilderV1(1, signal, signal.getGroups());
		ArrayList<AbstractDescriptorInterface> gen2Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
		if (gen2Descriptors.isEmpty() && DescGenSettings.MAX_NB_OF_DESCRIPTOR_GENERATIONS >= 2)
			returnsNextGenOfDescriptors = false;
		ArrayList<Group> factorizableGen2Descriptors = new ArrayList<Group>();
		sortNewDescriptors(gen2Descriptors, factorizableGen2Descriptors); 
		/*	System.out.println("***** GEN2 *****");
		System.out.println("-> whole String descriptors : ");
		System.out.println("");
		printListOfDescriptors(fullStringDescriptors); */
		/* System.out.println("-> factorizable descriptors : ");
		printListOfGroupDescriptors(factorizableGen2Descriptors); 
		System.out.println(""); */
		newGenOfDescriptorsBuilder = new NewGenOfDescriptorsBuilderV1(2, signal, factorizableGen2Descriptors);
		ArrayList<AbstractDescriptorInterface> gen3Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
		if (gen3Descriptors.isEmpty() && DescGenSettings.MAX_NB_OF_DESCRIPTOR_GENERATIONS >= 3)
			returnsNextGenOfDescriptors = false;		
		ArrayList<Group> factorizableGen3Descriptors = new ArrayList<Group>();
		sortNewDescriptors(gen3Descriptors, factorizableGen3Descriptors);
		/*	System.out.println("***** GEN3 *****");
		System.out.println("-> whole String descriptors : ");
		System.out.println("");
		printListOfDescriptors(fullStringDescriptors); */
		/* System.out.println("-> factorizable descriptors : ");
		printListOfGroupDescriptors(factorizableGen3Descriptors); 
		System.out.println("");	*/
		newGenOfDescriptorsBuilder = new NewGenOfDescriptorsBuilderV1(3, signal, factorizableGen3Descriptors);
		ArrayList<AbstractDescriptorInterface> gen4Descriptors = newGenOfDescriptorsBuilder.getNewGenOfDescriptors();
		if (gen4Descriptors.isEmpty() && DescGenSettings.MAX_NB_OF_DESCRIPTOR_GENERATIONS >= 4)
			returnsNextGenOfDescriptors = false;		
		ArrayList<Group> factorizableGen4Descriptors = new ArrayList<Group>();
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
	
	private void sortNewDescriptors(ArrayList<AbstractDescriptorInterface> listOfNewDescriptors, 
			ArrayList<Group> nextListOfFactorizableDescriptors) throws Exception {
		for (AbstractDescriptorInterface descriptor : listOfNewDescriptors) {
			if (descriptor.getDescriptorName().equals("group"))
				nextListOfFactorizableDescriptors.add((Group) descriptor);
			else if (descriptor.getDescriptorName().equals("charString"))
				fullStringDescriptors.add(descriptor);
			else throw new Exception();
		}
	}
	
	private void printListOfDescriptors(ArrayList<AbstractDescriptorInterface> listOfDescriptors) {
		for (AbstractDescriptorInterface descriptor : listOfDescriptors) {
			ArrayList<String> propertiesWithPath = descriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : propertiesWithPath) {
				System.out.println(propertyWithPath);
			}
			System.out.println("");
		}
	}
	
	private void printListOfGroupDescriptors(ArrayList<Group> listOfDescriptors) {
		for (AbstractDescriptorInterface descriptor : listOfDescriptors) {
			ArrayList<String> propertiesWithPath = descriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : propertiesWithPath) {
				System.out.println(propertyWithPath);
			}
			System.out.println("");
		}
	}	

}
