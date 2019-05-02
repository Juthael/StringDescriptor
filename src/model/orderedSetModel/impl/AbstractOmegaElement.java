package model.orderedSetModel.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public abstract class AbstractOmegaElement extends AbstractNonMinimalOS implements IOrderedSet {

	public AbstractOmegaElement(String name) {
		super(name);
	}

	protected void eliminateRedundancies() {
		Set<IOrderedSet> unionOfComponentsLowerSetS = getUnionOfComponentsLowerSets();
		Map<String, IOrderedSet> idToIOrderedSet = new HashMap<String, IOrderedSet>();
		for (IOrderedSet orderedSet : unionOfComponentsLowerSetS) {
			if (!idToIOrderedSet.keySet().contains(orderedSet.getElementID()))
					idToIOrderedSet.put(orderedSet.getElementID(), orderedSet);
		}
		idToIOrderedSet = eliminateRedundanciesInMap(idToIOrderedSet);
		eliminateRedundancies(idToIOrderedSet);		
	}
	
	@Override
	protected boolean checkThatInformativeStatusIsUpToDate() {
		boolean anotherUpdateMustProceed = true;
		incrementComponentsNbOfParents();
		while (anotherUpdateMustProceed == true) {
			setNbOfInformativeChildren();
			boolean anElementHasBeenUpdated = false;
			for (IElement element : getListOfComponents()) {
				boolean thisElementHasBeenUpdated = false;
				AbstractOrderedSet abstractOrderedSet = (AbstractOrderedSet) element;
				thisElementHasBeenUpdated = abstractOrderedSet.checkThatInformativeStatusIsUpToDate();
				if (thisElementHasBeenUpdated == true)
					anElementHasBeenUpdated = true;
			}
			anotherUpdateMustProceed = anElementHasBeenUpdated;
		}
		return true;
	}
	
	private Map<String, IOrderedSet> eliminateRedundanciesInMap(Map<String, IOrderedSet> redundantMap) {
		for (String elementID : redundantMap.keySet()) {
			redundantMap.get(elementID).eliminateRedundancies(redundantMap);
		}
		return redundantMap;
	}

}
