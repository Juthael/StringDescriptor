package model.orderedSetModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.generalModel.IElement;

public interface ISetElement extends IElement {
	
	String getElementID();
	
	Map<String, Set<String>> getRelation();
	
	List<String> getElementDescription();
	
	void setMayBeTheCodedElement(boolean mayBeTheCodedElement);
	
	void setIsTheCodedElement(boolean isTheCodedElement);
	
	boolean getMayBeTheCodedElement();
	
	boolean getIsTheCodedElement();

}
