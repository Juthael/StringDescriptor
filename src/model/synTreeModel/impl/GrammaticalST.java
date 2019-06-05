package model.synTreeModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.utils.IProperty;
import model.synTreeModel.impl.utils.IPropertyContainer;
import model.synTreeModel.impl.utils.impl.Property;
import model.synTreeModel.impl.utils.impl.PropertyContainer;
import settings.Settings;

public abstract class GrammaticalST extends SyntacticTree implements Cloneable, IGrammaticalST {

	public GrammaticalST() {
	}
	
	public GrammaticalST(boolean isCodingElement) {
		super(isCodingElement);
	}
	
	@Override
	public IPropertyContainer getpropertyContainer() {
		IPropertyContainer propertyContainer;
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<IGrammaticalST> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (IGrammaticalST relevantComponentDescriptor : listOfRelevantComponents) {
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
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<IGrammaticalST> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (IGrammaticalST componentDescriptor : listOfRelevantComponents) {
			List<String> listOfComponentRelevantPropertiesWithPath = 
					componentDescriptor.getListOfRelevantPropertiesWithPath();
			for (String propertyWithPath : listOfComponentRelevantPropertiesWithPath){
				String propertyWithUpdatedPath = 
						this.getDescriptorName().concat(Settings.PATH_SEPARATOR + propertyWithPath);
				listOfRelevantPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		return listOfRelevantPropertiesWithPath;
	}	
	
	protected List<IGrammaticalST> buildListOfRelevantComponentsForRelationBuilding() {
		return getListOfAllComponentsForRelationBuilding();
	}
	
	@Override 
	public void setIsCodingElement(boolean isCodingElement) {
		this.isCodingElement = isCodingElement;
	}

	@Override
	abstract protected GrammaticalST clone() throws CloneNotSupportedException;
	
	private List<IGrammaticalST> getListOfAllComponentsForRelationBuilding() {
		List<IGrammaticalST> listOfAllComponents = new ArrayList<IGrammaticalST>();
		for (IElement component : getListOfComponents()) {
			listOfAllComponents.add((IGrammaticalST) component);
		}
		return listOfAllComponents;
	}

}
