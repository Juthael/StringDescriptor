package copycatModel.implementations;

import java.util.ArrayList;
import java.util.HashMap;

import copycatModel.interfaces.PropertyContainerInterface;
import copycatModel.interfaces.PropertyInterface;
import exceptions.DescriptorsBuilderCriticalException;

public class PropertyContainerV1 implements Cloneable, PropertyContainerInterface {
	
	HashMap<String, PropertyInterface> dimensionToProperty = new HashMap<String, PropertyInterface>();
	
	public PropertyContainerV1(HashMap<String, PropertyInterface> dimensionToProperty) {
		this.dimensionToProperty = dimensionToProperty;
	}
	
	//Getters
	@Override
	public HashMap<String, PropertyInterface> getDimensionToProperty(){
		return dimensionToProperty;
	}

	@Override
	public ArrayList<String> getListOfDimensions() {
		ArrayList<String> listOfDimensions = new ArrayList<String>(dimensionToProperty.keySet());
		return listOfDimensions;
	}

	@Override
	public PropertyInterface getProperty(String dimension) throws DescriptorsBuilderCriticalException {
		if (dimensionToProperty.containsKey(dimension))
			return dimensionToProperty.get(dimension);
		else throw new DescriptorsBuilderCriticalException("Property container : unknown dimension requested");
	}
	
	

}
