package model.synTreeModel;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;

public interface ISynTreeElement extends IElement {

	//Getters
	
	IPropertyContainer getpropertyContainer();
	
	List<String> getListOfRelevantPropertiesWithPath();
	
	IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex);
}