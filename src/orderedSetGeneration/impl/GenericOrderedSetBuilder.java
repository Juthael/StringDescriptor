package orderedSetGeneration.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.OmegaOS;
import model.orderedSetModel.impl.GenericOmegaOS;
import model.synTreeModel.IGrammaticalST;
import orderedSetGeneration.IOrderedSetBuilder;
import settings.Settings;

public class GenericOrderedSetBuilder implements IOrderedSetBuilder {

	private OmegaOS omega;
	private int mapIndex = 1;
	private Map<List<String>, Integer> listOfPropertiesToIndex = new HashMap<List<String>, Integer>();
	
	public GenericOrderedSetBuilder(List<IGrammaticalST> listOfSynTreeElement) throws OrderedSetsGenerationException {
		setListOfPropertiesToIndexMap(listOfSynTreeElement);
		List<IOrderedSet> listOfSubMaximalPowerSetElements = new ArrayList<IOrderedSet>();
		for (IGrammaticalST grammaticalST : listOfSynTreeElement) {
			IOrderedSet subMaximalPowerSetElement = 
					grammaticalST.upgradeAsTheGenericElementOfAnOrderedSet(listOfPropertiesToIndex);
			listOfSubMaximalPowerSetElements.add(subMaximalPowerSetElement);
		}
		omega = new GenericOmegaOS(listOfSubMaximalPowerSetElements, Settings.NO_VERBAL_DESCRIPTION);
	}

	@Override
	public IOrderedSet getOrderedSet() {
		return omega;
	}

	@Override
	public Map<String, Set<String>> getRelation() throws OrderedSetsGenerationException {
		return omega.getRelation();
	}
	
	private void setListOfPropertiesToIndexMap(List<IGrammaticalST> listOfSynTreeElement) {
		for (IGrammaticalST grammaticalST : listOfSynTreeElement) {
			Set<List<String>> setOfPropertyLists = grammaticalST.getSetOfAllPropertyListsAccessibleFromThisDescriptor();
			for (List<String> listOfPropertiesWithPath : setOfPropertyLists) {
				if (!listOfPropertiesToIndex.containsKey(listOfPropertiesWithPath)) {
					listOfPropertiesToIndex.put(listOfPropertiesWithPath, mapIndex);
					mapIndex++;
				}
			}
		}
	}	

}
