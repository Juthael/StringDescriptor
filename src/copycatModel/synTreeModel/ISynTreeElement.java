package copycatModel.synTreeModel;

import java.util.List;
import java.util.Set;

public interface ISynTreeElement {

	boolean getIsCodingByDecomposition();
	
	List<String> getListOfPropertiesWithPath();
	
	List<String> getListOfPropertiesWithPathWithoutQuantifiers();	
	
	Set<List<String>> getSetOfAllPropertyListsAccessibleFromThisDescriptor();
	
	String getDescriptorName();
	
}
