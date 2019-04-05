package syntacticTreesGeneration.impl;

import java.util.List;

import copycatModel.grammar.Group;
import syntacticTreesGeneration.IListOfDescriptorsWithPositions;

public class ListOfDescriptorsWithPositionsImpl implements IListOfDescriptorsWithPositions {

	private List<Group> listOfDescriptors;
	private int firstPosition;
	private int lastPosition;
	
	public ListOfDescriptorsWithPositionsImpl(List<Group> listOfDescriptors, int firstPosition, int lastPosition) {
		this.listOfDescriptors = listOfDescriptors;
		this.firstPosition = firstPosition;
		this.lastPosition = lastPosition;
	}

	@Override
	public List<Group> getListOfDescriptors() {
		return listOfDescriptors;
	}

	@Override
	public int getFirstPosition() {
		return firstPosition;
	}

	@Override
	public int getLastPosition() {
		return lastPosition;
	}

}
