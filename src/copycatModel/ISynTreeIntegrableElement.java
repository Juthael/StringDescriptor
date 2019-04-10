package copycatModel;

import java.util.List;

public interface ISynTreeIntegrableElement {

	//Getters
	
	boolean getIsCodingDescriptor();
	
	String getDescriptorName();
	
	IPropertyContainer getpropertyContainer();
	
	List<String> getListOfPropertiesWithPath();

	List<String> getListOfRelevantPropertiesWithPath();

	List<String> getListOfPropertiesWithPathWithoutQuantifiers();

}