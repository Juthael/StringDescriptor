package model.generalModel;

import java.util.List;
import java.util.Set;

public interface IElement {

	boolean getIsCodingElement();
	
	List<String> getListOfPropertiesWithPath();
	
	List<String> getListOfPropertiesWithPathWithoutQuantifiers();	
	
	Set<List<String>> getSetOfAllPropertyListsAccessibleFromThisDescriptor();
	
	List<IElement> getListOfComponents();
	
	List<IElement> getListOfCodingElements();
	
	String getDescriptorName();
	
}
