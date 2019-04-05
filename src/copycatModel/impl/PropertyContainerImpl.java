package copycatModel.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import copycatModel.IProperty;
import copycatModel.IPropertyContainer;
import exceptions.DescriptorsBuilderException;

public class PropertyContainerImpl implements Cloneable, IPropertyContainer {
	
	Map<String, IProperty> dimensionToProperty = new HashMap<String, IProperty>();
	
	public PropertyContainerImpl(Map<String, IProperty> dimensionToProperty) {
		this.dimensionToProperty = dimensionToProperty;
	}
	
	//Getters
	@Override
	public Map<String, IProperty> getDimensionToProperty(){
		return dimensionToProperty;
	}

	@Override
	public List<String> getListOfDimensions() {
		List<String> listOfDimensions = new ArrayList<String>(dimensionToProperty.keySet());
		return listOfDimensions;
	}

	@Override
	public IProperty getProperty(String dimension) throws DescriptorsBuilderException {
		if (dimensionToProperty.containsKey(dimension))
			return dimensionToProperty.get(dimension);
		else throw new DescriptorsBuilderException("Property container : unknown dimension requested");
	}
	
	

}
