package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import settings.Settings;

public class GenericOmegaElement extends AbstractOmegaElement implements IOrderedSet {

	private static final String NAME = "omega";
	private List<IOrderedSet> listOfOrderedSets = new ArrayList<IOrderedSet>();
	
	public GenericOmegaElement(List<IOrderedSet> listOfSetElements, String verbalDescription) {
		super(NAME, verbalDescription);
		for (IOrderedSet element : listOfSetElements) {
			element.setMayBeTheCodedElement(true);
			this.listOfOrderedSets.add(element);
		}
		eliminateRedundancies();
		if (Settings.NON_INFORMATIVE_ELEMENTS_MUST_BE_REMOVED)
			checkThatInformativeStatusIsUpToDate();
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.addAll(listOfOrderedSets);
		return listOfComponents;
	}	

}
