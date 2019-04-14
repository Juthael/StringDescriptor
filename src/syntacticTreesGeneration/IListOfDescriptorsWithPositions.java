package syntacticTreesGeneration;

import java.util.List;

import copycatModel.synTreeModel.grammar.Group;

public interface IListOfDescriptorsWithPositions {

	List<Group> getListOfDescriptors();

	int getFirstPosition();

	int getLastPosition();

}