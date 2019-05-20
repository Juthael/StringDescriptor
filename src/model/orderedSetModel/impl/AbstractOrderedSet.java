package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;
import model.generalModel.impl.ElementImpl;
import model.orderedSetModel.IOrderedSet;
import settings.Settings;

public abstract class AbstractOrderedSet extends ElementImpl implements IOrderedSet {

	private String elementID;
	private boolean mayBeTheCodedElement;
	private boolean isTheCodedElement;
	private int nbOfParents = 0;
	private int nbOfInformativeChildren = 0;
	private boolean thisSetIsInformative = true;
	private boolean componentsNbOfParentsHaveBeenIncremented = false;
	
	public AbstractOrderedSet(String elementID) {
		this.elementID = elementID;
		isTheCodedElement = false;
	}
	
	public AbstractOrderedSet(String elementID, boolean isCodingElement) {
		super(isCodingElement);
		this.elementID = elementID;
		isTheCodedElement = false;
	}
	
	public AbstractOrderedSet(String elementID, boolean isCodingElement, boolean mayBeTheCodedElement) {
		super(isCodingElement);
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
		if (this.isCodingElement == true) {
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
	public Map<String, Set<String>> getReducedRelation(){
		Map<String, Set<String>> reducedRelation = new HashMap<String, Set<String>>();
		if (thisSetIsInformative == true) {
			reducedRelation.put(getElementID(), getLowerSetInformativeIDs());
			List<IElement> listOfComponents = getListOfComponents();
			for (IElement component : listOfComponents) {
				IOrderedSet setComponent = (IOrderedSet) component;
				if (setComponent.getThisSetIsInformative() == true)
					reducedRelation.putAll(setComponent.getReducedRelation());
			}	
		}
		return reducedRelation;
	}
	
	@Override
	public Map<String, Set<String>> getSetOfCodingComponentsRelation(){
		IOrderedSet omegaElement = buildGenericElementsWithCodingElemAsAtoms();
		return omegaElement.getRelation();
	}
	
	@Override
	public Map<String, Set<String>> getSetOfCodingComponentsReducedRelation(){
		IOrderedSet omegaElement = buildGenericElementsWithCodingElemAsAtoms();
		return omegaElement.getReducedRelation();
	}	
	
	@Override
	public List<String> getListOfLowerSetMaximalChains() {
		List<String> listOfMaximalChainsOfElementLowerSet = new ArrayList<String>();
		if (thisSetIsInformative == true) {
			List<IElement> listOfComponents = getListOfComponents();
			for (IElement component : listOfComponents) {
				IOrderedSet setComponent = (IOrderedSet) component;
				if (setComponent.getThisSetIsInformative() == true) {
					List<String> listOfMaximalChainsOfComponentLowerSet = setComponent.getListOfLowerSetMaximalChains();
					for (String componentMaximalChain : listOfMaximalChainsOfComponentLowerSet) {
						String maximalChain = this.getElementID().concat(Settings.PATH_SEPARATOR + componentMaximalChain);
						listOfMaximalChainsOfElementLowerSet.add(maximalChain);
					}
				}
			}
			if (listOfMaximalChainsOfElementLowerSet.isEmpty())
				listOfMaximalChainsOfElementLowerSet.add(this.getElementID());
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
	
	@Override
	public boolean getIsOmegaElement() {
		return false;
	}
	
	@Override
	public Set<String> getLowerSetIDs() {
		Set<String> lowerSet = new HashSet<String>();
		lowerSet.add(getElementID());
		lowerSet.addAll(getUnionOfComponentsLowerSetsIDs());
		return lowerSet;
	}
	
	@Override
	public Set<String> getLowerSetInformativeIDs(){
		Set<String> informativeLowerSet = new HashSet<String>();
		if (thisSetIsInformative == true) {
			informativeLowerSet.add(getElementID());
			informativeLowerSet.addAll(getUnionOfComponentsInformativeLowerSetsIDs());
		}
		return informativeLowerSet;
	}
	
	public Set<IOrderedSet> getLowerSet(){
		Set<IOrderedSet> lowerSet = new HashSet<IOrderedSet>();
		lowerSet.add(this);
		lowerSet.addAll(getUnionOfComponentsLowerSets());
		return lowerSet;
	}
	
	public Set<IOrderedSet> getInformativeLowerSet(){
		Set<IOrderedSet> informativeLowerSet = new HashSet<IOrderedSet>();
		if (thisSetIsInformative == true) {
			informativeLowerSet.add(this);
			informativeLowerSet.addAll(getUnionOfComponentsInformativeLowerSets());
		}
		return informativeLowerSet;
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
	
	@Override
	public int getNbOfParents() {
		return nbOfParents;
	}
	
	@Override
	public int getNbOfInformativeChildren() {
		return nbOfInformativeChildren;
	}
	
	@Override 
	public boolean getThisSetIsInformative() {
		return thisSetIsInformative;
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
	
	protected Set<String> getUnionOfComponentsInformativeLowerSetsIDs() {
		Set<String> unionOfComponentsLowerSetsIDs = new HashSet<String>();
		List<IOrderedSet> listOfComponents = new ArrayList<IOrderedSet>();
		for (IElement component : getListOfComponents()) {
			listOfComponents.add((IOrderedSet) component);
		}
		for (IOrderedSet component : listOfComponents) {
			if (component.getThisSetIsInformative() == true) {
				unionOfComponentsLowerSetsIDs.addAll(component.getLowerSetInformativeIDs());
			}
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
	
	protected Set<IOrderedSet> getUnionOfComponentsInformativeLowerSets(){
		Set<IOrderedSet> unionOfComponentsLowerSets = new HashSet<IOrderedSet>();
		List<IOrderedSet> listOfComponents = new ArrayList<IOrderedSet>();
		for (IElement component : getListOfComponents()) {
			listOfComponents.add((IOrderedSet) component);
		}
		for (IOrderedSet component :listOfComponents) {
			if (component.getThisSetIsInformative() == true)
				unionOfComponentsLowerSets.addAll(component.getInformativeLowerSet());
		}
		return unionOfComponentsLowerSets;
	}	
	
	protected void incrementComponentsNbOfParents() {
		if (componentsNbOfParentsHaveBeenIncremented == false) {
			for (IElement element : getListOfComponents()) {
				AbstractOrderedSet orderedSetComponent = (AbstractOrderedSet) element;
				orderedSetComponent.incrementNbOfParents();
				orderedSetComponent.incrementComponentsNbOfParents();
			}
		}
		componentsNbOfParentsHaveBeenIncremented = true;
	}
	
	protected void incrementNbOfParents() {
		nbOfParents++;
	}
	
	protected void setNbOfInformativeChildren() {
		nbOfInformativeChildren = 0;
		for (IElement element : getListOfComponents()) {
			AbstractOrderedSet orderedSetComponent = (AbstractOrderedSet) element;
			if (orderedSetComponent.getThisSetIsInformative() == true)
				nbOfInformativeChildren++;
			orderedSetComponent.setNbOfInformativeChildren();
		}		
	}
	
	protected boolean checkThatInformativeStatusIsUpToDate() {
		boolean thisElementHasBeenUpdated = false;
		if (thisSetIsInformative == true) {
			if (nbOfParents == 1 && nbOfInformativeChildren == 0) {
				thisSetIsInformative = false;
				thisElementHasBeenUpdated = true;
			}	
			else {
				for (IElement element : getListOfComponents()) {
					AbstractOrderedSet orderedSetComponent = (AbstractOrderedSet) element;
					boolean thisComponentHasBeenUpdated = orderedSetComponent.checkThatInformativeStatusIsUpToDate();
					if (thisComponentHasBeenUpdated == true)
						thisElementHasBeenUpdated = true;
				}				
			}
		}
		return thisElementHasBeenUpdated;
	}

	@Override
	abstract public String getDescriptorName();
	
	private GenericOmegaElement buildGenericElementsWithCodingElemAsAtoms() {
		String verbalDescription;
		if (this.getIsOmegaElement() == true) {
			AbstractOmegaElement omegaElement = (AbstractOmegaElement) this;
			verbalDescription = omegaElement.getVerbalDescription();
		}
		else verbalDescription = "No description available";
		List<IElement> listOfCodingComponents = getListOfCodingElements();
		List<IOrderedSet> listOfCodingComponentsOS = new ArrayList<IOrderedSet>();
		for (IElement element : listOfCodingComponents) {
			listOfCodingComponentsOS.add((IOrderedSet) element);
		}
		GenericOmegaElement omegaElement = new GenericOmegaElement(listOfCodingComponentsOS, verbalDescription);
		return omegaElement;
	}

}
