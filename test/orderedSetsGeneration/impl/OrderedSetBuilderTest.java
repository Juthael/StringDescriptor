package orderedSetsGeneration.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.copycatModel.signal.ICopycatSignal;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISyntacticTree;
import model.synTreeModel.IGrammaticalST;
import orderedSetGeneration.IOrderedSetBuilder;
import orderedSetGeneration.impl.OrderedSetBuilder;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.SignalBuilder;

public class OrderedSetBuilderTest {

	@Test
	public void whenOrderedSetIsBuiltThenCodingElementsCanBeRetrieved() 
			throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException, VerbalizationException {
		String thisString = "abcd";
		ISignalBuilder signalBuilder = new SignalBuilder(thisString, "fromLeftToRight"); 
		ISignal signal = signalBuilder.getSignal();
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilder((ICopycatSignal) signal);
		List<ISyntacticTree> listOfDescriptors = 
				new ArrayList<ISyntacticTree>(listOfDescriptorsBuilder.getListOfComprehensiveDescriptors());
		IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilder(listOfDescriptors);
		IOrderedSet omega = orderedSetBuilder.getOrderedSet();
		Set<IOrderedSet> lowerSet = omega.getLowerSet();
		Set<String> retrievedSetOfCodingElementsIDs = new HashSet<String>();
		for (IOrderedSet lowerSetElement : lowerSet) {
			if (lowerSetElement.getIsCodingElement() == true) {
				retrievedSetOfCodingElementsIDs.add(lowerSetElement.getElementID());
			}
		}
		assertTrue(!retrievedSetOfCodingElementsIDs.isEmpty());
	}
	
	@Test
	public void whenOrderedSetIsBuiltThenCodedElementsCanBeRetrieved() throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException, VerbalizationException {
		int nbOfCodedElements;
		String thisString = "abcd";
		ISignalBuilder signalBuilder = new SignalBuilder(thisString, "fromLeftToRight"); 
		ISignal signal = signalBuilder.getSignal();
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilder((ICopycatSignal) signal);
		List<ISyntacticTree> listOfDescriptors = 
				new ArrayList<ISyntacticTree>(listOfDescriptorsBuilder.getListOfComprehensiveDescriptors());
		nbOfCodedElements = listOfDescriptors.size();
		IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilder(listOfDescriptors);
		IOrderedSet omega = orderedSetBuilder.getOrderedSet();
		Set<IOrderedSet> lowerSet = omega.getLowerSet();
		Set<String> retrievedSetOfCodedElementsIDs = new HashSet<String>();
		for (IOrderedSet lowerSetElement : lowerSet) {
			if (lowerSetElement.getMayBeTheCodedElement() == true) {
				retrievedSetOfCodedElementsIDs.add(lowerSetElement.getElementID());
			}
		}
		assertTrue(retrievedSetOfCodedElementsIDs.size() == nbOfCodedElements);
	}
	
	@Test
	public void whenOrderedSetIsBuiltThenRelationWhithNoRedundancyIsReturned() 
			throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException {
		boolean thereIsNoRedundancy = true;
		try {
			String thisString = "abcd";
			ISignalBuilder signalBuilder = new SignalBuilder(thisString, "fromLeftToRight"); 
			ISignal signal = signalBuilder.getSignal();
			IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilder((ICopycatSignal) signal);
			List<ISyntacticTree> listOfDescriptors = 
					new ArrayList<ISyntacticTree>(listOfDescriptorsBuilder.getListOfComprehensiveDescriptors());
			IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilder(listOfDescriptors);
			IOrderedSet omega = orderedSetBuilder.getOrderedSet();
			Map<String, Set<String>> relation = omega.getRelation();
			List<String> attributes = new ArrayList<String>(relation.keySet());
			for (int i=0 ; i<attributes.size() ; i++) {
				Set<String> currentLowerSet = relation.get(attributes.get(i));
				for (int j=i+1 ; j<attributes.size() ; j++) {
					Set<String> comparedLowerSet = relation.get(attributes.get(j));
					if (currentLowerSet.equals(comparedLowerSet))
						thereIsNoRedundancy = false;
				}
			}
			assertTrue(thereIsNoRedundancy);
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
		}
	}

}
