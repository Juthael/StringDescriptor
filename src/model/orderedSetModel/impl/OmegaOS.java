package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.utils.OrderedSetsComparator;

public abstract class OmegaOS extends NonMinimalOS implements IOrderedSet {

	private String verbalDescription;
	
	public OmegaOS(String name, String verbalDescription) {
		super(name);
		this.verbalDescription = verbalDescription;
	}
	
	public String getVerbalDescription() {
		return verbalDescription;
	}

	protected void eliminateRedundancies() {
		Set<IOrderedSet> unionOfComponentsLowerSets = getUnionOfComponentsLowerSets();
		Map<String, IOrderedSet> idToGrammaticalOrderedSet = new HashMap<String, IOrderedSet>();
		Map<String, IOrderedSet> idToGenericOrderedSet = new HashMap<String, IOrderedSet>();
		Map<String, IOrderedSet> idToIOrderedSet = new HashMap<String, IOrderedSet>();
		List<IOrderedSet> cleanListOfOS;
		for (IOrderedSet orderedSet : unionOfComponentsLowerSets) {
			if (orderedSet.getThisSetIsGeneric() == true) {
				if (!idToGenericOrderedSet.containsKey(orderedSet.getElementID()))
					idToGenericOrderedSet.put(orderedSet.getElementID(), orderedSet);
			}
			else {
				if (!idToGrammaticalOrderedSet.containsKey(orderedSet.getElementID()));
					idToGrammaticalOrderedSet.put(orderedSet.getElementID(), orderedSet);
			}
		}
		idToIOrderedSet.putAll(idToGenericOrderedSet);
		idToIOrderedSet.putAll(idToGrammaticalOrderedSet);
		cleanListOfOS = getCleanListOfOS(idToIOrderedSet);
		for (int i=cleanListOfOS.size()-1 ; i>=0 ; i--) {
			eliminateRedundancies(cleanListOfOS.get(i));
		}
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
				OrderedSet orderedSet = (OrderedSet) element;
				thisElementHasBeenUpdated = orderedSet.checkThatInformativeStatusIsUpToDate();
				if (thisElementHasBeenUpdated == true)
					anElementHasBeenUpdated = true;
			}
			anotherUpdateMustProceed = anElementHasBeenUpdated;
		}
		return true;
	}
	
	@Override
	public boolean getIsOmegaElement() {
		return true;
	}	
	
	private List<IOrderedSet> getCleanListOfOS(Map<String, IOrderedSet> redundantMap) {
		List<IOrderedSet> listOfOS = new ArrayList<IOrderedSet>();
		for (String elementID : redundantMap.keySet())
			listOfOS.add(redundantMap.get(elementID));
		Comparator<IOrderedSet> comparator = new OrderedSetsComparator();
		Collections.sort(listOfOS, comparator);
		for (int i=1 ; i<listOfOS.size() ; i++) {
			IOrderedSet oSToClean = listOfOS.get(i);
			for (int j=i-1 ; j>=0 ; j--) {
				IOrderedSet cleanOS = listOfOS.get(j);
				oSToClean.eliminateRedundancies(cleanOS);
			}
		}
		return listOfOS;
	}

}