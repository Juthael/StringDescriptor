package copycatModel.interfaces;

import java.util.ArrayList;

public interface AbstractDescriptorInterface {

	//Getters
	
	boolean getIsCodingDescriptor();
	
	String getDescriptorName();
	
	PropertyContainerInterface getpropertyContainer();
	
	ArrayList<String> getListOfPropertiesWithPath();

}