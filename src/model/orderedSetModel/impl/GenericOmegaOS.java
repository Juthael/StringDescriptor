package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import settings.Settings;

public class GenericOmegaOS extends OmegaOS implements IOrderedSet {

	private static final String NAME = Settings.TREE_TOP_ELEMENT_GENERIC_NAME;
	private List<IOrderedSet> listOfOrderedSets = new ArrayList<IOrderedSet>();
	
	public GenericOmegaOS(List<IOrderedSet> listOfSetElements, String verbalDescription) {
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
	
	@Override
	public boolean getThisSetIsGeneric() {
		return true;
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.addAll(listOfOrderedSets);
		return listOfComponents;
	}	
	
	@Override
	public List<String> getListOfPropertiesWithPath(){
		return getListOfPropertiesWithPath(Settings.CALLED_BY_GENERIC);
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath(boolean calledByGeneric){
		List<String> listOfPropertiesWithPath;;
		if (calledByGeneric == Settings.CALLED_BY_GENERIC) {
			listOfPropertiesWithPath = new ArrayList<String>();
			List<IElement> listOfComponents = getListOfComponents();
			for (IElement componentDescriptor : listOfComponents) {
				boolean componentIsIdiosyncratic = this.getDescriptorName().equals(componentDescriptor.getDescriptorName());
				if (componentIsIdiosyncratic == false) {
					List<String> listOfComponentPropertiesWithPath = 
							componentDescriptor.getListOfPropertiesWithPath(calledByGeneric);
					for (String propertyWithPath : listOfComponentPropertiesWithPath){
						String propertyWithUpdatedPath = 
								this.getDescriptorName().concat(Settings.PATH_SEPARATOR + propertyWithPath);
						listOfPropertiesWithPath.add(propertyWithUpdatedPath);
					}	
				}
			}
		}
		else listOfPropertiesWithPath = super.getListOfPropertiesWithPath();
		return listOfPropertiesWithPath;
	}	

}