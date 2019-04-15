package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;

public abstract class IrrelevantSetElementImpl extends SetElementImpl implements ISetElement {

	public IrrelevantSetElementImpl(String elementID) {
		super(elementID);
	}

	public IrrelevantSetElementImpl(String elementID, boolean isCodingByDecomposition) {
		super(elementID, isCodingByDecomposition);
	}

	public IrrelevantSetElementImpl(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
		super(elementID, isCodingByDecomposition, mayBeTheCodedElement);
	}
	
	@Override
	public List<String> getElementDescription() {
		List<String> listOfMaximalStringsOfElementLowerSet = new ArrayList<String>();
		List<IElement> listOfComponents = buildListOfComponents();
		for (IElement component : listOfComponents) {
			ISetElement setComponent = (SetElementImpl) component;
			List<String> listOfMaximalStringsOfComponentLowerSet = setComponent.getElementDescription();
			listOfMaximalStringsOfElementLowerSet.addAll(listOfMaximalStringsOfComponentLowerSet);
		}
		return listOfMaximalStringsOfElementLowerSet;
	}	
	
	@Override
	public Map<String, Set<String>> getRelation() {
		Map<String, Set<String>> relation = new HashMap<String, Set<String>>();
		List<IElement> listOfComponents = buildListOfComponents();
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
