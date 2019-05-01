package orderedSetGeneration.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.OmegaElement;
import model.synTreeModel.ISynTreeElement;
import orderedSetGeneration.IOrderedSetBuilder;

public class OrderedSetBuilderImpl implements IOrderedSetBuilder {

	private IOrderedSet omega;
	private int mapIndex = 1;
	private Map<List<String>, Integer> listOfPropertiesToIndex = new HashMap<List<String>, Integer>();
	
	public OrderedSetBuilderImpl(List<ISynTreeElement> listOfSynTreeElement) throws OrderedSetsGenerationException {
		setListOfPropertiesToIndexMap(listOfSynTreeElement);
		List<IOrderedSet> listOfSubMaximalPowerSetElements = new ArrayList<IOrderedSet>();
		for (ISynTreeElement synTreeElement : listOfSynTreeElement) {
			IOrderedSet subMaximalPowerSetElement = synTreeElement.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			listOfSubMaximalPowerSetElements.add(subMaximalPowerSetElement);
		}
		omega = new OmegaElement(listOfSubMaximalPowerSetElements);
	}
	
	public OrderedSetBuilderImpl(ISynTreeElement synTreeElement) throws OrderedSetsGenerationException {
		List<ISynTreeElement> listOfSynTreeElement = new ArrayList<ISynTreeElement>();
		listOfSynTreeElement.add(synTreeElement);
		setListOfPropertiesToIndexMap(listOfSynTreeElement);
		omega = synTreeElement.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		omega.setElementID("charString".concat(getRandomIDNumber()));
	}

	@Override
	public IOrderedSet getOrderedSet() {
		return omega;
	}

	@Override
	public Map<String, Set<String>> getRelation() throws OrderedSetsGenerationException {
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
	
	private String getRandomIDNumber() {
		String IDNumber = "";
		Random random = new Random();
		while (IDNumber.length() !=8) {
			IDNumber = IDNumber.concat(Integer.toString(random.nextInt(10)));
		}
		return IDNumber;
	}

}