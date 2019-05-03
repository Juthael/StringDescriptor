package model.orderedSetModel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.orderedSetModel.IOrderedSet;
import settings.Settings;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;

public class AbstractOrderedSetTest {
	
	@Test
	public void whenOrderedSetIsBuiltThenRelationMapCanBeProvided() throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException {
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = 
				new ListOfDescriptorsBuilderImpl("abc", "fromLeftToRight");
		List<CharString> listOfDescriptors = listOfDescriptorsBuilder.getListOfStringDescriptors();
		Set<List<String>> setOfAccessibleListOfProperties = new HashSet<List<String>>();
		for (CharString charString : listOfDescriptors) {
			setOfAccessibleListOfProperties.addAll(charString.getSetOfAllPropertyListsAccessibleFromThisDescriptor());
		}
		Map<List<String>, Integer> listOfPropertiesToIndex = new HashMap<List<String>, Integer>();
		int listOfPropertiesIndex = 0;
		for (List<String> listOfProperties : setOfAccessibleListOfProperties) {
			listOfPropertiesToIndex.put(listOfProperties, listOfPropertiesIndex);
			listOfPropertiesIndex++;
		}
		List<IOrderedSet> listOfOrderedSetElements = new ArrayList<IOrderedSet>();
		for (CharString descriptor : listOfDescriptors)
			listOfOrderedSetElements.add(descriptor.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
		IOrderedSet orderedSet = listOfOrderedSetElements.get(0);
		List<String> listOfLowerSetMaximalChains = orderedSet.getListOfLowerSetMaximalChains();
		Map<String, Set<String>> relation = orderedSet.getRelation();
		boolean aRelationAtLeastCantBeVerified = false;
		for (String element : relation.keySet()) {
			for (String relatedElement : relation.get(element)) {
				boolean thisRelationHasBeenVerified = false;
				for (String maximalChain : listOfLowerSetMaximalChains) {
					String[] maximalChainArray = maximalChain.split(Settings.PATH_SEPARATOR);
					boolean elementHasBeenFound = false;
					boolean relatedElementHasBeenFound = false;
					for (String currentElement : maximalChainArray) {
						if (currentElement.equals(element))
							elementHasBeenFound = true;
						if (currentElement.equals(relatedElement))
							relatedElementHasBeenFound = true;
					}
					if (elementHasBeenFound == true && relatedElementHasBeenFound == true) {
						thisRelationHasBeenVerified = true;
					}
				}
				if (thisRelationHasBeenVerified == false)
					aRelationAtLeastCantBeVerified = true;
			}
		}
		assertFalse(aRelationAtLeastCantBeVerified);
	}
	
	@Test
	public void whenOrderedSetIsBuiltThenTwoComponentsNeverHaveTheSameElementID() 
			throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException, VerbalizationException {
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = 
				new ListOfDescriptorsBuilderImpl("abc", "fromLeftToRight");
		List<CharString> listOfDescriptors = listOfDescriptorsBuilder.getListOfStringDescriptors();
		CharString charString = listOfDescriptors.get(0);
		IOrderedSet charStringOmega = charString.upgradeAsTheSupremumOfAnOrderedSet();
		Set<IOrderedSet> lowerSet = charStringOmega.getLowerSet();
		/* for (IOrderedSet set : lowerSet) {
			System.out.print("SET ID : ");
			System.out.println(set.getElementID());
			System.out.print("HASHCODE : ");
			System.out.println(set.hashCode());
			System.out.print("NB OF PARENTS :");
			System.out.println(set.getNbOfParents());
			System.out.print("NB OF INFORMATIVE CHILDREN : ");
			System.out.println(set.getNbOfInformativeChildren());
			System.out.print("INFORMATIVE : ");
			System.out.println(set.getThisSetIsInformative());
			System.out.println("");
		} */
		Map<String, Set<Integer>> elementIDToHashCode = new HashMap<String, Set<Integer>>();
		for (IOrderedSet set : lowerSet) {
			if (elementIDToHashCode.containsKey(set.getElementID()))
				elementIDToHashCode.get(set.getElementID()).add(set.hashCode());
			else {
				Set<Integer> setOfHashcodes = new HashSet<Integer>();
				setOfHashcodes.add(set.hashCode());
				elementIDToHashCode.put(set.getElementID(), setOfHashcodes);
			}
		}
		for (String elementID : elementIDToHashCode.keySet()) {
			if (elementIDToHashCode.get(elementID).size() > 1) {
				System.out.println("FAIL 1");
				fail();
			}	
		}
	}
	
	@Test
	public void whenOrderedSetIsBuiltThenReducedSetCanBeProvided() 
			throws OrderedSetsGenerationException, SynTreeGenerationException, CloneNotSupportedException, VerbalizationException {
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = 
				new ListOfDescriptorsBuilderImpl("abc", "fromLeftToRight");
		List<CharString> listOfDescriptors = listOfDescriptorsBuilder.getListOfStringDescriptors();
		CharString charString = listOfDescriptors.get(0);
		IOrderedSet charStringOmega = charString.upgradeAsTheSupremumOfAnOrderedSet();
		List<String> propertiesWithPath = charStringOmega.getListOfPropertiesWithPath();
		List<String> lowerSetMaximalChains = charStringOmega.getListOfLowerSetMaximalChains();
		Set<IOrderedSet> lowerSet = charStringOmega.getLowerSet();
		/* for (IOrderedSet set : lowerSet) {
			System.out.print("SET ID : ");
			System.out.println(set.getElementID());
			System.out.print("HASHCODE : ");
			System.out.println(set.hashCode());
			System.out.print("NB OF PARENTS :");
			System.out.println(set.getNbOfParents());
			System.out.print("NB OF INFORMATIVE CHILDREN : ");
			System.out.println(set.getNbOfInformativeChildren());
			System.out.print("INFORMATIVE : ");
			System.out.println(set.getThisSetIsInformative());
			System.out.println("");
		} */
		/* System.out.println("DESCRIPTION : ");
		for (String property : propertiesWithPath) {
			System.out.println(property);
		}
		System.out.println("");
		System.out.println("SET : ");
		for (String maxChain : lowerSetMaximalChains) {
			System.out.println(maxChain);
		} */
		Map<String, Set<String>> minimalElementToParents = new HashMap<String, Set<String>>();
		for (String maxChain : lowerSetMaximalChains) {
			int lastSlashIndex = maxChain.lastIndexOf(Settings.PATH_SEPARATOR);
			int beforeLastIndex = maxChain.substring(0, lastSlashIndex).lastIndexOf(Settings.PATH_SEPARATOR);
			String minimalElement = maxChain.substring(lastSlashIndex+1);
			String parent = maxChain.substring(beforeLastIndex + 1, lastSlashIndex);
			if (minimalElementToParents.containsKey(minimalElement)) {
				minimalElementToParents.get(minimalElement).add(parent);
			}
			else {
				Set<String> listOfParents = new HashSet<String>();
				listOfParents.add(parent);
				minimalElementToParents.put(minimalElement, listOfParents);
			}
		}
		for (String minimalElement : minimalElementToParents.keySet()) {
			if (minimalElementToParents.get(minimalElement).size() <=1) {
				fail();
			}	
		}
	}
	
	@Test
	public void whenOrderedSetIsBuiltThenReducedRelationCanBeProvided() 
			throws SynTreeGenerationException, CloneNotSupportedException, OrderedSetsGenerationException, VerbalizationException {
		IListOfDescriptorsBuilder listOfDescriptorsBuilder = 
				new ListOfDescriptorsBuilderImpl("abcd", "fromLeftToRight");
		List<CharString> listOfDescriptors = listOfDescriptorsBuilder.getListOfStringDescriptors();
		List<IOrderedSet> listOfOrderedSetElements = new ArrayList<IOrderedSet>();
		for (CharString descriptor : listOfDescriptors)
			listOfOrderedSetElements.add(descriptor.upgradeAsTheSupremumOfAnOrderedSet());
		IOrderedSet orderedSet = listOfOrderedSetElements.get(0);
		List<String> maxChains = orderedSet.getListOfLowerSetMaximalChains();
		/* for (String maxChain : maxChains) {
			System.out.println(maxChain);
		}
		System.out.println("");
		System.out.println(""); */
		Map<String, Set<String>> relation = orderedSet.getRelation();		
		Map<String, Set<String>> reducedRelation = orderedSet.getReducedRelation();
		/* System.out.println("REDUCED RELATION : ");
		System.out.println("");
		for (String key : reducedRelation.keySet()) {
			System.out.print(key.concat(" : "));
			System.out.println(reducedRelation.get(key).toString());
			System.out.println("");
		}
		System.out.println("KEYS REMOVED :");
		System.out.println("");
		for (String key : relation.keySet()) {
			if (!reducedRelation.containsKey(key)) {
				System.out.print(key.concat(" : "));
				System.out.println(relation.get(key).toString());
				System.out.println("");
			}
		} */
		assertTrue(relation.size() > reducedRelation.size());
	}

}
