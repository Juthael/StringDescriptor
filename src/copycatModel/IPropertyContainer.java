package copycatModel;

import java.util.List;
import java.util.Map;

import exceptions.DescriptorsBuilderException;

public interface IPropertyContainer {

	//Getter
	Map<String, IProperty> getDimensionToProperty();
	
	List<String> getListOfDimensions();	
	
	IProperty getProperty(String dimension) throws DescriptorsBuilderException;	
	
}