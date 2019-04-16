package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;

public abstract class NonMinimalIrrelevantSetElement extends SetElementImpl implements ISetElement {

	public NonMinimalIrrelevantSetElement(String elementID) {
		super(elementID);
	}

	@Override
	public List<String> getLowerSetDescription() {
		List<String> listOfMaximalStringsOfElementLowerSet = new ArrayList<String>();
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			ISetElement setComponent = (SetElementImpl) component;
			List<String> listOfMaximalStringsOfComponentLowerSet = setComponent.getLowerSetDescription();
			listOfMaximalStringsOfElementLowerSet.addAll(listOfMaximalStringsOfComponentLowerSet);
		}
		return listOfMaximalStringsOfElementLowerSet;
	}	
	
	@Override
	public Map<String, Set<String>> getRelation() {
		Map<String, Set<String>> relation = new HashMap<String, Set<String>>();
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			ISetElement setComponent = (SetElementImpl) component;
			relation.putAll(setComponent.getRelation());
		}
		return relation;
	}
	
	@Override
	protected Set<String> getLowerSet() {
		Set<String> lowerSet = new HashSet<String>();
		lowerSet.addAll(getUnionOfComponentsLowerSets());
		return lowerSet;
	}

}
