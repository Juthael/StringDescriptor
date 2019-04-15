package model.generalModel;

import java.util.List;
import java.util.Set;

public interface IElement {

	boolean getIsCodingByDecomposition();
	
	List<String> getListOfPropertiesWithPath();
	
	List<String> getListOfPropertiesWithPathWithoutQuantifiers();	
	
	Set<List<String>> getSetOfAllPropertyListsAccessibleFromThisDescriptor();
	
	String getDescriptorName();
	
}
