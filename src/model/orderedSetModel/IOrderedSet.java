package model.orderedSetModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;

public interface IOrderedSet extends IElement {
	
	String getElementID();
	
	List<String> getListOfCodingComponentsIDs();
	
	Map<String, Set<String>> getRelation();
	
	List<String> getListOfLowerSetMaximalChains();
	
	boolean getMayBeTheCodedElement();
	
	boolean getIsTheCodedElement();
	
	Set<String> getLowerSetIDs();
	
	Set<IOrderedSet> getLowerSet();
	
	Set<IOrderedSet> getInformativeLowerSet();
	
	int getNbOfParents();	
	
	int getNbOfInformativeChildren();

	boolean getThisSetIsInformative();
	
	void setElementID(String elementID);
	
	void setMayBeTheCodedElement(boolean mayBeTheCodedElement);
	
	void setIsTheCodedElement(boolean isTheCodedElement);
	
	void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet);

}
