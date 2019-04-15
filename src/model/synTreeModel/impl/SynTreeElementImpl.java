package model.synTreeModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.generalModel.IElement;
import model.generalModel.impl.ElementImpl;
import model.synTreeModel.IProperty;
import model.synTreeModel.IPropertyContainer;
import model.synTreeModel.ISynTreeElement;
import settings.Settings;

public abstract class SynTreeElementImpl extends ElementImpl implements Cloneable, ISynTreeElement {

	public SynTreeElementImpl() {
		super();
	}
	
	public SynTreeElementImpl(boolean isCodingByDecomposition) {
		super(isCodingByDecomposition);
	}
	
	//Getters
	@Override
	public IPropertyContainer getpropertyContainer() {
		IPropertyContainer propertyContainer;
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeElementImpl relevantComponentDescriptor : listOfRelevantComponents) {
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
		List<SynTreeElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeElementImpl componentDescriptor : listOfRelevantComponents) {
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
	public void updatePosition(String newPosition, List<IElement>componentDescriptors) {
		doUpdatePosition(newPosition);
		updateComponentsPosition(newPosition, componentDescriptors);
	}
	
	protected void updateComponentsPosition(String newPosition,	List<IElement> componentDescriptors) {
		for (IElement componentDescriptor : componentDescriptors) {
			SynTreeElementImpl synTreeComponent = (SynTreeElementImpl) componentDescriptor;
			synTreeComponent.updatePosition(newPosition, synTreeComponent.buildListOfComponents());
		}
	}
	
	protected void updateComponentsPosition(int autoPosition, List<SynTreeElementImpl> componentDescriptors) 
			throws SynTreeGenerationException {
		if (autoPosition == Settings.COMPONENT_AUTO_POSITIONING) {
			int positionIndex = 1;
			StringBuilder sB;
			for (SynTreeElementImpl componentDescriptor : componentDescriptors) {
				sB = new StringBuilder();
				String positionValue = Integer.toString(positionIndex);
				String specialPositionValue = getSpecialPositionValue(positionIndex, componentDescriptors.size());
				sB.append(positionValue);
				if (!specialPositionValue.isEmpty()) {
					sB.append(Settings.POSITION_VALUES_SEPARATOR);
					sB.append(specialPositionValue);	
				}
				componentDescriptor.updatePosition(sB.toString(), componentDescriptor.buildListOfComponents());
				positionIndex++;			
			}
		} else throw new SynTreeGenerationException(
				"AbstractDescriptor.updateComponents() : illegal constant value.");
	}
	
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> listOfRelevantComponents = new ArrayList<SynTreeElementImpl>();
		for (IElement component : buildListOfComponents()) {
			listOfRelevantComponents.add((SynTreeElementImpl) component);
		}
		return listOfRelevantComponents;
	}
	
	protected void doUpdatePosition(String newPosition) {
	}	

	@Override
	abstract protected SynTreeElementImpl clone() throws CloneNotSupportedException;
	
	private String getSpecialPositionValue(int positionIndex, int nbOfComponentDescriptors) {
		String specialPositionValue = "";
		if (nbOfComponentDescriptors > 1) {
			if (positionIndex == 1)
				specialPositionValue = Settings.FIRST_POSITION;
			else if (positionIndex == nbOfComponentDescriptors) {
				specialPositionValue = Settings.LAST_POSITION;
			}
			else if (nbOfComponentDescriptors % 2 == 1) {
				int halfIntegerPart = (int) nbOfComponentDescriptors/2;
				if (positionIndex == (halfIntegerPart + 1))
					specialPositionValue = Settings.CENTRAL_POSITION;
			}
		}
		return specialPositionValue;
	}

}
