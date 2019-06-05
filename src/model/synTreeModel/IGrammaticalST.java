package model.synTreeModel;

import java.util.List;

import model.synTreeModel.impl.utils.IPropertyContainer;

public interface IGrammaticalST extends ISyntacticTree {

	IPropertyContainer getpropertyContainer();
	
	List<String> getListOfRelevantPropertiesWithPath();
	
	void setIsCodingElement(boolean isCodingElement);
}