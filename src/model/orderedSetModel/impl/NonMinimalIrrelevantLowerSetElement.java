package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;

public abstract class NonMinimalIrrelevantLowerSetElement extends LowerSetElementImpl implements ILowerSetElement {

	public NonMinimalIrrelevantLowerSetElement(String elementID) {
		super(elementID);
	}

	@Override
	public List<String> getListOfLowerSetMaximalChains() {
		List<String> listOfMaximalStringsOfElementLowerSet = new ArrayList<String>();
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			ILowerSetElement setComponent = (LowerSetElementImpl) component;
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
			ILowerSetElement setComponent = (LowerSetElementImpl) component;
			relation.putAll(setComponent.getRelation());
		}
		return relation;
	}
	
	@Override
	public Set<String> getLowerSet() {
		Set<String> lowerSet = new HashSet<String>();
		lowerSet.addAll(getUnionOfComponentsLowerSets());
		return lowerSet;
	}

}
