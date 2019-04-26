package orderedSetsGeneration.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import exceptions.OrderedSetsGenerationException;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import orderedSetGeneration.IOrderedSetBuilder;
import orderedSetGeneration.impl.OrderedSetBuilderImpl;
import orderedSetGeneration.impl.RelationClarifierImpl;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;

public class RelationClarifierImplTest {

	@Test
	public void whenRelationEnteredThenRelationWithoutNonInformingElementsReturned() throws OrderedSetsGenerationException {
		Map<String, Set<String>> clarifiedRelation;
		Map<String, Set<String>> relation = new HashMap<String, Set<String>>();
		Set<String> relatedToA = new HashSet(Arrays.asList(new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"} ));
		Set<String> relatedToB = new HashSet(Arrays.asList(new String[] {"b", "f", "g", "k", "l"} ));
		Set<String> relatedToC = new HashSet(Arrays.asList(new String[] {"c", "g", "l"} ));
		Set<String> relatedToD = new HashSet(Arrays.asList(new String[] {"d", "h", "i", "m", "n"} ));
		Set<String> relatedToE = new HashSet(Arrays.asList(new String[] {"e", "i", "j"} ));
		Set<String> relatedToF = new HashSet(Arrays.asList(new String[] {"f", "k", "l"} ));
		Set<String> relatedToG = new HashSet(Arrays.asList(new String[] {"g", "l"} ));
		Set<String> relatedToH = new HashSet(Arrays.asList(new String[] {"h", "m", "n"} ));
		Set<String> relatedToI = new HashSet(Arrays.asList(new String[] {"i"} ));
		Set<String> relatedToJ = new HashSet(Arrays.asList(new String[] {"j"} ));
		Set<String> relatedToK = new HashSet(Arrays.asList(new String[] {"k"} ));
		Set<String> relatedToL = new HashSet(Arrays.asList(new String[] {"l"} ));
		Set<String> relatedToM = new HashSet(Arrays.asList(new String[] {"m"} ));
		Set<String> relatedToN = new HashSet(Arrays.asList(new String[] {"n"} ));
		relation.put("a", relatedToA);
		relation.put("b", relatedToB);
		relation.put("c", relatedToC);
		relation.put("d", relatedToD);
		relation.put("e", relatedToE);
		relation.put("f", relatedToF);
		relation.put("g", relatedToG);
		relation.put("h", relatedToH);
		relation.put("i", relatedToI);
		relation.put("j", relatedToJ);
		relation.put("k", relatedToK);
		relation.put("l", relatedToL);
		relation.put("m", relatedToM);
		relation.put("n", relatedToN);
		try {
			RelationClarifierImpl relationClarifier = new RelationClarifierImpl(relation);
			clarifiedRelation = relationClarifier.getClarifiedRelation();
			Set<String> expectedRemainingAttributes = new HashSet<String>(
					Arrays.asList(new String[] {"a", "b", "c", "d", "e", "f", "g", "i", "l"}));
			assertTrue(clarifiedRelation.keySet().equals(expectedRemainingAttributes));
		}
		catch (Exception unexpected) {
			System.out.println(unexpected.getMessage());
			unexpected.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void whenCharStringRelationEnteredThenRelationClarifiedWithoutThrowingException() {
		try {
			IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilderImpl("abcd", "fromLeftToRight");
			List<ISynTreeElement> listOfDescriptors = new ArrayList<ISynTreeElement>();
			listOfDescriptors.addAll(listOfDescriptorsBuilder.getListOfStringDescriptors());
			IOrderedSetBuilder orderedSetBuilder = new OrderedSetBuilderImpl(listOfDescriptors);
			IOrderedSet orderedSet = orderedSetBuilder.getOrderedSet();
			Map<String, Set<String>> relation = orderedSet.getRelation();
			//System.out.println(relation.size());
			Map<String, Set<String>> clarifiedRelation = orderedSet.getClarifiedRelation();
			//System.out.println(clarifiedRelation.size());
			/* List<String> lowerSetElementDescription = lowerSetElement.getListOfLowerSetMaximalChains();
			for (String maximalChain : lowerSetElementDescription) {
				System.out.println(maximalChain);
			} */
		}
		catch (Exception unexpected) {
			fail();
		}
	}
}
