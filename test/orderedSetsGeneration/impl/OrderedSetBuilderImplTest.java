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
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISignal;
import model.synTreeModel.ISynTreeElement;
import orderedSetGeneration.IOrderedSetBuilder;
import orderedSetGeneration.impl.OrderedSetBuilderImpl;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.ISignalBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;
import syntacticTreesGeneration.impl.SignalBuilderImpl;

public class OrderedSetBuilderImplTest {

	@Test
	public void whenOrderedSetIsBuiltThenCodingElementsCanBeRetrieved() 
			throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException {
		String thisString = "abcd";
		ISignalBuilder signalBuilder = new SignalBuilderImpl(thisString, "fromLeftToRight"); 
		ISignal signal = signalBuilder.getSignal();
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilderImpl(signal);
		List<ISynTreeElement> listOfDescriptors = 
				new ArrayList<ISynTreeElement>(listOfDescriptorsBuilder.getListOfStringDescriptors());
		IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilderImpl(listOfDescriptors);
		IOrderedSet omega = orderedSetBuilder.getOrderedSet();
		Set<IOrderedSet> lowerSet = omega.getLowerSet();
		Set<String> retrievedSetOfCodingElementsIDs = new HashSet<String>();
		for (IOrderedSet lowerSetElement : lowerSet) {
			if (lowerSetElement.getIsCodingByDecomposition() == true) {
				retrievedSetOfCodingElementsIDs.add(lowerSetElement.getElementID());
			}
		}
		assertTrue(retrievedSetOfCodingElementsIDs.size() == thisString.length());
	}
	
	@Test
	public void whenOrderedSetIsBuiltThenCodedElementsCanBeRetrieved() throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException {
		int nbOfCodedElements;
		String thisString = "abcd";
		ISignalBuilder signalBuilder = new SignalBuilderImpl(thisString, "fromLeftToRight"); 
		ISignal signal = signalBuilder.getSignal();
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilderImpl(signal);
		List<ISynTreeElement> listOfDescriptors = 
				new ArrayList<ISynTreeElement>(listOfDescriptorsBuilder.getListOfStringDescriptors());
		nbOfCodedElements = listOfDescriptors.size();
		IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilderImpl(listOfDescriptors);
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
		String thisString = "abcd";
		ISignalBuilder signalBuilder = new SignalBuilderImpl(thisString, "fromLeftToRight"); 
		ISignal signal = signalBuilder.getSignal();
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilderImpl(signal);
		List<ISynTreeElement> listOfDescriptors = 
				new ArrayList<ISynTreeElement>(listOfDescriptorsBuilder.getListOfStringDescriptors());
		IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilderImpl(listOfDescriptors);
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

}
