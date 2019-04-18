package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;
import model.generalModel.impl.ElementImpl;
import model.orderedSetModel.ISetElement;
import settings.Settings;

public abstract class SetElementImpl extends ElementImpl implements ISetElement {

	private String elementID;
	private boolean mayBeTheCodedElement;
	private boolean isTheCodedElement;
	
	public SetElementImpl(String elementID) {
		this.elementID = elementID;
		mayBeTheCodedElement = false;
		isTheCodedElement = false;
	}
	
	public SetElementImpl(String elementID, boolean isCodingByDecomposition) {
		super(isCodingByDecomposition);
		this.elementID = elementID;
		mayBeTheCodedElement = false;
		isTheCodedElement = false;
	}
	
	public SetElementImpl(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
		super(isCodingByDecomposition);
		this.elementID = elementID;
		this.mayBeTheCodedElement = mayBeTheCodedElement;
		isTheCodedElement = false;
	}

	@Override
	public String getElementID() {
		return elementID;
	}
	
	@Override
	public Map<String, Set<String>> getRelation() {
		Map<String, Set<String>> relation = new HashMap<String, Set<String>>();
		relation.put(getElementID(), getLowerSet());
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			ISetElement setComponent = (ISetElement) component;
			relation.putAll(setComponent.getRelation());
		}
		return relation;
	}

	@Override
	public List<String> getLowerSetDescription() {
		List<String> listOfMaximalChainsOfElementLowerSet = new ArrayList<String>();
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			ISetElement setComponent = (ISetElement) component;
			List<String> listOfMaximalChainsOfComponentLowerSet = setComponent.getLowerSetDescription();
			for (String componentMaximalChain : listOfMaximalChainsOfComponentLowerSet) {
				String maximalChain = this.getElementID().concat(Settings.PATH_SEPARATOR + componentMaximalChain);
				listOfMaximalChainsOfElementLowerSet.add(maximalChain);
			}
		}
		return listOfMaximalChainsOfElementLowerSet;
	}	

	@Override
	public boolean getMayBeTheCodedElement() {
		return mayBeTheCodedElement;
	}

	@Override
	public boolean getIsTheCodedElement() {
		return isTheCodedElement;
	}
	
	public Set<String> getLowerSet() {
		Set<String> lowerSet = new HashSet<String>();
		lowerSet.add(getElementID());
		lowerSet.addAll(getUnionOfComponentsLowerSets());
		return lowerSet;
	}
	
	protected Set<String> getUnionOfComponentsLowerSets() {
		Set<String> unionOfComponentsLowerSets = new HashSet<String>();
		List<ISetElement> listOfComponents = new ArrayList<ISetElement>();
		for (IElement component : getListOfComponents()) {
			listOfComponents.add((ISetElement) component);
		}
		for (ISetElement component : listOfComponents) {
			unionOfComponentsLowerSets.addAll(component.getLowerSet());
		}
		return unionOfComponentsLowerSets;
	}
	
	@Override
	public void setMayBeTheCodedElement(boolean mayBeTheCodedElement) {
		this.mayBeTheCodedElement = mayBeTheCodedElement;

	}

	@Override
	public void setIsTheCodedElement(boolean isTheCodedElement) {
		this.isTheCodedElement = isTheCodedElement;

	}	

	@Override
	abstract public String getDescriptorName();

}
