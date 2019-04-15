package syntacticTreesGeneration;

import java.util.List;

import model.copycatModel.synTreeGrammar.Group;

public interface IListOfDescriptorsWithPositions {

	List<Group> getListOfDescriptors();

	int getFirstPosition();

	int getLastPosition();

}