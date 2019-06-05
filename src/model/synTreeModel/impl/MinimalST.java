package model.synTreeModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.MinimalOS;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.IMinimalST;
import model.synTreeModel.impl.utils.IProperty;
import model.synTreeModel.impl.utils.IPropertyContainer;
import model.synTreeModel.impl.utils.impl.Property;
import model.synTreeModel.impl.utils.impl.PropertyContainer;
import settings.Settings;

public class MinimalST extends GenericST implements IGrammaticalST, IMinimalST {

	public MinimalST(List<String> treeListOfMaxChains,
			Map<String, String> orderedSetIDToDescriptorName) throws SynTreeGenerationException {
		super(treeListOfMaxChains, orderedSetIDToDescriptorName);
	}

	public MinimalST(MinimalST minimalST) throws CloneNotSupportedException {
		super(minimalST);
	}
	
	public MinimalST(String value) {
		super(value);
	}	
	
	@Override
	public MinimalST clone() throws CloneNotSupportedException {
		return new MinimalST(this);
	}	

	@Override
	public List<String> getListOfPropertiesWithPath() {
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		listOfPropertiesWithPath.add(getValue());
		return listOfPropertiesWithPath;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath() {
		return getListOfPropertiesWithPath();
	}	
	
	@Override
	public Set<List<String>> getSetOfAllPropertyListsAccessibleFromThisDescriptor() {
		Set<List<String>> setOfAllPropertyListsAccessibleFromThisDescriptor = new HashSet<List<String>>();
		List<String> listOfProperties = new ArrayList<String>();
		listOfProperties.add(getValue());
		setOfAllPropertyListsAccessibleFromThisDescriptor.add(listOfProperties);
		return setOfAllPropertyListsAccessibleFromThisDescriptor;
	}	
	
	@Override
	public IOrderedSet upgradeAsTheGenericElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet orderedSet = new MinimalOS(getValue());
		return orderedSet;
	}	
	
	@Override
	public String getValue() {
		return getDescriptorName();
	}
	
	@Override
	public boolean getIsMinimal() {
		return true;
	}	

	@Override
	public IPropertyContainer getpropertyContainer() {
		IPropertyContainer propertyContainer;
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<GrammaticalST> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (GrammaticalST relevantComponentDescriptor : listOfRelevantComponents) {
			List<String> listOfRelevantComponentPropertiesWithPath = 
					relevantComponentDescriptor.getListOfRelevantPropertiesWithPath();
			for (String propertyWithPath : listOfRelevantComponentPropertiesWithPath){
				String propertyWithUpdatedPath = 
						this.getDescriptorName().concat(Settings.PATH_SEPARATOR + propertyWithPath);
				listOfRelevantPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		int dimensionToPropertyIndex = 0;
		Map<String, IProperty> dimensionToProperty = new HashMap<String, IProperty>();
		String indexedDimension;
		String propertyValue;
		IProperty property;
		StringBuilder stringBuilder;
		for (String propertyWithPath : listOfRelevantPropertiesWithPath) {
			stringBuilder = new StringBuilder();
			String dimensionToPropertyStringIndex = Integer.toString(dimensionToPropertyIndex);
			int lastSlashIndex = propertyWithPath.lastIndexOf(Settings.PATH_SEPARATOR);
			String dimension = propertyWithPath.substring(0, lastSlashIndex);
			stringBuilder.append(dimensionToPropertyStringIndex);
			stringBuilder.append(Settings.PROPERTY_INDEX_SEPARATOR);
			stringBuilder.append(dimension);
			indexedDimension = stringBuilder.toString();
			propertyValue = propertyWithPath.substring(lastSlashIndex + 1);
			property = new Property(propertyValue);
			dimensionToProperty.put(indexedDimension, property);
			dimensionToPropertyIndex++;
		}
		propertyContainer = new PropertyContainer(dimensionToProperty);
		return propertyContainer;
	}

	@Override
	public void setIsCodingElement(boolean isCodingElement) {
	}
	
	protected List<GrammaticalST> buildListOfRelevantComponentsForRelationBuilding() {
		List<GrammaticalST> listOfAllComponents = new ArrayList<GrammaticalST>();
		for (IElement component : getListOfComponents()) {
			listOfAllComponents.add((GrammaticalST) component);
		}
		return listOfAllComponents;
	}	
	
}