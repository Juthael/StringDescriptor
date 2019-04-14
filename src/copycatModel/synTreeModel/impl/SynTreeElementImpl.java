package copycatModel.synTreeModel.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import copycatModel.synTreeModel.ISynTreeElement;
import settings.Settings;

public abstract class SynTreeElementImpl implements ISynTreeElement {

	protected boolean isCodingByDecomposition;
	
	public SynTreeElementImpl() {
		isCodingByDecomposition = false;
	}
	
	public SynTreeElementImpl(boolean isCodingByDecomposition) {
		this.isCodingByDecomposition = isCodingByDecomposition;
	}

	@Override
	public boolean getIsCodingByDecomposition() {
		return isCodingByDecomposition;
	}

	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		List<SynTreeIntegrableElementImpl> listOfComponents = buildListOfComponents();
		for (SynTreeIntegrableElementImpl componentDescriptor : listOfComponents) {
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
	
	public Set<List<String>> getSetOfAllPropertyListsAccessibleFromComponents(){
		Set<List<String>> setOfAllPropertyListsAccessibleFromComponents = new HashSet<List<String>>();
		List<SynTreeIntegrableElementImpl> listOfComponents = buildListOfComponents();
		for (SynTreeIntegrableElementImpl component : listOfComponents) {
			setOfAllPropertyListsAccessibleFromComponents.add(component.getListOfPropertiesWithPath());
		}
		return setOfAllPropertyListsAccessibleFromComponents;
	}

	//Abstract
	abstract protected List<SynTreeIntegrableElementImpl> buildListOfComponents();	
	
	@Override
	abstract public String getDescriptorName();
	
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
