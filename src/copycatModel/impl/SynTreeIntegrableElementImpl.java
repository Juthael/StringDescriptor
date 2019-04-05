package copycatModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import copycatModel.ISynTreeIntegrableElement;
import copycatModel.IProperty;
import copycatModel.IPropertyContainer;
import exceptions.SynTreeGenerationException;
import settings.Settings;

public abstract class SynTreeIntegrableElementImpl implements Cloneable, ISynTreeIntegrableElement {

	public boolean isCodingDescriptor;
	
	public SynTreeIntegrableElementImpl(boolean codingDescriptor) {
		this.isCodingDescriptor = codingDescriptor;
	}
	
	//Getters
	@Override
	public IPropertyContainer getpropertyContainer() {
		IPropertyContainer propertyContainer;
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeIntegrableElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeIntegrableElementImpl relevantComponentDescriptor : listOfRelevantComponents) {
			List<String> listOfRelevantComponentPropertiesWithPath = 
					relevantComponentDescriptor.getListOfRelevantPropertiesWithPath();
			for (String propertyWithPath : listOfRelevantComponentPropertiesWithPath){
				String propertyWithUpdatedPath = this.getDescriptorName().concat("/" + propertyWithPath);
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
			int lastSlashIndex = propertyWithPath.lastIndexOf("/");
			String dimension = propertyWithPath.substring(0, lastSlashIndex);
			stringBuilder.append(dimensionToPropertyStringIndex);
			stringBuilder.append("_");
			stringBuilder.append(dimension);
			indexedDimension = stringBuilder.toString();
			propertyValue = propertyWithPath.substring(lastSlashIndex + 1);
			property = new PropertyImpl(propertyValue);
			dimensionToProperty.put(indexedDimension, property);
			dimensionToPropertyIndex++;
		}
		propertyContainer = new PropertyContainerImpl(dimensionToProperty);
		return propertyContainer;
	}
	
	@Override
	public boolean getIsCodingDescriptor() {
		return isCodingDescriptor;
	}
	
	@Override
	public List<String> getListOfPropertiesWithPath(){
		List<String> listOfPropertiesWithPath = new ArrayList<String>();
		List<SynTreeIntegrableElementImpl> listOfComponents = buildListOfComponents();
		for (SynTreeIntegrableElementImpl componentDescriptor : listOfComponents) {
			List<String> listOfComponentPropertiesWithPath = componentDescriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : listOfComponentPropertiesWithPath){
				String propertyWithUpdatedPath = this.getDescriptorName().concat("/" + propertyWithPath);
				listOfPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		return listOfPropertiesWithPath;
	}
	
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeIntegrableElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeIntegrableElementImpl componentDescriptor : listOfRelevantComponents) {
			List<String> listOfComponentRelevantPropertiesWithPath = 
					componentDescriptor.getListOfRelevantPropertiesWithPath();
			for (String propertyWithPath : listOfComponentRelevantPropertiesWithPath){
				String propertyWithUpdatedPath = this.getDescriptorName().concat("/" + propertyWithPath);
				listOfRelevantPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		return listOfRelevantPropertiesWithPath;
	}
	
	//Updater
	public void updatePosition(String newPosition, List<SynTreeIntegrableElementImpl>componentDescriptors) {
		doUpdatePosition(newPosition);
		updateComponentsPosition(newPosition, componentDescriptors);
	}
	
	protected void updateComponentsPosition(String newPosition,	List<SynTreeIntegrableElementImpl> componentDescriptors) {
		for (SynTreeIntegrableElementImpl componentDescriptor : componentDescriptors) {
			componentDescriptor.updatePosition(newPosition, componentDescriptor.buildListOfComponents());
		}
	}
	
	protected void updateComponentsPosition(int autoPosition, List<SynTreeIntegrableElementImpl> componentDescriptors) 
			throws SynTreeGenerationException {
		if (autoPosition == Settings.COMPONENT_AUTO_POSITIONING) {
			int positionIndex = 1;
			for (SynTreeIntegrableElementImpl componentDescriptor : componentDescriptors) {
				componentDescriptor.updatePosition(
						Integer.toString(positionIndex), componentDescriptor.buildListOfComponents());
				positionIndex++;			
			}
		} else throw new SynTreeGenerationException(
				"AbstractDescriptor.updateComponents() : illegal constant value.");
	}
	
	protected List<SynTreeIntegrableElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		return buildListOfComponents();
	}
	
	protected void doUpdatePosition(String newPosition) {
	}	
	
	//Abstract
	abstract protected List<SynTreeIntegrableElementImpl> buildListOfComponents();	
	
	@Override
	abstract protected SynTreeIntegrableElementImpl clone() throws CloneNotSupportedException;
	
	@Override
	abstract public String getDescriptorName();
	
}
