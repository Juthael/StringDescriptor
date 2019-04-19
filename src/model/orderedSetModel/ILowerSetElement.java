package model.orderedSetModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;

public interface ILowerSetElement extends IElement {
	
	String getElementID();
	
	Map<String, Set<String>> getRelation();
	
	List<String> getListOfLowerSetMaximalChains();
	
	boolean getMayBeTheCodedElement();
	
	boolean getIsTheCodedElement();
	
	Set<String> getLowerSetIDs();
	
	void setMayBeTheCodedElement(boolean mayBeTheCodedElement);
	
	void setIsTheCodedElement(boolean isTheCodedElement);	

}
