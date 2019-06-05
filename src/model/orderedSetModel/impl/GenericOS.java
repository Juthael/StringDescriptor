package model.orderedSetModel.impl;

import java.util.ArrayList;
import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IFrameOS;
import model.orderedSetModel.IOrderedSet;
import settings.Settings;

public class GenericOS extends OrderedSet implements IOrderedSet, IFrameOS {

	private final String descriptorName;
	List<IOrderedSet> listOfComponents;
	
	public GenericOS(String elementID, String descriptorName, List<IOrderedSet> listOfComponents) {
		super(elementID);
		this.descriptorName = descriptorName;
		this.listOfComponents = listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return descriptorName;
	}
	
	@Override
	public boolean getThisSetIsGeneric() {
		return true;
	}
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>();
		for (IOrderedSet orderedSetComponent : listOfComponents)
			componentDescriptors.add(orderedSetComponent);
		return componentDescriptors;
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
				IOrderedSet componentOS = (IOrderedSet) componentDescriptor;
				boolean componentIsIdiosyncratic;
				if (this.descriptorName == Settings.ABSTRACT_TREE_NAME)
					componentIsIdiosyncratic = componentOS.getElementID().equals(Settings.ABSTRACTABLE_TREE_NAME);
				else componentIsIdiosyncratic = this.getDescriptorName().equals(componentOS.getElementID());
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
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		for (IOrderedSet component : listOfComponents) {
			if (component.getElementID().equals(orderedSet.getElementID()) && component != orderedSet) {
				component = orderedSet;
			}
			else component.eliminateRedundancies(orderedSet);
		}
	}

}
