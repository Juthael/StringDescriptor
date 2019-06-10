package syntacticTreesGeneration.impl;

import java.util.List;

import model.copycatModel.synTreeGrammar.Frame;
import syntacticTreesGeneration.IListOfDescriptorsWithPositions;

public class ListOfDescriptorsWithPositions implements IListOfDescriptorsWithPositions {

	private List<Frame> listOfDescriptors;
	private int firstPosition;
	private int lastPosition;
	
	public ListOfDescriptorsWithPositions(List<Frame> listOfDescriptors, int firstPosition, int lastPosition) {
		this.listOfDescriptors = listOfDescriptors;
		this.firstPosition = firstPosition;
		this.lastPosition = lastPosition;
	}

	@Override
	public List<Frame> getListOfDescriptors() {
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
