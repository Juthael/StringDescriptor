package copycatModel.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.DescriptorsBuilderCriticalException;

public interface PropertyContainerInterface {

	//Getter
	HashMap<String, PropertyInterface> getDimensionToProperty();
	
	ArrayList<String> getListOfDimensions();	
	
	PropertyInterface getProperty(String dimension) throws DescriptorsBuilderCriticalException;	
	
}