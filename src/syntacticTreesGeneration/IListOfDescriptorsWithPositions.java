package syntacticTreesGeneration;

import java.util.List;

import copycatModel.grammar.Group;

public interface IListOfDescriptorsWithPositions {

	List<Group> getListOfDescriptors();

	int getFirstPosition();

	int getLastPosition();

}