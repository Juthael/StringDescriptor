package model.synTreeModel;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;

public interface ISynTreeElement extends IElement {

	//Getters
	
	IPropertyContainer getpropertyContainer();
	
	List<String> getListOfRelevantPropertiesWithPath();
	
	ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex);
}