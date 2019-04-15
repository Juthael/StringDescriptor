package model.synTreeModel;

import java.util.List;

import model.generalModel.IElement;

public interface ISynTreeElement extends IElement {

	//Getters
	
	IPropertyContainer getpropertyContainer();
	
	List<String> getListOfRelevantPropertiesWithPath();

}