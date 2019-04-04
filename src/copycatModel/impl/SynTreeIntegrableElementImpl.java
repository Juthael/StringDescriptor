package copycatModel.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.PropertyContainerInterface;
import copycatModel.interfaces.PropertyInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;

public abstract class AbstractDescriptorV1 implements Cloneable, AbstractDescriptorInterface {

	public boolean isCodingDescriptor;
	
	public AbstractDescriptorV1(boolean codingDescriptor) {
		this.isCodingDescriptor = codingDescriptor;
	}
	
	//Getters
	@Override
	public PropertyContainerInterface getpropertyContainer() {
		PropertyContainerInterface propertyContainer;
		ArrayList<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		ArrayList<AbstractDescriptorV1> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (AbstractDescriptorV1 relevantComponentDescriptor : listOfRelevantComponents) {
			ArrayList<String> listOfRelevantComponentPropertiesWithPath = 
					relevantComponentDescriptor.getListOfRelevantPropertiesWithPath();
			for (String propertyWithPath : listOfRelevantComponentPropertiesWithPath){
				String propertyWithUpdatedPath = this.getDescriptorName().concat("/" + propertyWithPath);
				listOfRelevantPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		int dimensionToPropertyIndex = 0;
		HashMap<String, PropertyInterface> dimensionToProperty = new HashMap<String, PropertyInterface>();
		String indexedDimension;
		String propertyValue;
		PropertyInterface property;
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
			property = new PropertyV1(propertyValue);
			dimensionToProperty.put(indexedDimension, property);
			dimensionToPropertyIndex++;
		}
		propertyContainer = new PropertyContainerV1(dimensionToProperty);
		return propertyContainer;
	}
	
	@Override
	public boolean getIsCodingDescriptor() {
		return isCodingDescriptor;
	}
	
	@Override
	public ArrayList<String> getListOfPropertiesWithPath(){
		ArrayList<String> listOfPropertiesWithPath = new ArrayList<String>();
		ArrayList<AbstractDescriptorV1> listOfComponents = buildListOfComponents();
		for (AbstractDescriptorV1 componentDescriptor : listOfComponents) {
			ArrayList<String> listOfComponentPropertiesWithPath = componentDescriptor.getListOfPropertiesWithPath();
			for (String propertyWithPath : listOfComponentPropertiesWithPath){
				String propertyWithUpdatedPath = this.getDescriptorName().concat("/" + propertyWithPath);
				listOfPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		return listOfPropertiesWithPath;
	}
	
	public ArrayList<String> getListOfRelevantPropertiesWithPath(){
		ArrayList<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		ArrayList<AbstractDescriptorV1> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (AbstractDescriptorV1 componentDescriptor : listOfRelevantComponents) {
			ArrayList<String> listOfComponentRelevantPropertiesWithPath = 
					componentDescriptor.getListOfRelevantPropertiesWithPath();
			for (String propertyWithPath : listOfComponentRelevantPropertiesWithPath){
				String propertyWithUpdatedPath = this.getDescriptorName().concat("/" + propertyWithPath);
				listOfRelevantPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		return listOfRelevantPropertiesWithPath;
	}
	
	//Updater
	public void updatePosition(String newPosition, ArrayList<AbstractDescriptorV1>componentDescriptors) {
		doUpdatePosition(newPosition);
		updateComponentsPosition(newPosition, componentDescriptors);
	}
	
	protected void updateComponentsPosition(String newPosition,	ArrayList<AbstractDescriptorV1> componentDescriptors) {
		for (AbstractDescriptorV1 componentDescriptor : componentDescriptors) {
			componentDescriptor.updatePosition(newPosition, componentDescriptor.buildListOfComponents());
		}
	}
	
	protected void updateComponentsPosition(int autoPosition, ArrayList<AbstractDescriptorV1> componentDescriptors) 
			throws DescriptorsBuilderCriticalException {
		if (autoPosition == DescGenSettings.COMPONENT_AUTO_POSITIONING) {
			int positionIndex = 1;
			for (AbstractDescriptorV1 componentDescriptor : componentDescriptors) {
				componentDescriptor.updatePosition(
						Integer.toString(positionIndex), componentDescriptor.buildListOfComponents());
				positionIndex++;			
			}
		} else throw new DescriptorsBuilderCriticalException(
				"AbstractDescriptor.updateComponents() : illegal constant value.");
	}
	
	protected ArrayList<AbstractDescriptorV1> buildListOfRelevantComponentsForRelationBuilding() {
		return buildListOfComponents();
	}
	
	protected void doUpdatePosition(String newPosition) {
	}	
	
	//Abstract
	abstract protected ArrayList<AbstractDescriptorV1> buildListOfComponents();	
	
	@Override
	abstract protected AbstractDescriptorV1 clone() throws CloneNotSupportedException;
	
	@Override
	abstract public String getDescriptorName();
	
}
