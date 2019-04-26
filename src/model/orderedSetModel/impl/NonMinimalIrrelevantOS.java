package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public abstract class NonMinimalIrrelevantOS extends OrderedSetImpl implements IOrderedSet {

	public NonMinimalIrrelevantOS(String elementID) {
		super(elementID);
	}

	@Override
	public List<String> getListOfLowerSetMaximalChains() {
		List<String> listOfMaximalStringsOfElementLowerSet = new ArrayList<String>();
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			IOrderedSet setComponent = (OrderedSetImpl) component;
			List<String> listOfMaximalStringsOfComponentLowerSet = setComponent.getListOfLowerSetMaximalChains();
			listOfMaximalStringsOfElementLowerSet.addAll(listOfMaximalStringsOfComponentLowerSet);
		}
		return listOfMaximalStringsOfElementLowerSet;
	}	
	
	@Override
	public Map<String, Set<String>> getRelation() {
		Map<String, Set<String>> relation = new HashMap<String, Set<String>>();
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			IOrderedSet setComponent = (OrderedSetImpl) component;
			relation.putAll(setComponent.getRelation());
		}
		return relation;
	}
	
	@Override
	public Set<String> getLowerSetIDs() {
		Set<String> lowerSet = new HashSet<String>();
		lowerSet.addAll(getUnionOfComponentsLowerSetsIDs());
		return lowerSet;
	}

}
