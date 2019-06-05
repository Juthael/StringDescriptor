package model.synTreeModel;

import java.util.List;

public interface IMinimalST extends ISyntacticTree {
	
	String getValue();
	
	public List<String> getListOfRelevantPropertiesWithPath();

}
