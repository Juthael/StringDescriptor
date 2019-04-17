package orderedSetsGeneration.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.orderedSetModel.ISetElement;
import model.synTreeModel.ISynTreeElement;
import orderedSetsGeneration.IOrderedSetBuilder;

public class OrderedSetBuilderImpl implements IOrderedSetBuilder {

	private ISetElement omega;
	private int mapIndex = 1;
	private Map<List<String>, Integer> listOfPropertiesToIndex = new HashMap<List<String>, Integer>();
	
	public OrderedSetBuilderImpl(List<ISynTreeElement> listOfSynTreeElement) {
		setListOfPropertiesToIndexMap(listOfSynTreeElement);
		List<ISetElement> listOfSubMaximalPowerSetElements = new ArrayList<ISetElement>();
		for (ISynTreeElement synTreeElement : listOfSynTreeElement) {
			ISetElement subMaximalPowerSetElement = synTreeElement.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			listOfSubMaximalPowerSetElements.add(subMaximalPowerSetElement);
		}
		omega = new OmegaElement(listOfSubMaximalPowerSetElements);
	}

	@Override
	public ISetElement getOrderedSet() {
		return omega;
	}

	@Override
	public Map<String, Set<String>> getRelation() {
		return omega.getRelation();
	}
	
	private void setListOfPropertiesToIndexMap(List<ISynTreeElement> listOfSynTreeElement) {
		for (ISynTreeElement synTreeElement : listOfSynTreeElement) {
			Set<List<String>> setOfPropertyLists = synTreeElement.getSetOfAllPropertyListsAccessibleFromThisDescriptor();
			for (List<String> listOfPropertiesWithPath : setOfPropertyLists) {
				if (!listOfPropertiesToIndex.containsKey(listOfPropertiesWithPath)) {
					listOfPropertiesToIndex.put(listOfPropertiesWithPath, mapIndex);
					mapIndex++;
				}
			}
		}
	}

}
