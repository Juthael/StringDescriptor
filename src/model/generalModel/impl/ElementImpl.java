package model.generalModel.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.generalModel.IElement;
import settings.Settings;

public abstract class ElementImpl implements IElement {

	protected boolean isCodingByDecomposition;
	
	public ElementImpl() {
		isCodingByDecomposition = false;
	}
	
	public ElementImpl(boolean isCodingByDecomposition) {
		this.isCodingByDecomposition = isCodingByDecomposition;
	}

	@Override
	public boolean getIsCodingByDecomposition() {
		return isCodingByDecomposition;
	}

	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement componentDescriptor : listOfComponents) {
			List<String> listOfComponentPropertiesWithPath = componentDescriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : listOfComponentPropertiesWithPath){
				String propertyWithUpdatedPath = 
						this.getDescriptorName().concat(Settings.PATH_SEPARATOR + propertyWithPath);
				listOfPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		return listOfPropertiesWithPath;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPathWithoutQuantifiers() {
		List<String> listOfPropertiesWithPathWithoutQuantifiers = new ArrayList<String>();
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		for (String property : listOfPropertiesWithPath) {
			String propertyWithoutQuantifiers = getPropertyWithoutQuantifiers(property);
			listOfPropertiesWithPathWithoutQuantifiers.add(propertyWithoutQuantifiers);
		}
		return listOfPropertiesWithPathWithoutQuantifiers;
	}	

	@Override
	public Set<List<String>> getSetOfAllPropertyListsAccessibleFromThisDescriptor() {
		Set<List<String>> setOfAllPropertyListsAccessibleFromThisDescriptor = new HashSet<List<String>>();
		setOfAllPropertyListsAccessibleFromThisDescriptor.add(getListOfPropertiesWithPath());
		setOfAllPropertyListsAccessibleFromThisDescriptor.addAll(getSetOfAllPropertyListsAccessibleFromComponents());
		return setOfAllPropertyListsAccessibleFromThisDescriptor;
	}

	public List<IElement> getListOfComponents(){
		List<IElement> listOfComponents = new ArrayList<IElement>();
		return listOfComponents;
	}
	
	@Override
	abstract public String getDescriptorName();
	
	private Set<List<String>> getSetOfAllPropertyListsAccessibleFromComponents(){
		Set<List<String>> setOfAllPropertyListsAccessibleFromComponents = new HashSet<List<String>>();
		List<IElement> listOfComponents = getListOfComponents();
		for (IElement component : listOfComponents) {
			setOfAllPropertyListsAccessibleFromComponents.add(component.getListOfPropertiesWithPath());
		}
		return setOfAllPropertyListsAccessibleFromComponents;
	}	
	
	private String getPropertyWithoutQuantifiers(String property) {
		String propertyWithoutQuantifiers;
		String[] propertyArray = property.split(Settings.PATH_SEPARATOR);
		StringBuilder sB = new StringBuilder();
		for (int i=0 ; i<propertyArray.length ; i++) {
			if (!propertyArray[i].contains("X")) {
				sB.append(propertyArray[i]);
				if (i < propertyArray.length - 1)
					sB.append(Settings.PATH_SEPARATOR);
			}
		}
		propertyWithoutQuantifiers = sB.toString();
		return propertyWithoutQuantifiers;
	}

}
