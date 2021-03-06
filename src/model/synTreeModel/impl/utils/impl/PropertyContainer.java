package model.synTreeModel.impl.utils.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.synTreeModel.impl.utils.IProperty;
import model.synTreeModel.impl.utils.IPropertyContainer;
import settings.Settings;

public class PropertyContainer implements Cloneable, IPropertyContainer {
	
	Map<String, IProperty> dimensionToProperty = new HashMap<String, IProperty>();
	
	public PropertyContainer(Map<String, IProperty> dimensionToProperty) {
		this.dimensionToProperty = dimensionToProperty;
	}
	
	//Getters
	@Override
	public Map<String, IProperty> getIndexedPathToProperty(){
		return dimensionToProperty;
	}

	@Override
	public List<String> getListOfIndexedPaths() {
		List<String> listOfDimensions = new ArrayList<String>(dimensionToProperty.keySet());
		return listOfDimensions;
	}
	
	@Override 
	public List<String> getOrderedListOfIndexedPaths() {
		List<String> orderedListOfDimensions;
		List<String> listOfDimensions = getListOfIndexedPaths();
		String[] dimensionArray = new String[listOfDimensions.size()];
		for (String indexedDimension : listOfDimensions) {
			int index = Integer.parseInt(indexedDimension.split(Settings.PROPERTY_INDEX_SEPARATOR)[0]);
			dimensionArray[index] = listOfDimensions.get(index);
		}
		orderedListOfDimensions = new ArrayList<String>(Arrays.asList(dimensionArray));
		return orderedListOfDimensions;
	}

	@Override
	public IProperty getProperty(String dimension) throws SynTreeGenerationException {
		if (dimensionToProperty.containsKey(dimension))
			return dimensionToProperty.get(dimension);
		else throw new SynTreeGenerationException("Property container : unknown dimension requested");
	}
	
	

}
