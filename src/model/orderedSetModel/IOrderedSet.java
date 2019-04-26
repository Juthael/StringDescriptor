package model.orderedSetModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import model.generalModel.IElement;

public interface IOrderedSet extends IElement {
	
	String getElementID();
	
	List<String> getListOfCodingComponentsIDs();
	
	Map<String, Set<String>> getRelation();
	
	Map<String, Set<String>> getClarifiedRelation() throws OrderedSetsGenerationException;
	
	List<String> getListOfLowerSetMaximalChains();
	
	boolean getMayBeTheCodedElement();
	
	boolean getIsTheCodedElement();
	
	Set<String> getLowerSetIDs();
	
	Set<IOrderedSet> getLowerSet();
	
	void setElementID(String elementID);
	
	void setMayBeTheCodedElement(boolean mayBeTheCodedElement);
	
	void setIsTheCodedElement(boolean isTheCodedElement);	

}
