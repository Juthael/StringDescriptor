package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public class OmegaElement extends AbstractNonMinimalOS implements IOrderedSet {

	private static final String NAME = "omega";
	private List<IOrderedSet> listOfSubMaxElements = new ArrayList<IOrderedSet>();
	
	public OmegaElement(List<IOrderedSet> listOfSetElements) {
		super(NAME);
		for (IOrderedSet element : listOfSetElements) {
			element.setMayBeTheCodedElement(true);
			this.listOfSubMaxElements.add(element);
		}
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.addAll(listOfSubMaxElements);
		return listOfComponents;
	}

	public void eliminateRedundancies() {
		Set<IOrderedSet> lowerSet = getLowerSet();
		Map<String, IOrderedSet> idToIOrderedSet = new HashMap<String, IOrderedSet>();
		for (IOrderedSet orderedSet : lowerSet) {
			if (!idToIOrderedSet.keySet().contains(orderedSet.getElementID()))
					idToIOrderedSet.put(orderedSet.getElementID(), orderedSet);
		}
		eliminateRedundancies(idToIOrderedSet);		
	}
	
	@Override
	protected boolean checkThatInformativeStatusIsUpToDate() {
		boolean anotherUpdateMustProceed = true;
		while (anotherUpdateMustProceed == true) {
			initializeNbOfParents();
			setNbOfParents();
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

}
