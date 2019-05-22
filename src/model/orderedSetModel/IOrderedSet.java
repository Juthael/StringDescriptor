package model.orderedSetModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.OrderedSetsGenerationException;
import model.generalModel.IElement;

public interface IOrderedSet extends IElement {
	
	String getElementID();
	
	List<String> getListOfCodingComponentsIDs();
	
	Map<String, Set<String>> getRelation() throws OrderedSetsGenerationException;
	
	Map<String, Set<String>> getReducedRelation() throws OrderedSetsGenerationException;
	
	Map<String, Set<String>> getContextualRelation(Set<String> setOfContextuallyRelevantElements, 
			boolean thisIsAComponentElement) throws OrderedSetsGenerationException;
	
	Map<String, Set<String>> getSetOfCodingComponentsRelation() throws OrderedSetsGenerationException;
	
	Map<String, Set<String>> getSetOfCodingComponentsReducedRelation() throws OrderedSetsGenerationException;
	
	List<String> getListOfLowerSetMaximalChains();
	
	boolean getMayBeTheCodedElement();
	
	boolean getIsTheCodedElement();
	
	Set<String> getLowerSetIDs();
	
	Set<String> getLowerSetInformativeIDs();	
	
	Set<String> getLowerSetContextuallyInformativeIDs(Set<String> setOfContextuallyRelevantElements);	
	
	Set<IOrderedSet> getLowerSet();
	
	Set<IOrderedSet> getInformativeLowerSet();
	
	boolean getIsOmegaElement();
	
	int getNbOfParents();	
	
	int getNbOfInformativeChildren();

	boolean getThisSetIsInformative();
	
	void setElementID(String elementID);
	
	void setMayBeTheCodedElement(boolean mayBeTheCodedElement);
	
	void setIsTheCodedElement(boolean isTheCodedElement);
	
	void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet);

}
