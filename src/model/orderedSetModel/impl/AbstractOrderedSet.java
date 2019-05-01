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
	
	public AbstractOrderedSet(String elementID) {
		this.elementID = elementID;
		isTheCodedElement = false;
	}
	
	public AbstractOrderedSet(String elementID, boolean isCodingByDecomposition) {
		super(isCodingByDecomposition);
		this.elementID = elementID;
		isTheCodedElement = false;
	}
	
	public AbstractOrderedSet(String elementID, boolean isCodingByDecomposition, boolean mayBeTheCodedElement) {
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
	
	protected void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		for (IElement element : getListOfComponents()) {
			AbstractOrderedSet abstractOrderedSet = (AbstractOrderedSet) element;
			AbstractOrderedSet abstractOrderedSetRef = 
					(AbstractOrderedSet) idToIOrderedSet.get(abstractOrderedSet.getElementID());
			if (!abstractOrderedSet.equals(abstractOrderedSetRef))
				abstractOrderedSet = abstractOrderedSetRef;
			abstractOrderedSet.eliminateRedundancies(idToIOrderedSet);
		}
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
	
	protected void initializeNbOfParents() {
		nbOfParents = 0;
		for (IElement element : getListOfComponents()) {
			AbstractOrderedSet orderedSetComponent = (AbstractOrderedSet) element;
			orderedSetComponent.initializeNbOfParents();
		}
	}
	
	protected void setNbOfParents() {
		for (IElement element : getListOfComponents()) {
			AbstractOrderedSet orderedSetComponent = (AbstractOrderedSet) element;
			orderedSetComponent.incrementNbOfParents();
			orderedSetComponent.setNbOfParents();
		}
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

}
