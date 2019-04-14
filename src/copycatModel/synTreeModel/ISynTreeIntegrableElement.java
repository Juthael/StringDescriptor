package copycatModel.synTreeModel;

import java.util.List;

public interface ISynTreeIntegrableElement extends ISynTreeElement {

	//Getters
	
	IPropertyContainer getpropertyContainer();
	
	List<String> getListOfRelevantPropertiesWithPath();

}