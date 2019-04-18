package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.orderedSetModel.ILowerSetElement;

public abstract class NonMinimalDeadLowerSetElement extends LowerSetElementImpl implements ILowerSetElement {

	public NonMinimalDeadLowerSetElement(String elementID) {
		super(elementID);
	}

	@Override
	public List<String> getListOfLowerSetMaximalChains() {
		List<String> listOfMaximalStringsOfElementLowerSet = new ArrayList<String>();
		return listOfMaximalStringsOfElementLowerSet;
	}	
	
	@Override
	public Map<String, Set<String>> getRelation() {
		Map<String, Set<String>> relation = new HashMap<String, Set<String>>();
		return relation;
	}
	
	@Override
	public Set<String> getLowerSet() {
		Set<String> lowerSet = new HashSet<String>();
		return lowerSet;
	}
}
