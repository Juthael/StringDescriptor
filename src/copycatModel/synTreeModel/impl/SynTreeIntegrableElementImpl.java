package copycatModel.synTreeModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import copycatModel.synTreeModel.IProperty;
import copycatModel.synTreeModel.IPropertyContainer;
import copycatModel.synTreeModel.ISynTreeIntegrableElement;
import exceptions.SynTreeGenerationException;
import settings.Settings;

public abstract class SynTreeIntegrableElementImpl extends SynTreeElementImpl implements Cloneable, ISynTreeIntegrableElement {

	public SynTreeIntegrableElementImpl() {
		super();
	}
	
	public SynTreeIntegrableElementImpl(boolean isCodingByDecomposition) {
		super(isCodingByDecomposition);
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
			property = new PropertyImpl(propertyValue);
			dimensionToProperty.put(indexedDimension, property);
			dimensionToPropertyIndex++;
		}
		propertyContainer = new PropertyContainerImpl(dimensionToProperty);
		return propertyContainer;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeIntegrableElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeIntegrableElementImpl componentDescriptor : listOfRelevantComponents) {
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

	@Override
	abstract protected SynTreeIntegrableElementImpl clone() throws CloneNotSupportedException;

}
