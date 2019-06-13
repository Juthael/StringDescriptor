package description;

import java.util.List;

import model.synTreeModel.IStartElementST;

public interface IStringSyntaxer {
	
	List<IStartElementST> getListOfSyntacticTrees();
	
	IStartElementST getSyntacticTree(int index);

}
