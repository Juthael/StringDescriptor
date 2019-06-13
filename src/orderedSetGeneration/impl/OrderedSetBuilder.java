package orderedSetGeneration.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.VerbalizationException;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.GenericOmegaOS;
import model.orderedSetModel.impl.OmegaOS;
import model.synTreeModel.IStartElementST;
import model.synTreeModel.ISyntacticTree;
import orderedSetGeneration.IOrderedSetBuilder;
import settings.Settings;

public class OrderedSetBuilder implements IOrderedSetBuilder {

	private OmegaOS omega;
	private int mapIndex = 1;
	private Map<List<String>, Integer> listOfPropertiesToIndex = new HashMap<List<String>, Integer>();
	
	public OrderedSetBuilder(List<ISyntacticTree> listOfSynTreeElement, String verbalDescription) 
			throws OrderedSetsGenerationException {
		setListOfPropertiesToIndexMap(listOfSynTreeElement);
		List<IOrderedSet> listOfSubMaximalPowerSetElements = new ArrayList<IOrderedSet>();
		for (ISyntacticTree synTreeGrammElement : listOfSynTreeElement) {
			IOrderedSet subMaximalPowerSetElement = synTreeGrammElement.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			listOfSubMaximalPowerSetElements.add(subMaximalPowerSetElement);
		}
		omega = new GenericOmegaOS(listOfSubMaximalPowerSetElements, verbalDescription);
	}
	
	public OrderedSetBuilder(List<ISyntacticTree> listOfSynTreeElement) throws OrderedSetsGenerationException {
		setListOfPropertiesToIndexMap(listOfSynTreeElement);
		List<IOrderedSet> listOfSubMaximalPowerSetElements = new ArrayList<IOrderedSet>();
		for (ISyntacticTree synTreeGrammElement : listOfSynTreeElement) {
			IOrderedSet subMaximalPowerSetElement = synTreeGrammElement.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			listOfSubMaximalPowerSetElements.add(subMaximalPowerSetElement);
		}
		omega = new GenericOmegaOS(listOfSubMaximalPowerSetElements, Settings.NO_VERBAL_DESCRIPTION);
	}	
	
	public OrderedSetBuilder(IStartElementST startElement) 
			throws OrderedSetsGenerationException, VerbalizationException {
		omega = startElement.upgradeAsTheSupremumOfAnOrderedSet();
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
	
	private void setListOfPropertiesToIndexMap(List<ISyntacticTree> listOfSynTreeElement) {
		for (ISyntacticTree syntacticTree : listOfSynTreeElement) {
			Set<List<String>> setOfPropertyLists = syntacticTree.getSetOfAllPropertyListsAccessibleFromThisDescriptor();
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
