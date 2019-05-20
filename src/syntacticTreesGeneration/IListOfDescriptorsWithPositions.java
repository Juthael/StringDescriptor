package syntacticTreesGeneration;

import java.util.List;

import model.copycatModel.synTreeGrammar.Frame;

public interface IListOfDescriptorsWithPositions {

	List<Frame> getListOfDescriptors();

	int getFirstPosition();

	int getLastPosition();

}