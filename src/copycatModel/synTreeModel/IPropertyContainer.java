package copycatModel.synTreeModel;

import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;

public interface IPropertyContainer {

	//Getter
	Map<String, IProperty> getIndexedPathToProperty();
	
	List<String> getListOfIndexedPaths();	
	
	List<String> getOrderedListOfIndexedPaths();	
	
	IProperty getProperty(String dimension) throws SynTreeGenerationException;
	
}