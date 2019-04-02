package syntacticTreesGeneration.interfaces;

import java.util.ArrayList;

import copycatModel.grammar.Group;

public interface ListOfDescriptorsWithPositionsInterface {

	ArrayList<Group> getListOfDescriptors();

	int getFirstPosition();

	int getLastPosition();

}