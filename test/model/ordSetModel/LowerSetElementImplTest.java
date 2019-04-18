package model.ordSetModel;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.orderedSetModel.ILowerSetElement;
import settings.Settings;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;

public class LowerSetElementImplTest {

	@Test
	public void whenLowerSetElementIsBuiltThenAccurateRelationIsProvided() throws SynTreeGenerationException, CloneNotSupportedException {
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
		List<ILowerSetElement> listOfOrderedSetElements = new ArrayList<ILowerSetElement>();
		for (CharString descriptor : listOfDescriptors)
			listOfOrderedSetElements.add(descriptor.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex));
		ILowerSetElement lowerSetElement = listOfOrderedSetElements.get(0);
		List<String> listOfLowerSetMaximalChains = lowerSetElement.getListOfLowerSetMaximalChains();
		Map<String, Set<String>> relation = lowerSetElement.getRelation();
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

}
