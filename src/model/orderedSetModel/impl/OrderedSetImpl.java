package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import model.generalModel.IElement;
import model.generalModel.impl.ElementImpl;
import model.orderedSetModel.IOrderedSet;
import orderedSetGeneration.RelationClarifier;
import orderedSetGeneration.impl.RelationClarifierImpl;
import settings.Settings;

public abstract class OrderedSetImpl extends ElementImpl implements IOrderedSet {

	private String elementID;
	private boolean mayBeTheCodedElement;
	private boolean isTheCodedElement;
	
	public OrderedSetImpl(String elementID) {
		this.elementID = elementID;
		isTheCodedElement = false;
	}
	
	public OrderedSetImpl(String elementID, boolean isCodingByDecomposition) {
		super(isCodingByDecomposition);
		this.elementID = elementID;
		isTheCodedElement = false;
	}
	
	public OrderedSetImpl(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
		super(isCodingByDecomposition);
		this.elementID = elementID;
		isTheCodedElement = false;
	}

	@Override
	public String getElementID() {
		return elementID;
	}
	
	@Override
	public List<String> getListOfCodingComponentsIDs(){
		List<String> listOfCodingComponentsIDs = new ArrayList<String>();
		if (this.isCodingByDecomposition == true) {
			listOfCodingComponentsIDs.add(elementID);
			for (IElement element : getListOfComponents()) {
				IOrderedSet subset = (IOrderedSet) element;
				listOfCodingComponentsIDs.addAll(subset.getListOfCodingComponentsIDs());
			}
		}
		return listOfCodingComponentsIDs;
	}	
	
	@Override
	public Map<String, Set<String>> getRelation(){
		Map<String, Set<String>> relation = new HashMap<String, Set<String>>();
		relation.put(getElementID(), getLowerSetIDs());
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			IOrderedSet setComponent = (IOrderedSet) component;
			relation.putAll(setComponent.getRelation());
		}
		return relation;
	}
	
	@Override
	public Map<String, Set<String>> getClarifiedRelation() throws OrderedSetsGenerationException {
		Map<String, Set<String>> relation = getRelation();
		if (Settings.RELATION_MUST_BE_CLARIFIED) {
			RelationClarifier relationClarifier = new RelationClarifierImpl(relation);
			relation = relationClarifier.getClarifiedRelation();
		}
		return relation;
	}

	@Override
	public List<String> getListOfLowerSetMaximalChains() {
		List<String> listOfMaximalChainsOfElementLowerSet = new ArrayList<String>();
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			IOrderedSet setComponent = (IOrderedSet) component;
			List<String> listOfMaximalChainsOfComponentLowerSet = setComponent.getListOfLowerSetMaximalChains();
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
	
	public Set<String> getLowerSetIDs() {
		Set<String> lowerSet = new HashSet<String>();
		lowerSet.add(getElementID());
		lowerSet.addAll(getUnionOfComponentsLowerSetsIDs());
		return lowerSet;
	}
	
	public Set<IOrderedSet> getLowerSet(){
		Set<IOrderedSet> lowerSet = new HashSet<IOrderedSet>();
		lowerSet.add(this);
		lowerSet.addAll(getUnionOfComponentsLowerSets());
		return lowerSet;
	}
	
	@Override
	public void setElementID(String elementID) {
		this.elementID = elementID;
	}
	
	@Override
	public void setMayBeTheCodedElement(boolean mayBeTheCodedElement) {
		this.mayBeTheCodedElement = mayBeTheCodedElement; 
	}
	
	@Override
	public void setIsTheCodedElement(boolean isTheCodedElement) {
		this.isTheCodedElement = isTheCodedElement;

	}
	
	protected Set<String> getUnionOfComponentsLowerSetsIDs() {
		Set<String> unionOfComponentsLowerSetsIDs = new HashSet<String>();
		List<IOrderedSet> listOfComponents = new ArrayList<IOrderedSet>();
		for (IElement component : getListOfComponents()) {
			listOfComponents.add((IOrderedSet) component);
		}
		for (IOrderedSet component : listOfComponents) {
			unionOfComponentsLowerSetsIDs.addAll(component.getLowerSetIDs());
		}
		return unionOfComponentsLowerSetsIDs;
	}
	
	protected Set<IOrderedSet> getUnionOfComponentsLowerSets(){
		Set<IOrderedSet> unionOfComponentsLowerSets = new HashSet<IOrderedSet>();
		List<IOrderedSet> listOfComponents = new ArrayList<IOrderedSet>();
		for (IElement component : getListOfComponents()) {
			listOfComponents.add((IOrderedSet) component);
		}
		for (IOrderedSet component :listOfComponents) {
			unionOfComponentsLowerSets.addAll(component.getLowerSet());
		}
		return unionOfComponentsLowerSets;
	}

	@Override
	abstract public String getDescriptorName();

}
